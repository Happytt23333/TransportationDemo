package com.example.lighthouse.transportationdemo;


import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class NoticeActivity extends AppCompatActivity {
    private Button noticeback;
    private TextView noticetext;
    private String action;
    private IntentFilter intentFilter;
    private TaskChangedReceiver taskChangedReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        noticeback = (Button)findViewById(R.id.notice_back);
        noticeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        noticetext = (TextView)findViewById(R.id.notice_first);

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.lighthouse.TASK_FINISHED");
        taskChangedReceiver = new TaskChangedReceiver();
        registerReceiver(taskChangedReceiver,intentFilter);

//        switch (action){
//            case "com.example.lighthouse.TASK_FINISHED":
//                inte  ntFilter.addAction("com.example.lighthouse.TASK_FINISHED");
//                registerReceiver(taskChangedReceiver,intentFilter);
//                noticetext.setText("您的订单已经被完成了");
//                break;
//            default:
//        }
    }
}
