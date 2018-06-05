package com.example.apple.croasa.fragment;

import android.annotation.SuppressLint;
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
import com.example.apple.croasa.presenter.DownloadFilePresenter;
import com.example.apple.croasa.view.DownLoadFileView;
import com.example.apple.croasa.view.DownLoadHistoryView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("ValidFragment")
public class Record_List_Dialog extends DialogFragment implements DownLoadFileView, DownLoadHistoryView {

    private static final String TAG = "Record_List_Dialog";
    AudioManager am;
    MediaPlayer mediaPlayer;
    RecyclerView rv;
    DownloadFilePresenter presenter;
    public String mPhone;
    Rcv_Dialog_Custom adapter;

    @SuppressLint("ValidFragment")
    public Record_List_Dialog(String mPhone) {
        this.mPhone = mPhone;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_listrecord_custome, container, false);
        rv = view.findViewById(R.id.rcv_list_record);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        presenter = new DownloadFilePresenter(this, this);
        Map<String, String> map = new HashMap<>();
        map.put("mobile_phone", mPhone);
        presenter.downloadHistory(map);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @Override
    public void showListFileDownload(ArrayList<Record> arr) {

    }

    @Override
    public void showListHistory(List<Record> list) {
        adapter = new Rcv_Dialog_Custom(list, getContext(), new PlayMusic() {
            @Override
            public void playMusic(String path) {
                String fileMp3 = Environment.getExternalStorageDirectory() + "/Crosa Record" + File.separator + path + ".mp3";
                File mp3 = new File(fileMp3);
                Log.d("path_music", path);
                if (mp3 != null) {
                    mediaPlayer = MediaPlayer.create(getContext(), Uri.fromFile(mp3));
                    if (mediaPlayer!=null) {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        mediaPlayer.start();
                    } else {
                        Toast.makeText(getContext(), "Bạn cần phải tải file ghi âm về trước", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void PauseMusic(String path) {
                if (mediaPlayer!=null) {
                    mediaPlayer.pause();
                }
            }

            @Override
            public void DownLoadMusic(String path, String nameRecord) {
                presenter.downloadFile(path, nameRecord);
                adapter.notifyDataSetChanged();
            }
        });
        rv.setAdapter(adapter);

    }

    @Override
    public void showHistoryListError(String error) {

    }
}
