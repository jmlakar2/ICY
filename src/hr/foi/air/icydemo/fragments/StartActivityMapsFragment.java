package hr.foi.air.icydemo.fragments;

import java.util.List;

import android.app.Dialog;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

//public class MapsActivity extends ActionBarActivity implements OnMyLocationChangeListener {
public class StartActivityMapsFragment extends SupportMapFragment {
	
	private GoogleMap googleMap = null;
//	private Location myLocation = null;
	
	@Override
	public void onStart() {
		super.onStart();
		
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.getActivity().getBaseContext());
		
		if(status != ConnectionResult.SUCCESS)
		{
			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this.getActivity(), requestCode);
			dialog.show();
		}
		else
		{			
//			this.googleMap = ((SupportMapFragment) this.getFragmentManager().findFragmentById(R.id.fragment_google_maps)).getMap();
			this.googleMap = this.getMap();
			this.googleMap.setMyLocationEnabled(true);
//			this.googleMap.setOnMyLocationChangeListener(this);
//			this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
			
			Location myLocation = null;
			LocationManager locationManager = (LocationManager) this.getActivity().getSystemService(ActionBarActivity.LOCATION_SERVICE);
			List<String> providers = locationManager.getProviders(true);
//			Location myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			for(String provider : providers)
			{
				myLocation = locationManager.getLastKnownLocation(provider);
				if(myLocation != null)
				{
					break;
				}
			}
			
			if(myLocation != null)
			{
				LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
				CameraUpdate update = CameraUpdateFactory.newLatLngZoom(myLatLng, 16); // zoom: between 0-21
				this.googleMap.animateCamera(update);
			}
			
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.getActivity().getBaseContext());
//		
//		if(status != ConnectionResult.SUCCESS)
//		{
//			int requestCode = 10;
//			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this.getActivity(), requestCode);
//			dialog.show();
//		}
//		else
//		{			
//			this.googleMap = ((MapFragment) this.getFragmentManager().findFragmentById(R.id.activity_start_fragment_maps)).getMap();
//			this.googleMap.setMyLocationEnabled(true);
////			this.googleMap.setOnMyLocationChangeListener(this);
////			this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
//			
//			Location myLocation = null;
//			LocationManager locationManager = (LocationManager) this.getActivity().getSystemService(ActionBarActivity.LOCATION_SERVICE);
//			List<String> providers = locationManager.getProviders(true);
////			Location myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//			for(String provider : providers)
//			{
//				myLocation = locationManager.getLastKnownLocation(provider);
//				if(myLocation != null)
//				{
//					break;
//				}
//			}
//			
//			if(myLocation != null)
//			{
//				LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
//				CameraUpdate update = CameraUpdateFactory.newLatLngZoom(myLatLng, 16); // zoom: between 0-21
//				this.googleMap.animateCamera(update);
//			}
//			
//		}
	}

//	@Override
//	public void onMyLocationChange(Location myLocation) {
////		LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
////		CameraUpdate update = CameraUpdateFactory.newLatLng(myLatLng);
////		this.googleMap.animateCamera(update);
//		this.myLocation = myLocation;
//	}
}
