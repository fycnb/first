package com.example.administrator.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/11/18.
 */

public class registerActivity extends Activity {

    private Button mqx_button;
    private Button mqd_button;
    private EditText maccount;
    private EditText mpassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mqx_button = findViewById(R.id.qx_button);
        mqd_button = findViewById(R.id.qd_button);
        maccount = findViewById(R.id.raccount_Edit_Text);
        mpassword = findViewById(R.id.rpassword_Edit_Text);
        mqx_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        mqd_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String account = maccount.getText().toString().trim();
                final String password = mpassword.getText().toString().trim();
                new Thread(){
                    public void run(){
                        final String result = RegisterService.registerByGet(account,password);
                        if(result!=null){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(registerActivity.this,result,0).show();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(registerActivity.this,"请求失败",0).show();
                                }
                            });
                        }
                    }
                }.start();
            }
        });
    }

}
