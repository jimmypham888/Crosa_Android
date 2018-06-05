package com.example.apple.croasa.adapter;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Rcv_Dialog_Custom extends RecyclerView.Adapter<Rcv_Dialog_Custom.ViewHolder> {


    List<Record> arrayList;

    Context context;

    PlayMusic listeer;

    Boolean isPlay = true;

    public Rcv_Dialog_Custom(List<Record> arrayList, Context context, PlayMusic listeer) {
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            holder.tv_name.setText(arrayList.get(position).getCallId());
            holder.tv_date.setText(arrayList.get(position).getStartTime());
            holder.btn_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isPlay) {
                        listeer.playMusic(arrayList.get(position).getCallId());
                        holder.tv_play.setText("Pause");
                        isPlay = false;
                    } else {
                        holder.tv_play.setText("Play");
                        isPlay = true;
                        listeer.PauseMusic(arrayList.get(position).getCallId());
                    }

                }
            });

            holder.btn_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listeer.DownLoadMusic(arrayList.get(position).getLinkDownRecord(),arrayList.get(position).getCallId());
                }
            });

//        String path = Environment.getExternalStorageDirectory().toString() + "/Crosa Record";
//        File directory = new File(path);
//        File[] files = directory.listFiles();
//        ArrayList<Record> arr = new ArrayList<>();
//        for (int i = 0; i < arrayList.size(); i++) {
//            for (int j = 0; j < files.length; j++) {
//                String fileName = files[j].getName();
//                if (fileName.equalsIgnoreCase(arrayList.get(i).getCallId())) {
//                    holder.btn_download.setVisibility(View.GONE);
//                }
//            }
//        }
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
