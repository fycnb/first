package com.example.administrator.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button mregister_button;
    private Button mlogin_button;
    private EditText maccount;
    private EditText mpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mregister_button = findViewById(R.id.register_button);
        mlogin_button = findViewById(R.id.login_button);
        maccount = findViewById(R.id.account_edit_Text);
        mpassword = findViewById(R.id.password_edit_Text);
        mregister_button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"sdsd",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this,registerActivity.class);
                startActivity(i);
            }
        });
        mlogin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String account = maccount.getText().toString().trim();
                final String password = mpassword.getText().toString().trim();
                new Thread(){
                    public void run(){
                        final String result = LoginService.loginByGet(account,password);
                        if(result!=null){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,result,0).show();
                                }
                            });
                        } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this,"请求失败",0).show();
                                        }
                                    });
                                }
                    }
                }.start();
            }
        });
    }
}
