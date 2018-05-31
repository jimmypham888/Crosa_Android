package com.example.apple.croasa.presenter;

import com.example.apple.croasa.callback.DownloadFileCallBack;
import com.example.apple.croasa.model.DownLoadFileModel;
import com.example.apple.croasa.view.DownLoadFileView;

import okhttp3.ResponseBody;

public class DownloadFilePresenter implements DownloadFileCallBack {


    DownLoadFileView listener;

    DownLoadFileModel model;

    public DownloadFilePresenter(DownLoadFileView listener) {
        this.listener = listener;
        model = new DownLoadFileModel(this);
    }

    public void downloadFile() {
        model.DownLoadFile();
    }

    @Override
    public void downLoadFileSuccess(String path) {
        listener.showListFileDownload(path);
    }

    @Override
    public void downloadFileFail(String status) {

    }
}
