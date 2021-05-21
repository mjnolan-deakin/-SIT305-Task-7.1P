package com.mjnolan.notesapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateDelete extends AppCompatActivity {

    SQLiteDatabase carolyn;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_delete);

        carolyn = openOrCreateDatabase("Carolyn",MODE_PRIVATE,null);
        carolyn.execSQL("CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, note VARCHAR);");

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        Cursor cursor = carolyn.rawQuery("SELECT note FROM notes WHERE `id` = " + id + ";",null);

        EditText et = findViewById(R.id.editTextTextMultiLineUpdate);

        while (cursor.moveToNext()) {
            String note = cursor.getString(0);
            et.setText(note);
        }
        cursor.close();
    }

    public void updateNote(View v) {
        EditText editText = findViewById(R.id.editTextTextMultiLineUpdate);
        carolyn.execSQL("UPDATE notes SET note = \"" + editText.getText().toString() +"\" WHERE id = " + id + ";");
        Intent i = new Intent(this, ShowNotes.class);
        startActivity(i);
    }

    public void delateNote(View v) {
        carolyn.execSQL("DELETE FROM notes WHERE id = " + id + ";");
        Intent i = new Intent(this, ShowNotes.class);
        startActivity(i);
    }
}
