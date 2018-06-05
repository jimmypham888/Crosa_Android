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
import com.example.apple.croasa.model.Contacts;

import java.util.ArrayList;

public class Rcv_ListContacts extends RecyclerView.Adapter<Rcv_ListContacts.ViewHolder>{

    ArrayList<Contacts> arrayList;

    Context context;

    Call_Note_Record_Callback listener;

    ArrayList<Contact> arrayListContact;

    public Rcv_ListContacts(ArrayList<Contacts> arrayList, Context context, Call_Note_Record_Callback listener) {
        this.arrayList = arrayList;
        this.context = context;
        this.listener = listener;
    }


    public Rcv_ListContacts(Context context, Call_Note_Record_Callback listener, ArrayList<Contact> arrayListContact) {
        this.context = context;
        this.listener = listener;
        this.arrayListContact = arrayListContact;
    }

    public Rcv_ListContacts(ArrayList<Contacts> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_contacts,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.name.setText(arrayListContact.get(position).getName());
        int stt = position + 1;
        holder.stt.setText(String.valueOf(stt));
        holder.phone.setText(arrayListContact.get(position).getPhone());
        holder.email.setText(arrayListContact.get(position).getEmail());
        holder.rank.setText("L" + arrayListContact.get(position).getCurrentLevel());
        holder.rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick_Item_ListView(view, position);
            }
        });

        holder.btn_close_slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick_Close_Swipe(holder.rl_swipe,holder.btn_show_slide);
            }
        });

        holder.btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick_Event_Call(arrayListContact.get(position).getPhone());
            }
        });

        holder.btn_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick_Event_Note();
            }
        });

        holder.btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick_Event_Recored(arrayListContact.get(position).getPhone());
            }
        });

        holder.btn_show_slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick_Show_Swipe(holder.rl_swipe,holder.btn_show_slide);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListContact.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name,email,phone,rank,stt;

        RelativeLayout rl_item,rl_swipe;

        Button btn_show_slide, btn_call,btn_note,btn_record, btn_close_slide;


        public ViewHolder(View itemView) {
            super(itemView);
            rl_item = itemView.findViewById(R.id.rl_item_listcontacts);
            name = itemView.findViewById(R.id.tv_list_contacts_infor);
            stt = itemView.findViewById(R.id.tv_list_contacts_stt);
            phone = itemView.findViewById(R.id.tv_list_contacts_infor_email);
            email = itemView.findViewById(R.id.tv_list_contacts_infor_phone);
            rank = itemView.findViewById(R.id.tv_listcontacts_level);
            btn_show_slide = itemView.findViewById(R.id.btn_show_slide_itemlist_contacts);
            btn_call = itemView.findViewById(R.id.btn_show_slide_itemlist_contacts_call);
            btn_note = itemView.findViewById(R.id.btn_note_item_listcontacts);
            btn_record = itemView.findViewById(R.id.btn_record_item_listcontacts);
            btn_close_slide = itemView.findViewById(R.id.btn_show_slide_itemlist_contacts_close);
            rl_swipe = itemView.findViewById(R.id.rl_slide_show_listcontacts);
        }
    }
}
