package com.example.a2048.ClientPakage;

import android.media.Image;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.fasterxml.jackson.core.*;        ///////////////////////////////////////////////

public class Client extends Thread {

    //attributes
    Socket client;
    DataInputStream read;
    DataOutputStream print;
    private String name,lastName,password,birthday;
    private Image picture;
    private static Client instence;
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


    public static Client getInstance() throws IOException {
        if (instence == null) instence = new Client();
        return instence;
    }

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
                ui.Result(true,"login success");
                ui.Result(false,"login failed");
            }
        };
        runOnNewThread(runnable);
    }

    private void runOnNewThread(Runnable runnable){
        new Thread(runnable).start();
    }//to in tabe ye tread mostaqel baraye login kardan karbar behesh midim

    public interface UiInterface {
        void Result(boolean isSuccess,String message);
        boolean isUiRunning();
    }



    public void create(String name,String password) throws IOException {

        Runnable runnable=new Runnable() {
            @Override
            public void run() {

//                ObjectWriter ow=new ObjectWrapper().writer().writeDefultprettyPrinter();
//                ObjectOutputStream

                ui.Result(true,"create success");

                ui.Result(false,"this name already exist");

            }
        };
        runOnNewThread(runnable);
    }
}
