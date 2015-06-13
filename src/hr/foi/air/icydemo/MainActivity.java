package hr.foi.air.icydemo;

import hr.foi.air.icydemo.core.LoginLogic;

import hr.foi.air.icydemo.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		SharedPreferences myPref = getSharedPreferences ("zeljenaPrijava", 0);
		int prijava = myPref.getInt("prijava", 0);
		
		LoginLogic provjera = new LoginLogic(prijava);
		if (provjera.provjeraLogiran(this)) {
			Intent intent = new Intent(this, StartActivity.class);
			this.startActivity(intent);
		}
		
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    this.setContentView(R.layout.activity_main); 
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onClickLogin(View v)
	{
		Log.d(this.getClass().toString(), "onClickLogin");
		Intent intent = new Intent(this, LoginActivity.class);
		this.startActivity(intent);
		finish();
	}
	
	public void onClickNoAccount(View v)
	{
		Intent intent = new Intent(this, StartActivity.class);
		this.startActivity(intent);
	}
	
}
