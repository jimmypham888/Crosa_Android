package com.example.apple.croasa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.apple.croasa.R;
import com.example.apple.croasa.callback.Call_Note_Record_Callback;
import com.example.apple.croasa.model.Contact;

import java.util.ArrayList;

public class Rcv_Status extends RecyclerView.Adapter<Rcv_Status.ViewHolder> {

    Context context;

    Call_Note_Record_Callback listener;

    ArrayList<Contact> arrayListContact;

    public Rcv_Status(Context context, Call_Note_Record_Callback listener, ArrayList<Contact> arrayListContact) {
        this.context = context;
        this.listener = listener;
        this.arrayListContact = arrayListContact;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_status,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayListContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name,email,phone,status_call,note;

        RelativeLayout rl_item;

        Button btn_show_slide, btn_call,btn_note,btn_record, btn_close_slide;
        public ViewHolder(View itemView) {
            super(itemView);
            rl_item = itemView.findViewById(R.id.rl_status);
            name = itemView.findViewById(R.id.tv_list_status_infor_name);
            phone = itemView.findViewById(R.id.tv_list_status_infor_phone);
            email = itemView.findViewById(R.id.tv_list_status_infor_email);
            status_call = itemView.findViewById(R.id.tv_list_status_infor_date);
            note = itemView.findViewById(R.id.tv_list_status_infor_hour_stt_);
            btn_show_slide = itemView.findViewById(R.id.btn_slide_calendar_call);
            btn_call = itemView.findViewById(R.id.btn_status_infor_slide_call_);
            btn_note = itemView.findViewById(R.id.btn_calendar_call_slide_note);
            btn_record = itemView.findViewById(R.id.btn_status_infor_slide_record);
            btn_close_slide = itemView.findViewById(R.id.btn_status_infor_call_slide_close_);
        }
    }
}
