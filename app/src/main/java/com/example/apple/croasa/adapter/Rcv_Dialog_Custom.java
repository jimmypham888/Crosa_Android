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
import android.widget.Toast;

import com.example.apple.croasa.R;
import com.example.apple.croasa.callback.PlayMusic;
import com.example.apple.croasa.model.Record;

import java.util.ArrayList;

public class Rcv_Dialog_Custom extends RecyclerView.Adapter<Rcv_Dialog_Custom.ViewHolder> {


    ArrayList<Record> arrayList;

    Context context;

    PlayMusic listeer;

    public Rcv_Dialog_Custom(ArrayList<Record> arrayList, Context context, PlayMusic listeer) {
        this.arrayList = arrayList;
        this.context = context;
        this.listeer = listeer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_dialog,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.tv_name.setText(arrayList.get(position).getName());
            holder.tv_date.setText(arrayList.get(position).getDate());
            holder.btn_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listeer.playMusic(arrayList.get(position).getPath());
                }
            });

            holder.btn_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listeer.PauseMusic(arrayList.get(position).getPath());
                }
            });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_stt, tv_date, tv_name,tv_play;
        public Button bth;
        public RelativeLayout rlt,btn_play, btn_download;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_stt = itemView.findViewById(R.id.tv_dialog_custom_stt_);
            tv_date = itemView.findViewById(R.id.tv_dialog_custom_date_);
            tv_name = itemView.findViewById(R.id.tv_dialog_custom_namefile_);
            tv_play = itemView.findViewById(R.id.tv_btn_play);
            btn_download = itemView.findViewById(R.id.btn_download_record);
            btn_play = itemView.findViewById(R.id.btn_play_record);
            rlt = itemView.findViewById(R.id.rl_item_record);
        }
    }
}
