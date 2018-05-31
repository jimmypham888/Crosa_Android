package com.example.apple.croasa.callback;

import okhttp3.ResponseBody;

public interface DownloadFileCallBack {

    void downLoadFileSuccess(String path);

    void downloadFileFail(String status);

}
