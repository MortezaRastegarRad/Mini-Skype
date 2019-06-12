package com.example.a2048.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.a2048.R;

public class SplashScreen extends Activity {

//    EditText email,onvan,payam;
//    Button send;
//    String myMail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splashscreen);

//        Thread T=new Thread(){ in ja ye tread baz mikone va on ro 5 saniye motevaqef mikone va bad miyad safhe jadid ro baz mikone
//
//
//            @Override
//            public void run() {
//
//                try {
//                    sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                finally {
//                    Intent GoToDetect =new Intent("android.intent.action.WELCOMEPAGE");
//                    startActivity(GoToDetect);
//                }
//
//            }
//
//
//        };
//        T.start();
//
//
//        init();
//
//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri uri = Uri.parse("mailto:\\" + email.getText().toString())
//                        .buildUpon()
//                        .appendQueryParameter("subject", onvan.getText().toString())
//                        .appendQueryParameter("body", payam.getText().toString())
//                        .appendQueryParameter("mailto",email.getText().toString())
//                        .build();
//
//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
//                startActivity(emailIntent);
//            }
//        });

//        Intent sendEmail=new Intent(Intent.ACTION_SENDTO);
//
//        sendEmail.setData(Uri.parse("mailto://emailAddress!!!"));
//        sendEmail.setType("message/rfc822");
//
//        sendEmail.putExtra(Intent.EXTRA_CC,myMail);
//        sendEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{email.getText().toString()});
//        sendEmail.putExtra(Intent.EXTRA_SUBJECT,onvan.getText().toString());
//        sendEmail.putExtra(Intent.EXTRA_TEXT,payam.getText().toString());
//
//        startActivity(sendEmail);
//                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
//
//                Uri data = Uri.parse("mailto:?subject=" + onvan.getText().toString()+ "&body=" + payam.getText().toString() + "&to=" + email.getText().toString());
//                mailIntent.setData(data);
//                startActivity(Intent.createChooser(mailIntent, "Send mail..."));


        new Handler().postDelayed(new Runnable() {//miyaim hamin tready ke roshim ro 5 saniye motevaqef mikonim va bad safhe jadid ro baz mikonim
            @Override
            public void run() {
                Intent GoToDetect =new Intent("android.intent.action.WELCOMEPAGE");
                startActivity(GoToDetect);
            }
        },5000);
        //farq in do ta ine ke vaqty thread jadid baz mikonim batryziyad try masraf mishe
    }
//    public void init(){
//        send=(Button) findViewById(R.id.send);
//        email=(EditText) findViewById(R.id.e1);
//        onvan=(EditText) findViewById(R.id.e2);
//        payam=(EditText) findViewById(R.id.e3);
//        myMail="mori.rad98@gmail.com";
//    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
