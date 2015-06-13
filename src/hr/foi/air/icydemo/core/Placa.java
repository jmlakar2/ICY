package hr.foi.air.icydemo.core;

import hr.foi.air.icydemo.db.Korisnici;
import hr.foi.air.icydemo.db.VrijemePosao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Placa {
	private double stopa_doprinosi_placa = 0.2;
	private int osobni_odbitak = 2600;
	private int po_uzdrzavani_clan = 1300;
	private int[] po_dijete = new int[] {1300, 1820, 2600, 3640, 4940, 6500, 8320, 10400, 12740, 15340};
	private int[] po_invalidnost = new int[] {780,2600};
	private int[] po_PPDS = new int[] {3500,3000,2400};
	private int po_BPP = 2400; 
	private int po_umirovljenici = 3800;
	private double[] porezne_stope = new double[] {0.12,0.25,0.4};

	/**
	 * metoda koja raèuna plaæu i radne sate
	 * @param mjesec prosljeðeni mjesec
	 * @param korisnik korisnik za kojeg se raèuna plaæa
	 * @return prvi element - odraðeni sati, drugi element - neto plaæa
	 */
	public double[] getPlaca (int mjesec, Korisnici korisnik) {
		double[] rezultat = new double[2];
		rezultat = racunanjePlace(mjesec, korisnik);

		return rezultat;
	}
	
	private double[] racunanjePlace (int mjesec, Korisnici korisnik) {
		double[] rezultat = new double[2];
		
		double sati = racunaj_sate(mjesec, korisnik);
		rezultat[0] = sati;
		
		double bruto = sati * korisnik.getSatnica();
		
		double doprinosi_iz_place = bruto * stopa_doprinosi_placa;
		
		double dohodak = bruto - doprinosi_iz_place;
		
		double odbici = osobni_odbitak + uzdrzavani_clanovi(korisnik) + broj_djece(korisnik) + invalidnost(korisnik) + ppds(korisnik) + 
				bp(korisnik) + umirovljenici(korisnik);
		
		double porezna_osnovica = dohodak - odbici;
		
		if (porezna_osnovica < 0)
			porezna_osnovica = 0;
		
		double prirez = (korisnik.getPrirez() / 100) * porez_na_dohodak(porezna_osnovica);
		
		double prirez_porez = prirez + porez_na_dohodak(porezna_osnovica);
		
		double neto = dohodak - prirez_porez;
		rezultat[1]=neto;
		
		return rezultat;
	}
	
	private double racunaj_sate (int mjesec, Korisnici korisnik) {
		List<VrijemePosao> dani = korisnik.dani();
		
		double pocetak, kraj, ukupno = 0;
		Calendar cal = Calendar.getInstance();
		
		for (VrijemePosao D : dani) {
			cal.setTime (D.getKraj());
			int m = cal.get(Calendar.MONTH);
			int y = cal.get(Calendar.YEAR);
			
			Date date = new Date();
			cal.setTime(date);
			int y_now = cal.get(Calendar.YEAR);
			
			m = m+1;
			if (m==mjesec && y_now==y) {
				pocetak = D.getPocetak().getTime();
				kraj = D.getKraj().getTime();
				ukupno += kraj - pocetak; 
			}
		}
		if (ukupno < 0)
			ukupno = 0;
		
		double sati = (ukupno/1000) / 3600;
		
		return sati;
	}
	
	private double uzdrzavani_clanovi (Korisnici korisnik) {
		if (korisnik.getBr_uzdrzavani_clanovi()>1) {
			return po_uzdrzavani_clan;
		}
		
		return 0;
	}
	
	private double broj_djece (Korisnici korisnik) {
		try
		{
			int index = korisnik.getBr_djeca()-1;
			return po_dijete[index];
		}catch (Exception e)
		{
			return 0;
		}
	}
	private double invalidnost (Korisnici korisnik) {
		if (korisnik.getRazina_invalidnost() == 1)
			return po_invalidnost[0];
		else if (korisnik.getRazina_invalidnost() == 2)
			return po_invalidnost[1];
		else return 0;
	}
	private double ppds (Korisnici korisnik) {
		try
		{
			int index = korisnik.getSkupina_PPDS()-1;
			return po_PPDS[index];
		}catch (Exception e)
		{
			return 0;
		}

	}
	private double bp (Korisnici korisnik) {
		if (korisnik.getBP_podrucje()==1)
			return po_BPP;
		return 0;
	}
	private double umirovljenici (Korisnici korisnik) {
		if (korisnik.getBr_umirovljenik()==1)
			return po_umirovljenici;
		return 0;
	}
	public double porez_na_dohodak (double po) {
		double rezultat = 0;
		if (po > 2200 && po <= 13200) {
			rezultat += 2200 * porezne_stope[0];
			rezultat += (po-2200) * porezne_stope[1];
		}
		else if (po > 13200) {
			rezultat += 2200 * porezne_stope[0];
			rezultat += 11200 * porezne_stope[1];
			rezultat += (po-13200)*porezne_stope[2];
		}
		else 
			rezultat += po*porezne_stope[0];
		return rezultat;
	}
}
