package com.mjnolan.notesapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowNotes extends AppCompatActivity {

    SQLiteDatabase carolyn;
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> indexIDs = new ArrayList<String>();
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_notes);

        carolyn = openOrCreateDatabase("Carolyn",MODE_PRIVATE,null);
        carolyn.execSQL("CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, note VARCHAR);");

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        ListView lv = findViewById(R.id.all_notes);
        //lv.setListAdapter(adapter);
        lv.setAdapter(adapter);


        Cursor cursor = carolyn.rawQuery("SELECT * FROM notes",null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String note = cursor.getString(1);
            int i = note.indexOf(System.getProperty("line.separator"));
            Log.i("TESTING", "0.1");
            Log.i("TESTING", "0.2");
            try {
                listItems.add(note.substring(0, i).trim());
            } catch (Exception e) {
                try {
                    listItems.add(note.substring(0, 30).trim());
                } catch (Exception ex) {
                    listItems.add(note.trim());
                }
            }
            indexIDs.add(id);
            Log.i("TESTING", "0.3");
        }
        cursor.close();
        adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String id = indexIDs.get(position);
                Intent i = new Intent(getApplicationContext(), UpdateDelete.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
    }

}

