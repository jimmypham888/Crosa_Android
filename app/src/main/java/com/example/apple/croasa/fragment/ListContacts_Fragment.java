package com.example.apple.croasa.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.app.usage.ExternalStorageStats;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.apple.croasa.R;
import com.example.apple.croasa.Utils.CallHelper;
import com.example.apple.croasa.activity.Details_Activity;
import com.example.apple.croasa.adapter.Rcv_Dialog_Custom;
import com.example.apple.croasa.adapter.Rcv_ListContacts;
import com.example.apple.croasa.callback.Call_Note_Record_Callback;
import com.example.apple.croasa.callback.PlayMusic;
import com.example.apple.croasa.model.Contact;
import com.example.apple.croasa.model.LoginObject;
import com.example.apple.croasa.model.Record;
import com.example.apple.croasa.network.APIService;
import com.example.apple.croasa.network.RetrofitHelpDownload;
import com.example.apple.croasa.presenter.DownloadFilePresenter;
import com.example.apple.croasa.presenter.MainAllPresenter;
import com.example.apple.croasa.view.DownLoadFileView;
import com.example.apple.croasa.view.MainAllView;
import com.example.apple.croasa.voip.OutgoingCallActivity;
import com.example.apple.croasa.voip.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListContacts_Fragment extends Fragment implements MainAllView , Call_Note_Record_Callback, DownLoadFileView {

    private static final String TAG = "ListContacts_Fragment";
    private RecyclerView recyclerView;

    private MainAllPresenter mainAllPresenter;

    private CallHelper callHelper;

    APIService apiService;

    DownloadFilePresenter presenter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_contacts, container, false);
        init(view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            LoginObject loginObject = (LoginObject) bundle.getSerializable("contacts");
            if (loginObject.getId() != null) {
                mainAllPresenter = new MainAllPresenter(this);
                Map<String,String> map = new HashMap<>();
                map.put("id_user_tvts",loginObject.getId().toString());
                mainAllPresenter.loadListContacts(map);
                callHelper = new CallHelper(getActivity());
                apiService = RetrofitHelpDownload.createService(APIService.class);
                presenter = new DownloadFilePresenter(this);
                presenter.downloadFile();
            }
        }
        return view;
    }


    public void init(View view) {

        recyclerView = view.findViewById(R.id.rcv_listcontacts);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


    }



    @Override
    public void onShowListContacts(ArrayList<Contact> arrayList) {
        Toast.makeText(getContext(), "list"+arrayList.size() , Toast.LENGTH_SHORT).show();
        Rcv_ListContacts adapter = new Rcv_ListContacts(getContext(),this,arrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick_Event_Call(String phone) {
        Toast.makeText(getContext(), "Call", Toast.LENGTH_SHORT).show();
        if (callHelper.getClient().isConnected()) {
            Intent intent = new Intent(getContext(), OutgoingCallActivity.class);
            intent.putExtra("from", "84901701063");
            intent.putExtra("to", phone);
            intent.putExtra("is_video_call", false);
            startActivity(intent);
        } else {
            Utils.reportMessage(getContext(), "Stringee session not connected");
        }
    }

    @Override
    public void onClick_Event_Note() {
        Toast.makeText(getContext(), "Note", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick_Event_Recored() {
        Toast.makeText(getContext(), "Record", Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager = getFragmentManager();
        Record_List_Dialog record_list_dialog = new Record_List_Dialog();
        record_list_dialog.show(fragmentManager,"dsd");

    }

    @Override
    public void onClick_Item_ListView(View view, int position) {
        Toast.makeText(getContext(), "ItemClick", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(),Details_Activity.class);
        startActivity(intent);
    }

    @Override
    public void onClick_Close_Swipe(RelativeLayout rl, Button btn) {
        rl.setVisibility(View.GONE);
        btn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick_Show_Swipe(RelativeLayout rl, Button btn) {
        if (rl.getVisibility() == View.VISIBLE) {
            rl.setVisibility(View.GONE);
        }
        rl.setVisibility(View.VISIBLE);
        btn.setVisibility(View.GONE);

    }

    @Override
    public void showListFileDownload(String path) {

    }
}
