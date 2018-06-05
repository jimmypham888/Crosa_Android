package com.example.apple.croasa.view;

import com.example.apple.croasa.model.Record;

import java.util.List;

public interface DownLoadHistoryView {
    void showListHistory(List<Record> list);

    void showHistoryListError(String error);
}
