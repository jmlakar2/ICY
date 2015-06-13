package hr.foi.air.icydemo;

import hr.com.air.icylib1.implement.ZaposleniciTest;
import hr.foi.air.icydemo.core.LoginLogic;
import hr.foi.air.icydemo.core.Placa;
import hr.foi.air.icydemo.db.Korisnici;
import hr.foi.air.icydemo.db.VrijemePosao;
import hr.foi.air.icydemo.fragments.StartActivityDynamicCompanyFragment;
import hr.foi.air.icydemo.fragments.StartActivityFormFragment;
import hr.foi.air.icydemo.fragments.StartActivityMapsFragment;
import hr.foi.air.icydemo.fragments.StartActivityStartFragment;
import hr.foi.air.icydemo.fragments.StartActivityUserFragment;
import hr.foi.air.icydemo.interfaces.IZaposlenici;
import hr.foi.air.icydemo.plugins.Zaposlenici;
import hr.foi.air.icydemo.services.LocationTracker;
import hr.foi.air.icydemo.types.ZaposleniciReturnType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.activeandroid.query.Select;

import hr.foi.air.icydemo.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends ActionBarActivity {
	private Korisnici korisnik = new Select().all().from(Korisnici.class).executeSingle();
	
	private static int NUM_PAGES = 4; 
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		startService(new Intent(getBaseContext(), LocationTracker.class));
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		SharedPreferences myPref = getSharedPreferences ("zeljenaPrijava", 0);
		int prijava = myPref.getInt("prijava", 0);
		
		LoginLogic provjera = new LoginLogic(prijava);
		
		if (!provjera.provjeraLogiran(this)) {
			setContentView(R.layout.activity_start_no);
			StartActivity.NUM_PAGES = 4;
		}
		else {
			setContentView(R.layout.activity_start);
			StartActivity.NUM_PAGES = 5; //5
		}
		
		mPager = (ViewPager) findViewById(R.id.view_pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        
	}
	
	/**
	 * osluškivaè za gumb iz StartActivityFormFragments fragmenta... ažuriraju/unose se podaci o korisniku u bazu
	 * @param v
	 */
	public void onClickSubmit (View v) {
		try {
			EditText tekst = (EditText)findViewById(R.id.editSatnica);
			float satnica = Float.parseFloat(tekst.getText().toString());
			tekst = (EditText)findViewById(R.id.editRadno);
			int radnoVrijeme = Integer.parseInt(tekst.getText().toString());
			tekst = (EditText)findViewById(R.id.editPauza);
			int pauza = Integer.parseInt(tekst.getText().toString());
			tekst = (EditText)findViewById(R.id.editPrirez);
			float prirez = Float.parseFloat(tekst.getText().toString());
			
			Spinner spinner = (Spinner)findViewById(R.id.spinnerUzdrzavani);
			int uzdrzavani = spinner.getSelectedItemPosition() + 1;
			spinner = (Spinner)findViewById(R.id.spinnerDjeca);
			int djeca = spinner.getSelectedItemPosition();
			spinner = (Spinner)findViewById(R.id.spinnerInvalid);
			int invalid = spinner.getSelectedItemPosition();
			spinner = (Spinner)findViewById(R.id.spinnerPPDS);
			int ppds = spinner.getSelectedItemPosition();
			spinner = (Spinner)findViewById(R.id.spinnerBrdo);
			int brdo = spinner.getSelectedItemPosition();
			spinner = (Spinner)findViewById(R.id.spinnerUmirov);
			int umirov = spinner.getSelectedItemPosition();
			
			korisnik = new Select().all().from(Korisnici.class).executeSingle();
			
			if (korisnik != null) {
				Korisnici newKorisnici = new Korisnici (1,satnica,radnoVrijeme,pauza,prirez,uzdrzavani,
						djeca,invalid,ppds,brdo,umirov);
				korisnik.updateKorisnci(newKorisnici);
				Toast.makeText(getApplicationContext(), getString(R.string.azuriranje_uspjeh), Toast.LENGTH_LONG).show();
			}
			else {
				korisnik = new Korisnici (1,satnica,radnoVrijeme,pauza,prirez,uzdrzavani,
						djeca,invalid,ppds,brdo,umirov);
				korisnik.save();
				Toast.makeText(getApplicationContext(), getString(R.string.baza_uspjeh), Toast.LENGTH_LONG).show();
			}
		}
		catch (Exception e){
			Toast.makeText(getApplicationContext(), getString(R.string.pogreska), Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	 * osluškivaè za gumb iz StartActivityFormFragments fragmenta... u bazu se unose podaci o trenutnoj korisnikovoj lokaciji
	 * @param v
	 */
	public void onClickLocation (View v) {
		try {
			korisnik = new Select().all().from(Korisnici.class).executeSingle();
			if (korisnik != null) {
				LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
				Location lokacija = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				korisnik.setRadno_mjesto_x(lokacija.getLongitude());
				korisnik.setRadno_mjesto_y(lokacija.getLatitude());
				korisnik.save();
				Toast.makeText(getApplicationContext(), getString(R.string.lokacija_uspjeh), Toast.LENGTH_LONG).show();
				Date date = new Date();
				korisnik.setZadnji_dolazak(date);
				korisnik.save();
				VrijemePosao vrijemePosao = new VrijemePosao (date,korisnik);
				vrijemePosao.save();
			}
			else {
				Toast.makeText(getApplicationContext(), getString(R.string.lokacija_neuspjeh), Toast.LENGTH_LONG).show();
			}
		}
		catch(Exception e) {
			Toast.makeText(getApplicationContext(), getString(R.string.no_GPS), Toast.LENGTH_LONG).show();
		}

	}
	
	/**
	 * osluškivaè za gumb iz StartActivityUserFragments fragmenta... raèuna se i prikazuje korisnikova neto plaæa i odraðeni sati za odabrani mjesec
	 * @param v
	 */
	public void onClickPrikazi (View v) {
		
		Spinner spinner = (Spinner)findViewById(R.id.spinnermjeseci);
		int mjesec = spinner.getSelectedItemPosition();
		
		Placa placa = new Placa();
		
		korisnik = new Select().all().from(Korisnici.class).executeSingle();
		
		if (korisnik != null) {
			double[] rezultat = new double[2];
			rezultat = placa.getPlaca(mjesec, korisnik);
			DecimalFormat df = new DecimalFormat("#.##");
			TextView t = (TextView)findViewById(R.id.editIzvjestaj);
			t.setText(String.valueOf(df.format(rezultat[1]))+ " kn");
			t = (TextView)findViewById(R.id.editSati);
			t.setText(String.valueOf(df.format(rezultat[0]))+ " hr");
		}
		else {
			Toast.makeText(getApplicationContext(), getString(R.string.lokacija_neuspjeh), Toast.LENGTH_LONG).show();
		}
	}
	
	public void onClickForm(View v)
	{	
		this.mPager.setCurrentItem(1);
	}
	
	public void onClickUser(View v)
	{
		this.mPager.setCurrentItem(2);
	}
	
	public void onClickMaps(View v)
	{
		this.mPager.setCurrentItem(3);
	}
	
	public void onClickCompany(View v)
	{
		this.mPager.setCurrentItem(4);
	}
	
	@Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
	
	public void onClickLogout(View v)
	{
		SharedPreferences myPref = getSharedPreferences ("zeljenaPrijava", 0);
		int prijava = myPref.getInt("prijava", 0);
		LoginLogic odjava = new LoginLogic(prijava);
		odjava.obrisiKorisnik(this);
		
		Toast.makeText(getApplicationContext(), getString(R.string.odjava), Toast.LENGTH_LONG).show();
		
		Intent intent = new Intent(this, MainActivity.class);
		this.startActivity(intent);
	}
	
	private void podaciShow () {
		
		SharedPreferences myPref = getSharedPreferences ("zeljenaPrijava", 0);
		int prijava = myPref.getInt("prijava", 0);
		LoginLogic logic = new LoginLogic(prijava);
		ArrayList<String> podaci = logic.vratiLokalnePodatke(this);
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
        
		ListView listView = (ListView) this.findViewById(R.id.list);
        ArrayList<String> ispis = new ArrayList<String>();
        
        for (int i = 0; i < listaZaposlenici.size(); i++) {
        	ispis.add("Ime: "+listaZaposlenici.get(i).getIme()
        			+"\nPrezime: "+listaZaposlenici.get(i).getPrezime()
        			+"\nE-mail: "+listaZaposlenici.get(i).getE_mail()
        			+"\nKontakt: "+listaZaposlenici.get(i).getKontakt()
        			+"\nRadno mjesto: "+listaZaposlenici.get(i).getRadno_mjesto()
        			);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          R.layout.custom_txt_view, ispis);

        listView.setAdapter(adapter); 
	}
	
	public void onClickRefresh(View v)
	{
		this.podaciShow();
	}
	
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	switch (position) {
			case 0:
				return new StartActivityStartFragment();
			case 1:
				return new StartActivityFormFragment();
			case 2:
				return new StartActivityUserFragment();
			case 3:
				return new StartActivityMapsFragment();
			case 4:
				return new StartActivityDynamicCompanyFragment();
			default:
				return new StartActivityStartFragment();
			}
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
	
}
