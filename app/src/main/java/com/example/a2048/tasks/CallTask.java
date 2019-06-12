package com.example.a2048.tasks;

import android.os.AsyncTask;

public class CallTask extends AsyncTask<String,Integer,Boolean> {
    public CallTask(){

    }

    //2
    //main call-class
    //define a socket in the constructor.
    @Override
    protected Boolean doInBackground(String... strings) {
        if (strings == null) return false;
        String username = strings[0];
        if (username == null) return false;


        return null;
    }

    //1
    //creates things we need in 1
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //3
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

}
