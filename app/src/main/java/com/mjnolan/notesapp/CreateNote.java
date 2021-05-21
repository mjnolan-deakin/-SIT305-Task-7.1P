package com.mjnolan.notesapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNote extends AppCompatActivity {

    SQLiteDatabase carolyn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);

        carolyn = openOrCreateDatabase("Carolyn",MODE_PRIVATE,null);
        carolyn.execSQL("CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, note VARCHAR);");

    }

    public void saveNote(View v) {
        EditText editText = findViewById(R.id.editTextTextMultiLine);
        carolyn.execSQL("INSERT INTO notes (note) VALUES (\"" + editText.getText().toString() +"\");");
        Intent i = new Intent(this, ShowNotes.class);
        startActivity(i);
    }
}
