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

    private Button usecar;
    private ImageView car;

    private EditText startedit;
    private EditText endedit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        small = (Button)findViewById(R.id.main_smallcar);
        middle = (Button)findViewById(R.id.main_middlecar);
        big = (Button)findViewById(R.id.main_largecar);

        car = (ImageView)findViewById(R.id.cariamge);
        usecar = (Button)findViewById(R.id.main_like);
        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                small.getResources().getColor(R.color.colorPrimary);
                car.setImageResource(R.drawable.smallcar);
                middle.getResources().getColor(R.color.white);
                big.getResources().getColor(R.color.white);
            }
        });

        middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                middle.getResources().getColor(R.color.colorPrimary);
                car.setImageResource(R.drawable.middlecar);
                small.getResources().getColor(R.color.white);
                big.getResources().getColor(R.color.white);
            }
        });

        big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               big.getResources().getColor(R.color.colorPrimary);
                car.setImageResource(R.drawable.bigcar);
                small.getResources().getColor(R.color.white);
                middle.getResources().getColor(R.color.white);
            }
        });

        usecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ConfirmActivity.class);
                startActivity(intent);
            }
        });
    }
}
