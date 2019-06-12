package com.example.a2048.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a2048.ClientPakage.Client;
import com.example.a2048.R;

import java.io.IOException;
import java.util.HashMap;

public class SigninActivity extends Activity implements View.OnClickListener, Client.UiInterface {

    Button back,next;
    TextView create,result;
    EditText name,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signin);


        init();

        back.setOnClickListener(this);
        next.setOnClickListener(this);
        create.setOnClickListener(this);

        try {
            Client.getInstance().setUi(this);
            //میاد این اکتیویتی رو میده به یو آی
            Client.getInstance().login(name.getText().toString(),password.getText().toString());
            //تو لوگین میایم فرایند ثبت نام رو انجام میدیم و اگر موفقیت امیز بود میایم یو آی رو تابعشو صدا میزنیم که تو هر اکتیویتی اورراید شده و عمل مخصوص گرافیک رو انجام میده
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //inja mortabet kardim be laye graphic
    @Override
    public void Result(final boolean isSuccess, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loginResult(isSuccess,message);
            }
        });
    }

    private void loginResult(boolean isSuccess, String message) {
        if (isSuccess){
            result.setText(message);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //starting next activity
                }
            },2000);
        }
    }

    private void init() {
        name=(EditText) findViewById(R.id.edit_name);
        password=(EditText) findViewById(R.id.edit_password);
        next=(Button) findViewById(R.id.next_btn);
        back=(Button) findViewById(R.id.back_btn);
        create =(TextView) findViewById(R.id.creat_account_txt);
        result=(TextView) findViewById(R.id.result_signin);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.creat_account_txt:
            case R.id.back_btn:
                Intent go_to_page_creat=new Intent("android.intent.action.CREATEACTIVITY");
                startActivity(go_to_page_creat);
                break;
            case  R.id.next_btn:
                //Client.getInstance().signin();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent go_to_page_create=new Intent("android.intent.action.CREATEACTIVITY");
        startActivity(go_to_page_create);
    }

    @Override
    public void Result(HashMap<String, Object> map) {

    }

    @Override
    public boolean isUiRunning() {
        return false;
    }
}
