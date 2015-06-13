package hr.foi.air.icydemo.core;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;



import android.os.AsyncTask;

public class ZaposServiceAsyncTask extends AsyncTask<Object, Void, Object>{

	@Override
	protected Object doInBackground(Object... params) {
		String user = (String) params[0];
		String kod = (String) params[1];
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet (
				"http://178.62.195.200/icy/servis/zaposlenici.php?user=" + user + "&kod=" + kod
				);
		ResponseHandler<String> handler = new BasicResponseHandler();
		String jsonResult = "[]";
		try {
			jsonResult = client.execute(request, handler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		client.getConnectionManager().shutdown();
		
		Object[] rezultat = new Object [] {jsonResult};
		
		return rezultat;
	}

}
