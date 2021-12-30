package com.example.i_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    public void playvideo(View v){
        startActivity(new Intent(this,PlayVideosActivity.class));
    }
    public void masks(View v){
        startActivity(new Intent(this,MaskActivity.class));
    }
    public void notes(View v){
        startActivity(new Intent(this,NoteMainActivity.class));
    }
    public void playmusic(View v){
        startActivity(new Intent(this,PlayMusicActivity.class));
    }

}