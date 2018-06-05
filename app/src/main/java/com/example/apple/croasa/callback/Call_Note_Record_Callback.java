package com.example.apple.croasa.callback;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public interface Call_Note_Record_Callback {
    public void onClick_Event_Call(String phone);

    public void onClick_Event_Note();

    public void onClick_Event_Recored(String number);

    public void onClick_Item_ListView(View view, int position);

    public void onClick_Close_Swipe(RelativeLayout rl,Button btn);

    public void onClick_Show_Swipe(RelativeLayout rl, Button btn);
}
