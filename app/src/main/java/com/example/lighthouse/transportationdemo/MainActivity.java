package com.example.lighthouse.transportationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button small;
    private Button middle;
    private Button big;

    private ImageView car;

    private EditText startedit;
    private EditText endedit;

    private Button user;
    private Button notifigation;
    private Button likeuse;

    public String price = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        small = (Button)findViewById(R.id.main_smallcar);
        middle = (Button)findViewById(R.id.main_middlecar);
        big = (Button)findViewById(R.id.main_largecar);

        car = (ImageView)findViewById(R.id.cariamge);

        user = (Button)findViewById(R.id.main_me);
        notifigation = (Button)findViewById(R.id.main_info);
        likeuse = (Button)findViewById(R.id.main_like);

        startedit = (EditText)findViewById(R.id.main_startedit);
        endedit = (EditText)findViewById(R.id.main_finaledit);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });
        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                small.getResources().getColor(R.color.colorPrimary);
                car.setImageResource(R.drawable.smallcar);
                price = "80";
                middle.getResources().getColor(R.color.white);
                big.getResources().getColor(R.color.white);
            }
        });

        middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                middle.getResources().getColor(R.color.colorPrimary);
                car.setImageResource(R.drawable.middlecar);
                price = "120";
                small.getResources().getColor(R.color.white);
                big.getResources().getColor(R.color.white);
            }
        });

        big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               big.getResources().getColor(R.color.colorPrimary);
                car.setImageResource(R.drawable.bigcar);
                price = "200";
                small.getResources().getColor(R.color.white);
                middle.getResources().getColor(R.color.white);
            }
        });



        likeuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ConfirmActivity.class);
                String start = startedit.getText().toString();
                String end = endedit.getText().toString();
                intent.putExtra("start_data",start);
                intent.putExtra("end_data",end);
                intent.putExtra("price",price);
                startActivity(intent);
            }
        });

        notifigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NoticeActivity.class);
                startActivity(intent);
            }
        });
    }
}
