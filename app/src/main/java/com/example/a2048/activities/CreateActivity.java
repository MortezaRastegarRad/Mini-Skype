package com.example.a2048.activities;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a2048.ClientPakage.Client;
import com.example.a2048.R;

import java.io.IOException;
import java.util.HashMap;

public class CreateActivity extends Activity implements View.OnClickListener,Client.UiInterface{

    EditText name,password;
    Button next,back;
    TextView signin,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create);

        init();

        next.setOnClickListener(this);
        back.setOnClickListener(this);
        signin.setOnClickListener(this);


//        createNotification();


    }

    private void createNotification() {

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("test","push notifications", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);

            NotificationChannelGroup group = new NotificationChannelGroup("test","messages");
            manager.createNotificationChannelGroup(group);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"test");

        builder.setContentTitle("message from ali")
                .setContentText("hello!!")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setColor(Color.BLUE)
                .addAction(R.drawable.ic_launcher_foreground,"mark as read",null);

        manager.notify(10,builder.build());

    }

    public void init(){
        name=(EditText) findViewById(R.id.edit_name_txt);
        password=(EditText) findViewById(R.id.edit_password_txt);
        next=(Button) findViewById(R.id.next_btnc);
        back=(Button) findViewById(R.id.back_btnc);
        signin=(TextView)findViewById(R.id.signin_tx);
        result=(TextView) findViewById(R.id.result_create);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.back_btnc:

                Intent back_to_previous_page=new Intent("android.intent.action.SIGNINORCREATE");
                startActivity(back_to_previous_page);
                break;
            case R.id.next_btnc:
                try {

                    Client.getInstance().setUi(this);
                    Client.getInstance().create(name.getText().toString(),password.getText().toString());

                } catch (IOException e) {

                    e.printStackTrace();

                }
                break;
            case R.id.signin_tx:
                Intent Go_to_page_signinActivity=new Intent("android.intent.action.SIGNINACTIVITY");
                startActivity(Go_to_page_signinActivity);
                break;
        }
    }

    public void Signin(boolean isSuccess,String message){
        result.setText(message);
        if(isSuccess){
            result.setTextColor(ContextCompat.getColor(this,R.color.success));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent go_to_page_menu=new Intent();//ye action bayad bedimesh
                    startActivity(go_to_page_menu);

                }
            },1000);
        }
        else {
            result.setTextColor(ContextCompat.getColor(this,R.color.failed));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent go_to_page_signinOrCreate=new Intent("android.intent.action.SIGNINORCREATE");
        startActivity(go_to_page_signinOrCreate);
    }

    @Override
    public void Result(HashMap<String, Object> map) {

    }

    @Override
    public boolean isUiRunning() {
        return isFinishing();
    }
}
