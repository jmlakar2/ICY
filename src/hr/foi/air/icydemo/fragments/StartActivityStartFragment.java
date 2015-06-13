package hr.foi.air.icydemo.fragments;

import hr.foi.air.icydemo.core.LoginLogic;

import hr.foi.air.icydemo.R;

import android.support.v4.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class StartActivityStartFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
		return inflater.inflate(R.layout.activity_start_fragment_start, container, false);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	
	@Override
	public void onStart() {
		super.onStart();
		SharedPreferences myPref = getActivity().getSharedPreferences ("zeljenaPrijava", 0);
		int prijava = myPref.getInt("prijava", 0);
		
		LoginLogic provjera = new LoginLogic(prijava);
		if (!provjera.provjeraLogiran(this.getActivity())) 
			this.getView().findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
		else
			this.getView().findViewById(R.id.textView2).setVisibility(View.VISIBLE);
	}
}
