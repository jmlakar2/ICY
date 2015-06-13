package hr.foi.air.icydemo.core;

import hr.foi.air.icydemo.db.Korisnici;
import hr.foi.air.icydemo.db.VrijemePosao;

import java.util.Date;

import com.activeandroid.query.Select;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class MyLocationListener implements LocationListener{
	/**
	 * Osluškivaè za trenutnu lokaciju mobilnog ureðaja... ovisno o lokaciji u bazu unosi timestamp za poèetak ili kraj posla
	 */
	@Override
	public void onLocationChanged(Location location) {
		try {
			Korisnici korisnik = new Select().all().from(Korisnici.class).executeSingle();
			VrijemePosao vrijemePosao;
			Date nula = new Date(0);
			if (location != null && korisnik != null) {
				if (korisnik.getRadno_mjesto_x()!=location.getLongitude()
						||korisnik.getRadno_mjesto_y()!=location.getLatitude()) {
					long zadnji_dolazak = korisnik.getZadnji_dolazak().getTime();
					vrijemePosao = new Select().all().from(VrijemePosao.class).where("pocetak = ?", zadnji_dolazak).executeSingle();
					if(vrijemePosao.getKraj().equals(nula) && !vrijemePosao.getPocetak().equals(nula)) {	
						Date date = new Date();
						vrijemePosao.setKraj(date);
						vrijemePosao.save();
					}
				}
				else {
					long zadnji_dolazak = korisnik.getZadnji_dolazak().getTime();
					vrijemePosao = new Select().all().from(VrijemePosao.class).where("pocetak = ?",zadnji_dolazak).executeSingle();
					if (!vrijemePosao.getKraj().equals(nula) || vrijemePosao.getPocetak().equals(nula)) {
						Date date = new Date();
						korisnik.setZadnji_dolazak(date);
						korisnik.save();
						vrijemePosao = new VrijemePosao (date,korisnik);
						vrijemePosao.save();
					}
				}
				
			}
			
		}catch (Exception e) {
			
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
