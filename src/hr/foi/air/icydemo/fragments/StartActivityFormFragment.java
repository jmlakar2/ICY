package hr.foi.air.icydemo.fragments;


import hr.foi.air.icydemo.db.Korisnici;

import com.activeandroid.query.Select;
import hr.foi.air.icydemo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

public class StartActivityFormFragment extends Fragment {
	
	private Korisnici korisnik;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
		View v = inflater.inflate(R.layout.activity_start_fragment_form, container, false);
		korisnik = new Select().all().from(Korisnici.class).executeSingle();
		if (korisnik != null) {
			napuniFormu(v);
		}
		return v;
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	private void napuniFormu (View v) {
		EditText tekst = (EditText)v.findViewById(R.id.editSatnica);
		tekst.setText(String.valueOf(korisnik.getSatnica()));
		tekst = (EditText)v.findViewById(R.id.editRadno);
		tekst.setText(String.valueOf(korisnik.getRadno_vrijeme()));
		tekst = (EditText)v.findViewById(R.id.editPauza);
		tekst.setText(String.valueOf(korisnik.getPauza()));
		tekst = (EditText)v.findViewById(R.id.editPrirez);
		tekst.setText(String.valueOf(korisnik.getPrirez()));
		
		Spinner spinner = (Spinner)v.findViewById(R.id.spinnerUzdrzavani);
		spinner.setSelection(korisnik.getBr_uzdrzavani_clanovi()-1);
		spinner = (Spinner)v.findViewById(R.id.spinnerDjeca);
		spinner.setSelection(korisnik.getBr_djeca()-1);
		spinner = (Spinner)v.findViewById(R.id.spinnerInvalid);
		spinner.setSelection(korisnik.getRazina_invalidnost()-1);
		spinner = (Spinner)v.findViewById(R.id.spinnerPPDS);
		spinner.setSelection(korisnik.getSkupina_PPDS()-1);
		spinner = (Spinner)v.findViewById(R.id.spinnerBrdo);
		spinner.setSelection(korisnik.getBP_podrucje()-1);
		spinner = (Spinner)v.findViewById(R.id.spinnerUmirov);
		spinner.setSelection(korisnik.getBr_umirovljenik()-1);
	}
}
