package com.projeman.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.projeman.adapters.BookListAdapter;
import com.projeman.library.R;

public class BooksList extends Activity {
	
	private static final String TAG = "MainActivity";
	
	private ProgressBar progressBar;
	private ListView listView;
	private Button btnMenu;
	
	private List<Books> bookList;
	private BookListAdapter adapter;
		
	public String fonts;
	
	private Typeface face;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.books_list);
		
		Bundle bundle = getIntent().getExtras();
		fonts = bundle.getString("font");
		Log.i(TAG, fonts);	
		
		progressBar = (ProgressBar) findViewById(R.id.actionbar_progress);
        progressBar.setVisibility(View.INVISIBLE);
        
        listView = (ListView) findViewById(R.id.listViewBooks);
        listView.setVisibility(View.INVISIBLE);
        
        
        // go to menu
        btnMenu = (Button) findViewById(R.id.btn_menu_compound);
                        
		btnMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				Intent intent = new Intent(BooksList.this, MainMenu.class);
				startActivity(intent);
			}
		});
        		
     // Get Data
		getDataFromInternet();	
        
	}
		
	//---------------------------------------------------------------
		
	@SuppressLint("NewApi")
	private void getDataFromInternet() {
		if(getConnectionStatus()) {
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
				new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[]) null);
			else
				new MyAsyncTask().execute();
		}
	}
	
	private boolean getConnectionStatus() {
    	boolean found = false;
    	
    	ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnected()) {		    	
	    	found = true;
	    }
	    
	    return found;
    }
	
	
	//My AsyncTask
	
	public class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {
        
		@Override
	    protected void onPreExecute() {
			Log.i(TAG, "myAsyncTask is about to start...");
			
			btnMenu.setVisibility(View.INVISIBLE);
			progressBar.setVisibility(View.VISIBLE);
	    }
		
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean status = false;	
			
			bookList = getLatestBookItems();
			
			// Check categoryList content
			for(Books str: bookList)
				Log.i(TAG, str.toString());
			
			if(bookList != null)
				status = true;
				
			return status;
		}

		@Override
	    protected void onPostExecute(Boolean result) {
			Log.i(TAG, "myAsyncTask finished its task.");
			
			progressBar.setVisibility(View.INVISIBLE);
			btnMenu.setVisibility(View.VISIBLE);
			listView.setVisibility(View.VISIBLE);
			
			if(result)
				displayData();
	    }
	}
	
	private List<Books> getLatestBookItems() {
		List<Books> list = new ArrayList<Books>();
		String connection = Books.URL;
		
		try {
			URL url = new URL(connection);
			Log.i(TAG, "Try to open: " + connection);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
			int responseCode = conn.getResponseCode();
			Log.i(TAG, "Response code is: " + responseCode);
			
			if(responseCode != HttpURLConnection.HTTP_OK) {
				Log.e(TAG, "Couldn't open connection in getLatestKategoriItems()");
				return null;
			}
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			if (in != null) {
				StringBuilder strBuilder = new StringBuilder();
				// Read character by character    			
				int ch = 0;
				while ((ch = in.read()) != -1)
					strBuilder.append((char) ch);
				    			
				// get returned message and show it
				String response = strBuilder.toString();
				Log.i("JSON returned by server:", response);
				    			
				// Parse JSON String
				JSONObject jObject = new JSONObject(response);
				JSONArray jObjects = jObject.getJSONArray("posts");
				for(int i=0; i<jObjects.length(); i++){
					Books book = new Books();
							
					book.setId(jObjects.getJSONObject(i).getString("id"));
					book.setBookTitle(jObjects.getJSONObject(i).getString("title"));
					book.setBookDate(jObjects.getJSONObject(i).getString("year"));
					book.setBookImageURL(jObjects.getJSONObject(i).getString("image"));
					book.setBookPublisher(jObjects.getJSONObject(i).getString("author"));					
					list.add(book);
				}
			}
			    
			in.close();
						
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private void displayData() {
    	Log.i(TAG, "Inside display data...");
    	
    	face = Typeface.createFromAsset(getAssets(), "font/"+fonts+"");
    	
    	adapter = new BookListAdapter(getApplicationContext(), bookList, face);    	
    	listView.setAdapter(adapter);    
    
    }	
		
}