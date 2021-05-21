package com.mjnolan.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase carolyn;
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems = new ArrayList<String>();
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carolyn = openOrCreateDatabase("Carolyn",MODE_PRIVATE,null);
        carolyn.execSQL("CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, note VARCHAR);");
    }

    public void newNote(View v) {
        Intent i = new Intent(this, CreateNote.class);
        startActivity(i);
    }

    public void viewNotes(View v) {

        Intent i = new Intent(this, ShowNotes.class);
        startActivity(i);

        Cursor cursor = carolyn.rawQuery("SELECT * FROM notes",null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String note = cursor.getString(1);
            Log.i("TESTING", id);
            Log.i("TESTING", note);
            //and so on
        }
        cursor.close();

    }
}

