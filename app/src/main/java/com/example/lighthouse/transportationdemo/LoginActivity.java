package com.example.lighthouse.transportationdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText nameEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox masterCheck;
    private CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        nameEdit = (EditText)findViewById(R.id.nameEdit);
        passwordEdit = (EditText)findViewById(R.id.passwordEdit);
        masterCheck = (CheckBox)findViewById(R.id.masterCheck);
        remember = (CheckBox)findViewById(R.id.remember);

        boolean isremember = pref.getBoolean("remember_password",false);
        if(isremember){
            String name = pref.getString("name","");
            String password = pref.getString("password","");
            nameEdit.setText(name);
            passwordEdit.setText(password);
            remember.setChecked(true);
        }
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                Boolean master = masterCheck.isChecked();

                if(name.equals("zhangsan") && password.equals("abcdefg")){
                    editor = pref.edit();
                    if(master){
                        if(remember.isChecked()){
                            editor.putBoolean("remember",true);
                            editor.putString("name",name);
                            editor.putString("password",password);
                        }else {
                            editor.clear();
                        }
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,"  Select the CheckBox of Master",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this,"Name or password is invalid",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
