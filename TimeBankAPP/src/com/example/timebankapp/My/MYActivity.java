package com.example.timebankapp.My;

import com.example.timebankapp.LoginActivity;
import com.example.timebankapp.R;
import com.example.timebankapp.SharedPrefUtility;
import com.example.timebankapp.R.id;
import com.example.timebankapp.R.layout;
import com.example.timebankapp.R.menu;

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

		setContentView(R.layout.activity_my);
	
	}
	
}
