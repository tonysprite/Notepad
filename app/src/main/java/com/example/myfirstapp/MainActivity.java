package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE="com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view){
        String userAccount = this.getUserAccount();
        String password = this.getPassword();
        Boolean loginResult = this.checkLogin(userAccount,password);
        if(loginResult==true){
            Intent intent  = new Intent(this, DisplayMessageActivity.class);
            String message = this.getHistoryContent();
            intent.putExtra(EXTRA_MESSAGE,message);
            startActivity(intent);
        }else{
            Toast.makeText(this, "帐号密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    private String getUserAccount(){
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        return message;
    }

    private String getPassword(){
        EditText editPassword = findViewById(R.id.editText5);
        String password = editPassword.getText().toString();
        return password;
    }

    public Boolean checkLogin(String userAccount,String password){
        String rightAccount = "18611622478";
        String rightPassword = "abc123";
        if(rightAccount.equals(userAccount)==false){
            return false;
        }
        if(rightPassword.equals(password)==false){
            return false;
        }
        return true;
    }

    public String getHistoryContent(){
        String content ="历史笔记";
        return content;
    }
}
