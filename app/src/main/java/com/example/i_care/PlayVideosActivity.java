package com.example.i_care;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class PlayVideosActivity extends AppCompatActivity {
    VideoView videoView;
    ListView listView;
    ArrayList<String> videolist;
    ArrayAdapter adapter;
    // Search EditText
    EditText inputSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_videos);
        //instancier la partie ou on joue la video
        videoView=findViewById(R.id.videoview);
        //instancier une liste déroulante des titres des videos
        listView=findViewById(R.id.lvideo);

        inputSearch=findViewById(R.id.search);
        inputSearch.requestFocus();
        inputSearch.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0f, 0f, 0));
        inputSearch.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0f, 0f, 0));



        //ajout des videos dans la liste
        videolist=new ArrayList<>();
        videolist.add("morning meditation because you deserve to feel good today");
        videolist.add("morning yoga for beginners");
        videolist.add("meditation you can do anywhere");
        videolist.add("morning meditation for positive energy");
        //création de l'adapter
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,videolist){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                //design
                if (position %2 == 1){
                    view.setBackgroundColor(Color.parseColor("#D8A2A9"));
                }
                else {
                    view.setBackgroundColor(Color.parseColor("#EFD1D5"));
                }
                return view;
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.morning_meditation_because_you_deserve_to_feel_good_today));
                        break;
                    case 1:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.morning_yoga_for_beginners));
                        break;
                    case 2:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.meditation_you_can_do_anywhere));
                        break;
                    case 3:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.morning_maditation_for_positive_energy));
                        break;
                    default:
                        break;
                }
                videoView.setMediaController(new MediaController(PlayVideosActivity.this));
                videoView.requestFocus();
                videoView.start();
            }
        });

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
}