package com.example.lighthouse.transportationdemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TaskActivity extends AppCompatActivity {
    private Button back;
    private Button ingtask;
    private Button finishtask;
    private Button canceltask;

    private TextView task_time;
    private TextView task_number;
    private TextView task_start;
    private TextView task_end;
    private TextView task_master;

    private Button taskeditLeft;
    private Button taskeditRight;
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        dbHelper = new MyDatabaseHelper(this,"Task.db",null,2);

        back = (Button)findViewById(R.id.back_task);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });
        ingtask = (Button)findViewById(R.id.tasking);
        finishtask = (Button)findViewById(R.id.taskfinish);
        canceltask = (Button)findViewById(R.id.taskcancel);

        task_time = (TextView)findViewById(R.id.task_getTime);
        task_number = (TextView)findViewById(R.id.tasknumber);
        task_start = (TextView)findViewById(R.id.task_startText);
        task_end = (TextView)findViewById(R.id.task_endText);
        task_master = (TextView)findViewById(R.id.task_userText);

        taskeditLeft = (Button)findViewById(R.id.finished);
        taskeditRight = (Button)findViewById(R.id.canceled);

        Intent intent = getIntent();
        task_time.setText(intent.getStringExtra("Time"));
        task_number.setText(intent.getStringExtra("TaskNumber"));
        task_start.setText(intent.getStringExtra("start_data2"));
        task_end.setText(intent.getStringExtra("end_data2"));
        task_master.setText(intent.getStringExtra("Master"));

        final String a = task_time.getText().toString();
        final String b = task_number.getText().toString();
        final String c = task_start.getText().toString();
        final String d = task_end.getText().toString();
        final String e = task_master.getText().toString();

        taskeditLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TaskActivity.this,"Click the Button of 订单完成",Toast.LENGTH_SHORT).show();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(b.equals("")){
                    Toast.makeText(TaskActivity.this,"no tasking can finish",Toast.LENGTH_SHORT).show();
                }else{
                    //删除当前订单，textview清空
                    db.delete("tasking","tasknumber = ?",new String[]{b});
                    task_time.setText("");
                    task_number.setText("");
                    task_start.setText("");
                    task_end.setText("");
                    task_master.setText("");
                    //添加到taskfinished表中

                    ContentValues values = new ContentValues();
                    values.put("time",a);
                    values.put("tasknumber",b);
                    values.put("taskstart",c);
                    values.put("taskend",d);
                    values.put("taskmaster",e);
                    db.insert("taskfinished",null,values);
                    values.clear();
                }
            }
        });

        taskeditRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TaskActivity.this,"Click the Button of 订单取消",Toast.LENGTH_SHORT).show();
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                if(b.equals("")){
//                    Toast.makeText(TaskActivity.this,"no tasking can cancel",Toast.LENGTH_SHORT).show();
//                }else{
//                    //删除当前订单，textview清空
//                    db.delete("tasking","tasknumber = ?",new String[]{b});
//                    task_time.setText("");
//                    task_number.setText("");
//                    task_start.setText("");
//                    task_end.setText("");
//                    task_master.setText("");
//                    //添加到taskcancel表中
//
//                    ContentValues values = new ContentValues();
//                    values.put("time",a);
//                    values.put("tasknumber",b);
//                    values.put("taskstart",c);
//                    values.put("taskend",d);
//                    values.put("taskmaster",e);
//                    db.insert("taskcancel",null,values);
//                    values.clear();
//                }
                Intent intent = new Intent("com.example.lighthouse.TASK_FINISHED");
                sendBroadcast(intent);
            }
        });

        ingtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TaskActivity.this,"Click the Button of 进行中",Toast.LENGTH_SHORT).show();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("tasking",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do{
                        //遍历Cursor对象，取出数据并赋值到相应的TextView
                        String time = cursor.getString(cursor.getColumnIndex("time"));
                        String number = cursor.getString(cursor.getColumnIndex("tasknumber"));
                        String start = cursor.getString(cursor.getColumnIndex("taslstart"));
                        String end = cursor.getString(cursor.getColumnIndex("taskend"));
                        String master = cursor.getString(cursor.getColumnIndex("taskmaster"));
                        task_time.setText(time);
                        task_number.setText(number);
                        task_start.setText(start);
                        task_end.setText(end);
                        task_master.setText(master);
                    }while (cursor.moveToNext());
                }
            }
        });

        finishtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TaskActivity.this,"Click the Button of 已完成",Toast.LENGTH_SHORT).show();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("taskfinished",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do{
                        //遍历Cursor对象，取出数据并赋值到相应的TextView
                        String time = cursor.getString(cursor.getColumnIndex("time"));
                        String number = cursor.getString(cursor.getColumnIndex("tasknumber"));
                        String start = cursor.getString(cursor.getColumnIndex("taslstart"));
                        String end = cursor.getString(cursor.getColumnIndex("taskend"));
                        String master = cursor.getString(cursor.getColumnIndex("taskmaster"));
                        task_time.setText(time);
                        task_number.setText(number);
                        task_start.setText(start);
                        task_end.setText(end);
                        task_master.setText(master);
                    }while (cursor.moveToNext());
                }
            }
        });

        canceltask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TaskActivity.this,"Click the Button of 进行中",Toast.LENGTH_SHORT).show();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("taskcanceled",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do{
                        //遍历Cursor对象，取出数据并赋值到相应的TextView
                        String time = cursor.getString(cursor.getColumnIndex("time"));
                        String number = cursor.getString(cursor.getColumnIndex("tasknumber"));
                        String start = cursor.getString(cursor.getColumnIndex("taslstart"));
                        String end = cursor.getString(cursor.getColumnIndex("taskend"));
                        String master = cursor.getString(cursor.getColumnIndex("taskmaster"));
                        task_time.setText(time);
                        task_number.setText(number);
                        task_start.setText(start);
                        task_end.setText(end);
                        task_master.setText(master);
                    }while (cursor.moveToNext());
                }
            }
        });
    }
}
