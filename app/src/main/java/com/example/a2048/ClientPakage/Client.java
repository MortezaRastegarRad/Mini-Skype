package com.example.a2048.ClientPakage;

import android.media.Image;

import com.example.a2048.activities.Constants;

import org.bson.Document;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {

    //attributes
    Socket client;
    DataInputStream read;
    DataOutputStream print;
    private String username, lastName, password, birthday;
    private Image picture;
    private static Client instence;
    private UiInterface ui;

    //getter setter start
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setUi(UiInterface ui) {

        this.ui = ui;

    }

    //getter setter finished
    protected Client() throws IOException {

        client = new Socket("192.168.43.244", 8888);
        read = new DataInputStream(client.getInputStream());
        print = new DataOutputStream(client.getOutputStream());

    }
    // constructor is private yani nemishe biron az in ja azash shey sakht

    public interface UiInterface {

        void Result(boolean isSuccess, String message);
        boolean isUiRunning();

    }

    private void runOnNewThread(Runnable runnable) {
        new Thread(runnable).start();
    }//to in tabe ye tread mostaqel baraye login kardan karbar behesh midim

    @Override
    public void run() {

        String result, command;
        Document document;

        while (true) {

            result=null;
            command=null;
            document=null;

//inja montazer mimone ta server ye dastory barash befreste va in bere ba tavajoh be natije morede nazar layeye graphicy ro avaz mikone
            try {

                result = read.readUTF();
                document = Document.parse(result);
                command = document.getString(Constants.TYPE);

            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (command) {
                case Constants.SIGNUPREQUEST:
                    SignUpًReaction(document);
                    break;
                case Constants.LOGINREQUEST:
                    LoginReaction(document);
                    break;
                case Constants.NOTIFICATION:
//                    createNotification();
                    break;

            }

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


        }
    }


    public void createAccount(final String name, final String password) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try {
                    // in print baraye ine ke server ham befahme bayad tabe sign up ro baz kone
                    print.writeUTF(Constants.SIGNUPREQUEST);
                    print.flush();
                    // inja miyaim name o pass ro mifrestim be server ta result ro azash begirim
                    Document json=new Document();
                    json.append(Constants.USERNAME,name);
                    json.append(Constants.PASSWORD,password);
                    String Send = json.toString();

                    print.writeUTF(Send);
                    print.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
        runOnNewThread(runnable);
    }

    private void SignUpًReaction(Document document) {

        ui.Result(document.getBoolean(Constants.WASSUCCESS),document.getString(Constants.MASSAGE));

        if(document.getBoolean(Constants.WASSUCCESS)) {

            setUsername(document.getString(Constants.USERNAME));
            setPassword(document.getString(Constants.PASSWORD));

        }
    }

    public void Login(final String username, final String password){

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {

                    print.writeUTF(Constants.LOGINREQUEST);
                    print.flush();

                    Document json = new Document();
                    json.append(Constants.USERNAME,username);
                    json.append(Constants.PASSWORD,password);
                    String send = json.toString();

                    print.writeUTF(send);
                    print.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        runOnNewThread(runnable);
    }

    private void LoginReaction(Document document) {

        ui.Result(document.getBoolean(Constants.WASSUCCESS),document.getString(Constants.MASSAGE));

    }

//    public void login(final String username, final String password) {//tabe login hastesh
//        //create new thread and send login request
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                //out.write("login "+ username +" "+ password);
//                ui.Result(true, "login success");
//                ui.Result(false, "login failed");
//            }
//        };
//        runOnNewThread(runnable);
//    }
}