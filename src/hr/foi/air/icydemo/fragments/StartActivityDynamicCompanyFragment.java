package hr.foi.air.icydemo.fragments;

import hr.com.air.icylib1.implement.ZaposleniciTest;
import hr.foi.air.icydemo.core.LoginLogic;
import hr.foi.air.icydemo.interfaces.IZaposlenici;
import hr.foi.air.icydemo.plugins.Zaposlenici;
import hr.foi.air.icydemo.types.ZaposleniciReturnType;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.icydemo.R;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class StartActivityDynamicCompanyFragment extends Fragment{

	private ListView listView ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
		return inflater.inflate(R.layout.list_view_layout, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		podaciShow();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	/**
	 * metoda za dinamièki prikaz podataka o zaposlenicima
	 */
	private void podaciShow () {
		
		SharedPreferences myPref = getActivity().getSharedPreferences ("zeljenaPrijava", 0);
		int prijava = myPref.getInt("prijava", 0);
		LoginLogic logic = new LoginLogic(prijava);
		ArrayList<String> podaci = logic.vratiLokalnePodatke(this.getActivity());
		String user = podaci.get(0);
		String kod = podaci.get(1);
		
		IZaposlenici zaposlenici = new Zaposlenici();
		List<ZaposleniciReturnType> listaZaposlenici = new ArrayList<ZaposleniciReturnType>();
		listaZaposlenici = zaposlenici.vratiZaposlenike(user, kod);
		
		if (listaZaposlenici.isEmpty())
		{
			IZaposlenici z = new ZaposleniciTest();
			listaZaposlenici = z.vratiZaposlenike(user, kod);
		}
        
        listView = (ListView) this.getActivity().findViewById(R.id.list);
        ArrayList<String> ispis = new ArrayList<String>();
        
        for (int i = 0; i < listaZaposlenici.size(); i++) {
        	ispis.add("Ime: "+listaZaposlenici.get(i).getIme()
        			+"\nPrezime: "+listaZaposlenici.get(i).getPrezime()
        			+"\nE-mail: "+listaZaposlenici.get(i).getE_mail()
        			+"\nKontakt: "+listaZaposlenici.get(i).getKontakt()
        			+"\nRadno mjesto: "+listaZaposlenici.get(i).getRadno_mjesto()
        			);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
          R.layout.custom_txt_view, ispis);

        listView.setAdapter(adapter); 
	}
	/*
	public void onClickRefresh(View v)
	{
		podaciShow();
	}*/
	
}
