package com.example.apple.croasa.model;

import android.os.Environment;
import android.util.Log;

import com.example.apple.croasa.callback.DownloadFileCallBack;
import com.example.apple.croasa.network.APIService;
import com.example.apple.croasa.network.ApiUltis;
import com.example.apple.croasa.network.RetrofitHelpDownload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownLoadFileModel {
    DownloadFileCallBack listener;

    APIService apiService;

    private static final String TAG = "DownLoadFileModel";

    public DownLoadFileModel(DownloadFileCallBack listener) {
        this.listener = listener;
        apiService = RetrofitHelpDownload.createService(APIService.class);
    }


    public void DownLoadFile() {
        apiService.downloadFile().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    writeResponseBodyToDisk(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    listener.downloadFileFail(t.getLocalizedMessage());
            }
        });
    }



    private boolean writeResponseBodyToDisk(ResponseBody body) {
        // todo change the file location/name according to your needs

        File folder = new File(Environment.getExternalStorageDirectory() + "/Crosa Record");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }
        if (success) {
            File futureStudioIconFile = new File(Environment.getExternalStorageDirectory() + "/Crosa Record" + File.separator + "Recordemo.mp3");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize + futureStudioIconFile.getAbsolutePath());
                    listener.downLoadFileSuccess(futureStudioIconFile.getAbsolutePath());
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
        }


        return true;
    }
}
