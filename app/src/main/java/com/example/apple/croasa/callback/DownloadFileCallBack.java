package com.example.apple.croasa.callback;

import com.example.apple.croasa.model.Record;

import java.util.ArrayList;

import okhttp3.ResponseBody;

public interface DownloadFileCallBack {

    void downLoadFileSuccess(ArrayList<Record> listPath);

    void downloadFileFail(String status);

}
