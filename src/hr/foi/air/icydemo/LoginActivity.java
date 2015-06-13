package hr.foi.air.icydemo;

import hr.foi.air.icydemo.core.LoginLogic;

import hr.foi.air.icydemo.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
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
	
	public void onClickSubmit(View v)
	{
		LoginLogic rezultat = new LoginLogic(1);
		
		SharedPreferences korPod = getSharedPreferences ("zeljenaPrijava", 0);
		SharedPreferences.Editor editor = korPod.edit();
		editor.putInt("prijava", 1);
		editor.commit();
		
		EditText tekst = (EditText)findViewById(R.id.korIme);
		String user = tekst.getText().toString();
		tekst = (EditText)findViewById(R.id.lozinka);
		String pass = tekst.getText().toString();
		if (rezultat.provjeraPodaci(user, pass, this)) {
			
			Intent intent = new Intent(this, StartActivity.class);
			this.startActivity(intent);
			
			finish();
		}
		else {
			Toast.makeText(getApplicationContext(), getString(R.string.wrong_auth), Toast.LENGTH_LONG).show();
		}
	}
	
	public void onClickTest (View v)
	{
		LoginLogic rezultat = new LoginLogic(2);
		
		if (rezultat.provjeraPodaci("x","x", this)) {
		
			SharedPreferences korPod = getSharedPreferences ("zeljenaPrijava", 0);
			SharedPreferences.Editor editor = korPod.edit();
			editor.putInt("prijava", 2);
			editor.commit();
			Intent intent = new Intent(this, StartActivity.class);
			this.startActivity(intent);
			
			finish();
		
		}
		else {
			Toast.makeText(getApplicationContext(), getString(R.string.wrong_auth), Toast.LENGTH_LONG).show();
		}
	}
	
	public void onBackPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		this.startActivity(intent);
	}
}
