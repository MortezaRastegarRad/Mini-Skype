package com.example.a2048.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.a2048.R;

import java.util.List;

public class MenuActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Button call,search,contact,chat,profile;
    ScrollView scrollMenu;
    ListView listView;


    public void init(){
        call = (Button) findViewById(R.id.call_btn);
        search = (Button) findViewById(R.id.search_btn);
        contact = (Button) findViewById(R.id.contact_btn);
        chat = (Button) findViewById(R.id.chat_btn);
        profile = (Button) findViewById(R.id.profile_btn);
        scrollMenu = (ScrollView) findViewById(R.id.scroll_menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_page);
        init();

        listView = findViewById(R.id.contacts_list);

        initContactsList();
    }

    private void initContactsList() {
        List<String> contactsList = getContactsList();
        //listam ro ba contactam por mikonam
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contactsList));
        //setAdapter miyad ye laye graphici migire , ba tavajoh be on laye graphiky ma mitonim behesh vorodi bedim ta poresh kone ke inja ma faqat string dadim ke stringesh bayad idish text1 bashe
        listView.setOnItemClickListener(this);
    }

    private List<String> getContactsList() {

       List<String> l = null;



       //inja bayad ba server ertebat barqarar konam va esm tamam contact haye taraf ro begiram berizam to listam
        return l;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.call_btn:
                Intent go_to_page_calls;
            case R.id.search_btn:
            case R.id.contact_btn:
            case R.id.chat_btn:
                Intent go_to_page_chat=new Intent("android.intent.action.MENUACTIVITY");
                startActivity(go_to_page_chat);
                break;
            case R.id.profile_btn:

        }

    }

    /**
     * click handler for contacts
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String username = ((TextView)view).getText().toString();

    }
}
