package com.example.a2048.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a2048.R;

public class WelcomeActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcomep_page);


        Button letsGoBTN = findViewById(R.id.Lets_go_btn);


        letsGoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Go_to_page_three = new Intent(WelcomeActivity.this, SigninOrCreate_page.class);

                startActivity(Go_to_page_three);
            }

        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //
        finish();
    }
}
