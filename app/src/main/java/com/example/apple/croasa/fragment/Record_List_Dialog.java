package com.example.apple.croasa.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apple.croasa.R;
import com.example.apple.croasa.adapter.Rcv_Dialog_Custom;
import com.example.apple.croasa.callback.PlayMusic;
import com.example.apple.croasa.model.Record;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Record_List_Dialog extends DialogFragment {

    private static final String TAG ="Record_List_Dialog" ;
    AudioManager am;
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_listrecord_custome,container,false);
        RecyclerView rv = view.findViewById(R.id.rcv_list_record);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        String path = Environment.getExternalStorageDirectory().toString()+"/Crosa Record";
        Log.d(TAG, "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d(TAG, "Size: "+ files.length);
        ArrayList<Record> arr = new ArrayList<>();

        for (int i = 0; i < files.length; i++)
        {
            String fileName = files[i].getName();
            Log.d(TAG, "FileName:" + fileName);
            Record rc = new Record();
            rc.setDate("01/01/2018");
            rc.setName(fileName);
            String filePath = path+File.separator+fileName;
            rc.setPath(filePath);
            arr.add(rc);
            Log.d(TAG, "Path:" + rc.getPath());
        }
        Rcv_Dialog_Custom adapter = new Rcv_Dialog_Custom(arr, getContext(), new PlayMusic() {
            @Override
            public void playMusic(String path) {
                File mp3 = new File(path);
                Log.d("path_music",path);
                mediaPlayer = MediaPlayer.create(getContext(), Uri.fromFile(mp3));
                mediaPlayer.start();
            }

            @Override
            public void PauseMusic(String path) {

            }
        });
        rv.setAdapter(adapter);
        return view;
    }
}
