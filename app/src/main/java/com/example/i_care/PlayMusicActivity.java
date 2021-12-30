package com.example.i_care;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class PlayMusicActivity extends AppCompatActivity {

    private ListView listView;
    private MusicModel[] musicList;
    private MusicAdapter adapter;
    private MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        listView = findViewById(R.id.music_list_view);

        loadmusic();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                loadmusic();
                MusicModel music = musicList[i];
                if (mediaPlayer !=null){
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        music.setPlaying(false);
                    }
                }
                try {
                    mediaPlayer = MediaPlayer.create(PlayMusicActivity.this, music.getId());

                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            music.setPlaying(false);
                        }
                        else{
                            mediaPlayer.start();
                            music.setPlaying(true);
                        }

                }
                catch (Exception e){
                    Log.e("Exception", e.getMessage());
                }
            }
        });

    }
    public void loadmusic(){
        musicList = new MusicModel[]{
                new MusicModel(R.raw.asia,"Asia",false),
                new MusicModel(R.raw.deep,"Deep",false),
                new MusicModel(R.raw.meditation,"Meditation",false),
                new MusicModel(R.raw.nature,"Nature",false),
                new MusicModel(R.raw.piano,"Piano",false),
                new MusicModel(R.raw.relaxation,"Relaxation",false),
                new MusicModel(R.raw.sea,"Sea",false),
                new MusicModel(R.raw.sleep,"Sleep",false),
                new MusicModel(R.raw.wind,"Wind",false),
        };
        adapter = new MusicAdapter(PlayMusicActivity.this, musicList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer !=null){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer.reset();
            }
        }
    }

}