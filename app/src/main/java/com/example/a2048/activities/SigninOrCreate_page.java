package com.example.a2048.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a2048.R;

public class SigninOrCreate_page extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signin_or_create);

        Button SigninBTN=findViewById(R.id.signin_create_btn);

        SigninBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage=new Intent("android.intent.action.CREATEACTIVITY");

                startActivity(nextPage);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

