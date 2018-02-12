package com.example.timebankapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	 private EditText etaccount;
	 private EditText etPassword;
	 private Button btnlogin;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		
		etaccount = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnlogin = (Button)findViewById(R.id.clicklogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                final String account = etaccount.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();
               
                new Thread(){
                    @Override
					public void run(){
                        final String result = LoginService.LoginByPost(account,password);
//                        System.out.println(result);
                        if(result != null) {
                            if (result.equals("µ«¬º≥…π¶")) {
                            	 SharedPrefUtility sp = new SharedPrefUtility();
                                 SharedPrefUtility.setParam(LoginActivity.this, SharedPrefUtility.IS_LOGIN, true);
                                 SharedPrefUtility.setParam(LoginActivity .this, SharedPrefUtility.LOGIN_DATA,result);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, result, 0).show();
                                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        
                                    }
                                });
                            } else {
                                //«Î«Û ß∞‹£¨µØ≥ˆtoast
                                runOnUiThread(new Runnable() {
                                  
                                    @Override
									public void run() {
                                        Toast.makeText(LoginActivity.this, "’À∫≈ªÚ√‹¬Î¥ÌŒÛ", 0).show();

                                    }
                                });
                            }
                        }
                    }
                }.start();
            }
        });

	}


}
