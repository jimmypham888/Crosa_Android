package com.example.apple.croasa.callback;

import com.example.apple.croasa.model.Record;

import java.util.List;

public interface DownLoadHistoryCallback {
    void getHistorySuccess(List<Record> records);

    void getHistoryFail(String error);

}
