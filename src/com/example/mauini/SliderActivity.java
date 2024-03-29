package com.example.mauini;

import java.util.regex.Pattern;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SliderActivity extends ActionBarActivity {

	int serverResponseCode = 0;
	String upLoadServerUri = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slider);
		
		

		final Intent intent = new Intent(this, AddProduct.class);

		final EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		final EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);

		Button logInButton = (Button) findViewById(R.id.logInButton);

		upLoadServerUri = "https://mauini.staging.wpengine.com/wc-api/v1/orders?status=completed&consumer_key=ck_58b7fc2888bfb2af86721ba4e305c699&consumer_secret=cs_c5f3ba30001304858ba447c2f1440d65";

		final HttpGet httpGet = new HttpGet(upLoadServerUri); // create new
																// httpGet
																// object
		final HttpClient httpclient = new DefaultHttpClient(); // create new
																// httpClient

		// httpGet.setHeader("consumer_key","ck_58b7fc2888bfb2af86721ba4e305c699");
		// httpGet.setHeader("consumer_secret","cs_c5f3ba30001304858ba447c2f1440d65");

		logInButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String username = usernameEditText.getText().toString();
				String password = passwordEditText.getText().toString();
				
				if (!validEmail(username)) {
					Toast.makeText(SliderActivity.this,"Enter valid e-mail",Toast.LENGTH_LONG).show();
				}
				else if(password.length() == 0)
				{
					Toast.makeText(SliderActivity.this,"Enter password",Toast.LENGTH_LONG).show();
				}
				else
				{
					System.out.println("Email: "+username);
					System.out.println("password: "+password);
					if(username.equals("admin@admin.com") && password.equals("admin"))
					{
						startActivity(intent);
					}
					else
					{
						Toast.makeText(SliderActivity.this,"Wrong Credential",Toast.LENGTH_LONG).show();
					}
				}

//				new Thread() {
//					@Override
//					public void run() {
//
//						try {
//
//							HttpResponse response;
//							response = httpclient.execute(httpGet); // execute
//																	// httpGet
//							StatusLine statusLine = response.getStatusLine();
//							int statusCode = statusLine.getStatusCode();
//
//							System.out.println("status Code: " + statusCode);
//
//							Log.i("Code", statusCode + "");
//							
//							//this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
//
//							startActivity(intent);
//							
//							
////							overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
//							
//							
////							Looper.prepare();
////							new Handler().postDelayed(new Runnable() { 
////						         public void run() { 		        	 
////						        	 startActivity(intent);
////						             overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
////						         } 
////						         
////						    }, 3000);
////
////							Looper.loop();
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//
//					}
//				}.start();

				// String username = usernameEditText.getText().toString();
				// String password = passwordEditText.getText().toString();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.slider, menu);
		return true;
	}
	
	private boolean validEmail(String email) {
	    Pattern pattern = Patterns.EMAIL_ADDRESS;
	    return pattern.matcher(email).matches();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
