package com.example.lighthouse.transportationdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TaskChangedReceiver extends BroadcastReceiver {
    public TaskChangedReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        Toast.makeText(context,"Receive the broadcast",Toast.LENGTH_SHORT).show();
    }
}
