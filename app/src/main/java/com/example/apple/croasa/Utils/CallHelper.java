package com.example.apple.croasa.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.apple.croasa.activity.Main_All_Activity;
import com.example.apple.croasa.voip.IncomingCallActivity;
import com.example.apple.croasa.voip.Utils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.stringee.StringeeClient;
import com.stringee.call.StringeeCall;
import com.stringee.exception.StringeeError;
import com.stringee.listener.StringeeConnectionListener;

import java.util.HashMap;
import java.util.Map;

public class CallHelper {

    Activity context;



        public static StringeeClient client;
    private String to = "";
    public static Map<String, StringeeCall> callsMap = new HashMap<>();
    private String accessToken = "eyJjdHkiOiJzdHJpbmdlZS1hcGk7dj0xIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJqdGkiOiJTS2dkYk5GbjVCR0pIa2JLNDJ3OUFEQTk3N2N2UDJwZlp0LTE1Mjc0MzUzNjkiLCJpc3MiOiJTS2dkYk5GbjVCR0pIa2JLNDJ3OUFEQTk3N2N2UDJwZlp0IiwiZXhwIjoxNTMwMDI3MzY5LCJ1c2VySWQiOiJkdW9uZ19kbyJ9.LdJ1_4y3EBTZZr5n7CKXd4B3zrQcF5rhqjjugcTZqxE"; // replace your access token here.

    private EditText etTo;
    private TextView tvUserId;
    private ProgressDialog progressDialog;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private final String PREF_NAME = "com.stringee.onetoonecallsample";
    private final String IS_TOKEN_REGISTERED = "is_token_registered";
    private final String TOKEN = "token";

    public CallHelper(Activity context) {
        this.context = context;

        initAndConnectStringee();
    }


    public StringeeClient getClient() {
        return client;
    }

    public void initAndConnectStringee() {
        client = new StringeeClient(context);
        client.setConnectionListener(new StringeeConnectionListener() {
            @Override
            public void onConnectionConnected(final StringeeClient stringeeClient, boolean isReconnecting) {
                boolean isTokenRegistered = sharedPreferences.getBoolean(IS_TOKEN_REGISTERED, false);
                if (!isTokenRegistered) {
                    final String token = FirebaseInstanceId.getInstance().getToken();
                    client.registerPushToken(token, new StringeeClient.RegisterPushTokenListener() {
                        @Override
                        public void onPushTokenRegistered(boolean success, String desc) {
                            Log.d("Stringee", "Register push token: " + desc);
                            if (success) {
                                editor.putBoolean(IS_TOKEN_REGISTERED, true);
                                editor.putString(TOKEN, token);
                                editor.commit();
                            }
                        }

                        @Override
                        public void onPushTokenUnRegistered(boolean success, String desc) {
                            Log.d("Stringee", "Unregister push token: " + desc);
                        }
                    });
                }

                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Utils.reportMessage(context, "StringeeClient is connected.");
                    }
                });
            }

            @Override
            public void onConnectionDisconnected(StringeeClient stringeeClient, boolean isReconnecting) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Utils.reportMessage( context, "StringeeClient disconnected.");
                    }
                });
            }

            @Override
            public void onIncomingCall(final StringeeCall stringeeCall) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callsMap.put(stringeeCall.getCallId(), stringeeCall);
                        Intent intent = new Intent(context, IncomingCallActivity.class);
                        intent.putExtra("call_id", stringeeCall.getCallId());
                        context.startActivity(intent);
                    }
                });
            }

            @Override
            public void onConnectionError(StringeeClient stringeeClient, final StringeeError stringeeError) {
                Log.d("Stringee", "StringeeClient fails to connect: " + stringeeError.getMessage());
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utils.reportMessage(context, "StringeeClient fails to connect: " + stringeeError.getMessage());
                    }
                });
            }

            @Override
            public void onRequestNewToken(StringeeClient stringeeClient) {
                // Get new token here and connect to Stringe server
            }
        });
        client.connect(accessToken);
    }



}
