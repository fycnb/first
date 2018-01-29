package com.example.timebankapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MYActivity extends Activity {
	private Button btnexitlogin;
	private Boolean isLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 isLogin = (Boolean) SharedPrefUtility.getParam(MYActivity.this, SharedPrefUtility.IS_LOGIN,false);
		                      
		if(isLogin == true){
		setContentView(R.layout.activity_my);
		btnexitlogin = (Button) findViewById(R.id.exitlogin);
		btnexitlogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPrefUtility.setParam(MYActivity.this, SharedPrefUtility.IS_LOGIN, false);
				SharedPrefUtility.removeParam(MYActivity.this, SharedPrefUtility.LOGIN_DATA);
				Intent intent = new Intent(MYActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		}else{
			Intent intent = new Intent(MYActivity.this,LoginActivity.class);
			startActivity(intent);
		}
		
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my, menu);
		return true;
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
