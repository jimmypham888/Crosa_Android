package com.example.apple.croasa.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.croasa.R;
import com.example.apple.croasa.model.LoginObject;
import com.example.apple.croasa.network.APIService;
import com.example.apple.croasa.network.DownLoadRecord;
import com.example.apple.croasa.network.RetrofitHelpDownload;
import com.example.apple.croasa.network.RetrofitHelper;
import com.example.apple.croasa.presenter.LoginPresenter;
import com.example.apple.croasa.view.LoginView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Header;

public class Login_Activity extends AppCompatActivity implements LoginView,
        View.OnClickListener{

    private Button btn_login;

    private EditText edt_user_name, edt_pass;

    private LoginPresenter loginPresenter;

    public static final int REQUEST_PERMISSION_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);
        btn_login = findViewById(R.id.btn_login);
        edt_pass = findViewById(R.id.edt_pass);
        edt_user_name = findViewById(R.id.edt_email);
        btn_login.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> lstPermissions = new ArrayList<>();

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                lstPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }

            if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    lstPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (lstPermissions.size() > 0) {
                String[] permissions = new String[lstPermissions.size()];
                for (int i = 0; i < lstPermissions.size(); i++) {
                    permissions[i] = lstPermissions.get(i);
                }
                ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSION_CALL);
                return;
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isGranted = false;
        if (grantResults.length > 0) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    isGranted = false;
                    break;
                } else {
                    isGranted = true;
                }
            }
        }
    }

    @Override
    public void logInSuccess(LoginObject contact) {
        Intent intent = new Intent(Login_Activity.this,Main_All_Activity.class);
        intent.putExtra("contacts",contact);
        startActivity(intent);
    }

    @Override
    public void logInFaile(String status) {

        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String userName = edt_user_name.getText().toString();
                String pass = edt_pass.getText().toString();
                if (userName.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(this, "Username and pass word not null", Toast.LENGTH_SHORT).show();
                    return;
                } else if (userName.isEmpty() && !pass.isEmpty()){
                    Toast.makeText(this, "Username not null", Toast.LENGTH_SHORT).show();
                    return;
                } else if (pass.isEmpty() && !userName.isEmpty()){
                    Toast.makeText(this, "Password not null", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("username",userName);
                map.put("password",pass);
                logIn(map);
                break;
                default:
                    break;
        }
    }


    public void logIn(Map<String,String> userId) {
        loginPresenter.login(userId);
    }
}
