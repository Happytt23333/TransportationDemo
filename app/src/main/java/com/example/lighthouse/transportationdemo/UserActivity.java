package com.example.lighthouse.transportationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.Driver;

public class UserActivity extends AppCompatActivity {
    private Button task;
    private Button driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        task = (Button)findViewById(R.id.user_task);
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,TaskActivity.class);
                startActivity(intent);
            }
        });

        driver = (Button)findViewById(R.id.user_driver);
        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, DriversActivity.class);
                startActivity(intent);
            }
        });
    }
}
