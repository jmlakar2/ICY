package hr.foi.air.icydemo.services;

import hr.foi.air.icydemo.core.MyLocationListener;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
/**
 * servis za praæenje promjene lokacije mobilnog ureðaja
 * @author Josip
 *
 */
public class LocationTracker extends Service{
	protected LocationManager locationManager;
	private static final long PROMJENA_UDALJENOSTI = 100;
	private static final long VRIJEME_PROMJENE = 1000;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand (Intent intent, int flags, int startId) {
		LocationListener locationListener = new MyLocationListener();
		
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(
				                LocationManager.GPS_PROVIDER,
				                PROMJENA_UDALJENOSTI,
				                VRIJEME_PROMJENE,
				                locationListener
				        );
		return Service.START_NOT_STICKY;
	}

}
