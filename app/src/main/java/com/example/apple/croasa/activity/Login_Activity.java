package com.example.apple.croasa.activity;

import android.content.Intent;
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

import java.util.HashMap;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);
        btn_login = findViewById(R.id.btn_login);
        edt_pass = findViewById(R.id.edt_pass);
        edt_user_name = findViewById(R.id.edt_email);
        btn_login.setOnClickListener(this);
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
