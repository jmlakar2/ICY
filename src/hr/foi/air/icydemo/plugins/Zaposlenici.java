package hr.foi.air.icydemo.plugins;

import hr.foi.air.icydemo.core.ZaposServiceAsyncTask;
import hr.foi.air.icydemo.interfaces.IZaposlenici;
import hr.foi.air.icydemo.types.ZaposleniciReturnType;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Zaposlenici implements IZaposlenici{

	@Override
	public List<ZaposleniciReturnType> vratiZaposlenike(String user, String kod) {
		String rezultat = dohvatiJson (user, kod);
		List<ZaposleniciReturnType> parsiraniRezultat = new ArrayList<ZaposleniciReturnType>();
		parsiraniRezultat = parsirajJson (rezultat);
		return parsiraniRezultat;
	}
	
	/**
	 * metoda za primanje podataka o zaposlenicima sa servisa
	 * 
	 * @param user
	 * @param kod
	 * @return json string dobiven sa servisa
	 */
	public String dohvatiJson (String user, String kod) {
		
		ZaposServiceAsyncTask asyncTask = new ZaposServiceAsyncTask();
		asyncTask.execute(new Object[] {user, kod});
		String jsonResult = "[]";
		try {
			jsonResult = (String) ((Object[])asyncTask.get())[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonResult;
	}
	
	/**
	 * 
	 * @param jsonPod json string
	 * @return rezultat parsirani podaci iz json stringa
	 */
	private List<ZaposleniciReturnType> parsirajJson (String jsonPod){
		List<ZaposleniciReturnType> rezultat = new ArrayList<ZaposleniciReturnType>();
		try {
			JSONArray zaposlenici = new JSONArray(jsonPod);
			
			for (int i = 0; i < zaposlenici.length(); i++){
				JSONObject zaposlenik = zaposlenici.getJSONObject(i);
				String ime = zaposlenik.getString("ime");
				String prezime = zaposlenik.getString("prezime");
				String e_mail = zaposlenik.getString("e_mail");
				String kontakt = zaposlenik.getString("kontakt");
				String radno_mjesto = zaposlenik.getString("radno_mjesto");
				
				ZaposleniciReturnType zaposlenikReturn = new ZaposleniciReturnType(ime, prezime, e_mail, kontakt, radno_mjesto);
				rezultat.add(zaposlenikReturn);	
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return rezultat;
	}

}
