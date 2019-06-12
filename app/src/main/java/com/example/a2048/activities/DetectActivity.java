package com.example.a2048.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2048.ClientPakage.Client;
import com.example.a2048.R;

import java.io.IOException;
import java.util.HashMap;

public class DetectActivity extends AppCompatActivity implements Client.UiInterface {
    static final int AUDIO_REQUEST_CODE = 12;

    // This textview is used to display swipe or tap status info.
    private TextView textView;

    // This is the gesture detector compat instance.
    private GestureDetectorCompat gestureDetectorCompat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the text view.
        textView = findViewById(R.id.detect_swipe_direction_textview);

        // Create a common gesture listener object.
        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener();

        // Set activity in the listener.
        gestureListener.setActivity(this);

        // Create the gesture detector with the gesture listener.
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);

        try {
            Client.getInstance().login("asd","asdasd");
            Client.getInstance().setUi(this);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[] {Manifest.permission.RECORD_AUDIO},AUDIO_REQUEST_CODE);
            } else {
                //initAudio()
            }
        } else{
            //initAudio()
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Pass activity on touch event to the gesture detector.
        gestureDetectorCompat.onTouchEvent(event);
        // Return true to tell android OS that event has been consumed, do not pass it to other event listeners.
        return true;
    }

    public void displayMessage(String message)
    {
        if(textView!=null)
        {
            // Display text in the text view.
            textView.setText(message);
        }
    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (AUDIO_REQUEST_CODE == requestCode){
//            if (grantResults.length>0){
//                //initAudio();
//            } else {
//                Toast.makeText(this,"Audio permission is needed for making calls",Toast.LENGTH_LONG).show();
//            }
//        } else
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    @Override
    public void Result(HashMap<String, Object> map) {

    }

    @Override
    public boolean isUiRunning() {
        return false;
    }
}


/*

 */