package com.example.a2048.ClientPakage;

import android.media.Image;

import com.example.a2048.tasks.CallTask;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class Client extends Thread {

    //attributes
    Socket client;
    DataInputStream read;
    DataOutputStream print;
    private String name,lastName,password,birthday;
    private Image picture;
    private static Client instance;
    private UiInterface ui;

    //getter setter start
    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

    //if i's been launched
    public static Client getInstance() throws IOException {
        if (instance == null) instance = new Client();
        return instance;
    }

    //connection between client & graphic thread.
    public void setUi(UiInterface ui){
        this.ui = ui;
    }

    //getter setter finished
    protected Client() throws IOException {

    }
    // constructor is private yani nemishe biron az in ja azash shey sakht

    @Override
    public void run() {


        try {//init() mikonim


            initialize();

            String command = read.readUTF();

//            switch (command) {
//
//                case creat:
//                    create();
//                    break;
//                case signin:
//
//                    break;
//                case signout:
//
//                    break;
//            }
            //switch case ha ro inja mizarim

            //listen for incoming messages

            //login ro to inja fara mikhonim
            //ui.onLoginResult(true,"login success");
            //break;
            //logout

            //list

            //tamas

            //...

// deqat konim har kodom az in case ha qabl az break shodan bayad zavab nahaie ro be thread UI befrestan

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws IOException {

                                                        //create new socket and input and output streams
        client=new Socket("localhost",8888);
        read=new DataInputStream(client.getInputStream());
        print=new DataOutputStream(client.getOutputStream());

    }

    public void login(final String username,final String password){//tabe login hastesh
        //create new thread and send login request

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //out.write("login "+ username +" "+ password);
//                ui.Result(true,"login success");
//                ui.Result(false,"login failed");
            }
        };
        runOnNewThread(runnable);
    }

    //in this function we have an independent thread for user to login.
    private void runOnNewThread(Runnable runnable){
        new Thread(runnable).start();
    }


    public void call(String username){
        new CallTask().execute(username);
    }

    public interface UiInterface {
        //message , undefined object -> it may be boolean, arrayList, ..
        //void Result(HashMap<String,Object> map);


        //to see if app is still open.
        //boolean isUiRunning();

        void Result(boolean isSuccess,String message);
    }

    public void create(String name,String password) throws IOException {


        /*
        JSONObject object = new JSONObject();
        object.put("isLoginSuccess",true);
        object.getBoolean("isLoginSuccess");
        object.toString();
        JSONObject o = new JSONObject("");
        */

        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                ui.Result(true,"create success");

                ui.Result(false,"this name already exist");

            }
        };
        runOnNewThread(runnable);
    }
}

/*
{
"key":"value",
"key2": 10,
"key3": { }
 */

/*
ObjectWriter a = ObjectWrapper().writer().withDefaultPrettyPrinter();
String json = a.writeValueAsString(obj);


ObjectMapper b = new ObjectWrapper();
message msg = b.readValue(json , massage.class);
 */