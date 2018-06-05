package com.example.apple.croasa.presenter;

import com.example.apple.croasa.callback.DownLoadHistoryCallback;
import com.example.apple.croasa.callback.DownloadFileCallBack;
import com.example.apple.croasa.model.DownLoadFileModel;
import com.example.apple.croasa.model.Record;
import com.example.apple.croasa.view.DownLoadFileView;
import com.example.apple.croasa.view.DownLoadHistoryView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;

public class DownloadFilePresenter implements DownloadFileCallBack , DownLoadHistoryCallback{


    DownLoadFileView listener;

    DownLoadFileModel model;

    DownLoadHistoryView downLoadHistoryView;

    public DownloadFilePresenter(DownLoadFileView listener,DownLoadHistoryView downLoadHistoryView) {
        this.listener = listener;
        this.downLoadHistoryView = downLoadHistoryView;
        model = new DownLoadFileModel(this,this);
    }

    public void downloadFile(String url, String nameRecord) {
        model.DownLoadFile(url,nameRecord);
    }

    public void downloadHistory(Map<String,String> number) {
        model.getAllHistory(number);
    }

    @Override
    public void downLoadFileSuccess(ArrayList<Record> listPath) {
        listener.showListFileDownload(listPath);
    }

    @Override
    public void downloadFileFail(String status) {

    }

    @Override
    public void getHistorySuccess(List<Record> records) {

        downLoadHistoryView.showListHistory(records);
    }

    @Override
    public void getHistoryFail(String error) {
        downLoadHistoryView.showHistoryListError(error);
    }
}
