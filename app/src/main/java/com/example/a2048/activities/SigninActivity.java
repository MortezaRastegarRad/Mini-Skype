package com.example.a2048.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a2048.ClientPakage.Client;
import com.example.a2048.R;

import java.io.IOException;

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

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.creat_account_txt:
                Intent go_to_page_creat=new Intent("android.intent.action.CREATEACTIVITY");
                startActivity(go_to_page_creat);
                break;
            case R.id.back_btn:
                Intent go_to_page_SigninOrCreate=new Intent("android.intent.action.SIGNINORCREATE");
                startActivity(go_to_page_SigninOrCreate);
                break;
            case  R.id.next_btn:
                try {
                    //میاد این اکتیویتی رو میده به یو آی
                    Client.getInstance().setUi(this);
                    Client.getInstance().Login(name.getText().toString(),password.getText().toString());
                    //تو لوگین میایم فرایند ثبت نام رو انجام میدیم و اگر موفقیت امیز بود میایم یو آی رو تابعشو صدا میزنیم که تو هر اکتیویتی اورراید شده و عمل مخصوص گرافیک رو انجام میده
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    //inja mortabet kardim be laye graphic
    @Override
    public void Result(final boolean isSuccess, final String message) {
        result.setText(message);

        if (isSuccess){
            result.setTextColor(ContextCompat.getColor(this,R.color.success));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent go_to_page_Menu = new Intent();
                    startActivity(go_to_page_Menu);

                }

            },1000);
        }else{
            result.setTextColor(ContextCompat.getColor(this,R.color.failed));
        }
    }

    @Override
    public boolean isUiRunning() {
        return true;
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
    protected void onPause() {
        super.onPause();
        Intent go_to_page_create=new Intent("android.intent.action.CREATEACTIVITY");
        startActivity(go_to_page_create);
    }
}
