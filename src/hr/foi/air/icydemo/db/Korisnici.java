package hr.foi.air.icydemo.db;

import java.util.Date;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Korisnici")
public class Korisnici extends Model{
	@Column (name = "id_korisnik")
	private int id_korisnik;
	@Column (name = "radno_mjesto_x")
	private double radno_mjesto_x;
	@Column (name = "radno_mjesto_y")
	private double radno_mjesto_y;
	@Column (name = "satnica")
	private float satnica;
	@Column (name = "radno_vrijeme")
	private int radno_vrijeme;
	@Column (name = "pauza")
	private int pauza;
	@Column (name = "prirez")
	private float prirez;
	@Column (name = "br_uzdrzavani_clanovi")
	private int br_uzdrzavani_clanovi;
	@Column (name = "br_djeca")
	private int br_djeca;
	@Column (name = "razina_invalidnost")
	private int razina_invalidnost;
	@Column (name = "skupina_PPDS")
	private int skupina_PPDS;
	@Column (name = "BP_podrucje")
	private int BP_podrucje;
	@Column (name = "br_umirovljenik")
	private int br_umirovljenik;
	@Column (name = "zadnji_dolazak")
	private Date zadnji_dolazak;
	
	public Korisnici () {
		super();
	}
	
	public Korisnici (int id_korisnik, 
			float satnica, int radno_vrijeme, int pauza, float prirez, int br_uzdrzavani_clanovi, int br_djeca, int razina_invalidnost,
			int skupina_PPDS, int BP_podrucje, int br_umirovljenik) {
		this.id_korisnik = id_korisnik;
		this.radno_mjesto_x = 0;
		this.radno_mjesto_y = 0;
		this.satnica = satnica;
		this.radno_vrijeme = radno_vrijeme;
		this.pauza = pauza;
		this.prirez = prirez;
		this.br_uzdrzavani_clanovi = br_uzdrzavani_clanovi;
		this.br_djeca = br_djeca;
		this.razina_invalidnost = razina_invalidnost;
		this.skupina_PPDS = skupina_PPDS;
		this.BP_podrucje = BP_podrucje;
		this.br_umirovljenik = br_umirovljenik;
		this.zadnji_dolazak = null;
	}
	
	public List<VrijemePosao> dani(){
		return getMany(VrijemePosao.class, "Korisnici");
	}
	
	public int getBP_podrucje() {
		return BP_podrucje;
	}
	public int getBr_djeca() {
		return br_djeca;
	}
	public int getBr_umirovljenik() {
		return br_umirovljenik;
	}
	public int getBr_uzdrzavani_clanovi() {
		return br_uzdrzavani_clanovi;
	}
	public int getId_korisnik() {
		return id_korisnik;
	}
	public int getPauza() {
		return pauza;
	}
	public float getPrirez() {
		return prirez;
	}
	public double getRadno_mjesto_x() {
		return radno_mjesto_x;
	}
	public double getRadno_mjesto_y() {
		return radno_mjesto_y;
	}
	public int getRadno_vrijeme() {
		return radno_vrijeme;
	}
	public int getRazina_invalidnost() {
		return razina_invalidnost;
	}
	public float getSatnica() {
		return satnica;
	}
	public int getSkupina_PPDS() {
		return skupina_PPDS;
	}
	public Date getZadnji_dolazak() {
		return zadnji_dolazak;
	}
	public void setRadno_mjesto_x(double radno_mjesto_x) {
		this.radno_mjesto_x = radno_mjesto_x;
	}
	public void setRadno_mjesto_y(double radno_mjesto_y) {
		this.radno_mjesto_y = radno_mjesto_y;
	}
	public void setZadnji_dolazak(Date dolazak) {
		this.zadnji_dolazak=dolazak;
	}
	public void updateKorisnci (Korisnici newKorisnici) {
		this.satnica = newKorisnici.getSatnica();
		this.radno_vrijeme = newKorisnici.getRadno_vrijeme();
		this.pauza = newKorisnici.getPauza();
		this.prirez = newKorisnici.getPrirez();
		this.br_uzdrzavani_clanovi = newKorisnici.getBr_uzdrzavani_clanovi();
		this.br_djeca = newKorisnici.getBr_djeca();
		this.razina_invalidnost = newKorisnici.getRazina_invalidnost();
		this.skupina_PPDS = newKorisnici.getSkupina_PPDS();
		this.BP_podrucje = newKorisnici.getBP_podrucje();
		this.br_umirovljenik = newKorisnici.getBr_umirovljenik();
		this.save();
	}
	
}
