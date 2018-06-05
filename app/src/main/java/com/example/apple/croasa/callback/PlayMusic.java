package com.example.apple.croasa.callback;

import com.example.apple.croasa.model.Record;

import java.util.ArrayList;

public interface PlayMusic {
    public void playMusic(String path);

    public void PauseMusic(String path);

    public void DownLoadMusic(String path,String nameRecord);
}
