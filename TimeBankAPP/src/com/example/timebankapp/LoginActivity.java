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
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                final String account = etaccount.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();

                new Thread(){
                    @Override
					public void run(){
                        final String result = LoginService.LoginByPost(account,password);
//                        System.out.println(result);
                        if(result != null) {
                            if (result.equals("��¼�ɹ�")) {
                                
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, result, 0).show();
                                      
                                        
                                    }
                                });
                            } else {
                                //����ʧ�ܣ�����toast
                                runOnUiThread(new Runnable() {
                                  
                                    @Override
									public void run() {
                                        Toast.makeText(LoginActivity.this, "�˺Ż��������", 0).show();

                                    }
                                });
                            }
                        }
                    }
                }.start();
            }
        });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
