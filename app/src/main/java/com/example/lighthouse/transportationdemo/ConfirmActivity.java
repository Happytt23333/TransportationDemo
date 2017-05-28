package com.example.lighthouse.transportationdemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class ConfirmActivity extends AppCompatActivity {
    private TextView price;
    private Button confirm;
    private TextView time;

    private EditText needConfirm;
    private EditText taskInfo;
    private EditText userConfirm;
    private EditText phoneConfirm;
    private int i = 1512180300;

    private MyDatabaseHelper dbHelper;
    private CheckBox checkDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        dbHelper = new MyDatabaseHelper(this,"Task.db",null,1);
        price = (TextView)findViewById(R.id.price);
        time = (TextView)findViewById(R.id.getTime);

        time.setText(getTime());

        Intent intent = getIntent();
        final String confirm_start = intent.getStringExtra("start_data");
        final String confirm_end = intent.getStringExtra("end_data");
        String confirm_price = intent.getStringExtra("price");
        price.setText("价格："+confirm_price);

        needConfirm = (EditText)findViewById(R.id.needEdit);
        taskInfo = (EditText)findViewById(R.id.attentionEdit);
        userConfirm = (EditText)findViewById(R.id.userEdit);
        phoneConfirm = (EditText)findViewById(R.id.userphone);
        final String user  = userConfirm.getText().toString()+"("+phoneConfirm.getText().toString()+")";

        checkDriver = (CheckBox)findViewById(R.id.checkDriver);
        checkDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checked = checkDriver.isChecked();
                String driver = load();
                if(checked){
                    if(driver.equals("")){
                        Toast.makeText(ConfirmActivity.this,"Setting your driver",Toast.LENGTH_SHORT).show();
                        checkDriver.setChecked(false);
                    }
                }

            }
        });
        confirm = (Button)findViewById(R.id.next);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConfirmActivity.this,"Clicked the button",Toast.LENGTH_SHORT).show();
                dbHelper.getWritableDatabase();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //开始添加数据
                values.put("time",getTime());
                values.put("tasknumber",""+i);
                values.put("taskstart",confirm_start);
                values.put("taskend",confirm_end);
                values.put("taskmaster",user);
                db.insert("tasking",null,values);
                values.clear();

                //传输数据到TaskActivity
                Intent intent1 = new Intent(ConfirmActivity.this,TaskActivity.class);
                intent1.putExtra("Time",getTime());
                intent1.putExtra("TaskNumber",""+i);
                intent1.putExtra("start_data2",confirm_start);
                intent1.putExtra("end_data2",confirm_end);
                intent1.putExtra("Master",user);
                startActivity(intent1);
            }
        });
    }

    public String getTime(){
        int year = 0;
        int month = 0;
        int day = 0;
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        day = c.get(Calendar.DAY_OF_MONTH);
        return year+"年"+month+"月"+day+"日";
    }

    public String load(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        try{
            in = openFileInput("driver");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine())!= null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
