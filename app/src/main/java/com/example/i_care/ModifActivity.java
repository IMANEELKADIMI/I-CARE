package com.example.i_care;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class ModifActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText Title,Content;
    Calendar cl;
    String todaysDate;
    String currentTime;
    long Id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent i = getIntent();
        Id = i.getLongExtra("ID",0);
        DBN db = new DBN(this);
        Note note = db.getNote(Id);

        final String title = note.getTitle();
        String content = note.getContent();
        Title = findViewById(R.id.noteTitle);
        Content = findViewById(R.id.noteDetails);
        Title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Title.setText(title);
        Content.setText(content);

        // set current date and time
        cl = Calendar.getInstance();
        todaysDate = cl.get(Calendar.YEAR)+"/"+(cl.get(Calendar.MONTH)+1)+"/"+cl.get(Calendar.DAY_OF_MONTH);
        Log.d("DATE", "Date: "+todaysDate);
        currentTime = pad(cl.get(Calendar.HOUR))+":"+pad(cl.get(Calendar.MINUTE));
        Log.d("TIME", "Time: "+currentTime);
    }


    private String pad(int time) {
        if(time < 10)
            return "0"+time;
        return String.valueOf(time);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save){
            Note note = new Note(Id,Title.getText().toString(),Content.getText().toString(),todaysDate,currentTime);
            Log.d("EDITED", "edited: before saving id -> " + note.getId());
            DBN dbn = new DBN(getApplicationContext());
            long id = dbn.editNote(note);
            Log.d("EDITED", "EDIT: id " + id);
            goToMain();
            Toast.makeText(this, "Note Edited.", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.delete){
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent i = new Intent(this,NoteMainActivity.class);
        startActivity(i);
    }


}
