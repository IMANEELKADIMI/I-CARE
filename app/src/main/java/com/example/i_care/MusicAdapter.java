package com.example.i_care;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter {
    private Context context;
    private MusicModel[] musics;

    MusicAdapter(Context context, MusicModel[] musics){
        this.context = context;
        this.musics = musics;
    }

    @Override
    public int getCount() {
        return musics.length;
    }

    @Override
    public Object getItem(int i) {
        return musics[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MusicModel music = (MusicModel) getItem(i);
        ViewHolder holder = null;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.music_item, viewGroup, false);
            holder = new ViewHolder();
            holder.musicImage = view.findViewById(R.id.music_image);
            holder.titleText = view.findViewById(R.id.music_title);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        holder.titleText.setText(music.getName());
        if (music.isPlaying()){
            holder.musicImage.setImageResource(R.drawable.ic_pause_circle);
        }
        else{
            holder.musicImage.setImageResource(R.drawable.ic_play_circle);
        }
        return view;
    }
    static class ViewHolder{
        ImageView musicImage;
        TextView titleText;

    }
}
