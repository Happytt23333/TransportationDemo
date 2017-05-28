package com.example.lighthouse.transportationdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Driver;

public class DriversActivity extends AppCompatActivity {
    private Button driverback;
    private Button drivercommit;

    private EditText driveredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);

        driverback = (Button)findViewById(R.id.driver_back);
        driverback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriversActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });

        driveredit = (EditText)findViewById(R.id.driver_edit);
        final String driverphone = driveredit.getText().toString();
        drivercommit = (Button)findViewById(R.id.driver_add);
        drivercommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DriversActivity.this,"Add successful",Toast.LENGTH_SHORT).show();
                save(driverphone);
            }
        });
    }

    public void save(String inputtext){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("driver", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputtext);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
