package com.example.apple.croasa.activity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.croasa.R;
import com.example.apple.croasa.adapter.Rcv_ListContacts;
import com.example.apple.croasa.callback.Call_Note_Record_Callback;
import com.example.apple.croasa.fragment.ListContacts_Fragment;
import com.example.apple.croasa.model.Contact;
import com.example.apple.croasa.model.Contacts;
import com.example.apple.croasa.model.LoginObject;
import com.example.apple.croasa.network.APIService;
import com.example.apple.croasa.network.ApiUltis;
import com.example.apple.croasa.presenter.MainAllPresenter;
import com.example.apple.croasa.view.MainAllView;
import com.example.apple.croasa.voip.IncomingCallActivity;
import com.example.apple.croasa.voip.OutgoingCallActivity;
import com.example.apple.croasa.voip.Utils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.stringee.StringeeClient;
import com.stringee.call.StringeeCall;
import com.stringee.exception.StringeeError;
import com.stringee.listener.StringeeConnectionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main_All_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener{
    APIService apiService;

    private Button btn_logout, btn_calendar, btn_status, btn_contacts;
    private Map<String,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__all_);
        btn_logout = findViewById(R.id.btn_logout);
        btn_contacts = findViewById(R.id.btn_contacts);
        btn_calendar = findViewById(R.id.btn_calendar);
        btn_status = findViewById(R.id.btn_status);
        btn_status.setOnClickListener(this);
        btn_calendar.setOnClickListener(this);
        btn_contacts.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        apiService = ApiUltis.getApiService();
        Bundle bundle = getIntent().getExtras();
        LoginObject loginObject = (LoginObject) bundle.get("contacts");
        Bundle bundle_send = new Bundle();
        bundle_send.putSerializable("contacts",loginObject);

        Map<String,String> map = new HashMap<>();
        map.put("id_user_tvts",loginObject.getId().toString());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initFragmentListContact(savedInstanceState,bundle_send);
        activeButton(btn_contacts,true);
        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);

    }


    void activeButton(Button btn,Boolean active) {
        if (active) {
            btn.setBackgroundResource(R.drawable.back_ground_button_contact_active);
        } else {
            btn.setBackgroundResource(R.drawable.back_ground_button_contact_inactive);
        }
    }

    public void initFragmentListContact(Bundle savedInstanceState,Bundle values) {
        if (findViewById(R.id.container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            ListContacts_Fragment firstFragment = new ListContacts_Fragment();
            firstFragment.setArguments(values);

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, firstFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main__all_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        }  else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_logout:
                Intent intent = new Intent(Main_All_Activity.this,Login_Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_contacts:
                activeButton(btn_contacts,true);
                activeButton(btn_calendar,false);
                activeButton(btn_status,false);
                break;
            case R.id.btn_calendar:
                activeButton(btn_contacts,false);
                activeButton(btn_calendar,true);
                activeButton(btn_status,false);
                break;
            case R.id.btn_status:
                activeButton(btn_contacts,false);
                activeButton(btn_calendar,false);
                activeButton(btn_status,true);
                break;
                default:
                    break;
        }

    }

}
