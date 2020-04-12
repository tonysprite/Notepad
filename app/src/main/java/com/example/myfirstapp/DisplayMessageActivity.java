package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DisplayMessageActivity extends AppCompatActivity {
    public static String PREFIX="CaseShareMemo";
    public static String SUFFIX=".txt";
    public static final String EXTRA_MESSAGE="com.example.myfirstapp.MESSAGE";
    public static String FILENAME="note1.text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.editText2);
        textView.setText(message);
    }

    public void onSave(View view){
        String result = "";
        try{
            String content = this.readContent();
            EditText editText = findViewById(R.id.editText2);
            String message = editText.getText().toString();
            if(message.equals("")){
                throw new Exception("请输入内容");
            }
            this.writeContent(message);
            result="保存成功"+this.readContent();
        }catch (Exception e){
            result = "保存失败"+e.getMessage();
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

    }

    public String readContent() throws Exception {
        try{
            String content ="";
            String pathName=DisplayMessageActivity.FILENAME;

            Context context = this.getApplicationContext();
            File file = new File(context.getFilesDir()+"/"+pathName);
            if(!file.exists()){
                file.createNewFile();
            }
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader
                    inputStreamReader= new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            try(BufferedReader reader = new BufferedReader (inputStreamReader)) {
                content = reader.readLine();
                while(content !=null){
                    stringBuilder.append(content).append("\n");
                    content = content+reader.readLine()+"\n";
                }
            }catch (IOException e){
                throw new Exception("read file failed,m:"+e.getMessage());
            }finally {
                content = stringBuilder.toString();
            }
            return content;
        }catch (Exception e){
            throw new Exception("readContent,e"+e.getMessage());
        }
    }

    public void writeContent(String content) throws Exception {
        try{
            String filename=DisplayMessageActivity.FILENAME;
            Context context = this.getApplicationContext();
            File file = new File(context.getFilesDir()+"/"+filename);
            try(FileOutputStream fos = new FileOutputStream(file)){
                fos.write(content.getBytes());
            }
        }catch (Exception e){
            throw new Exception("writeContent,e:"+e.getMessage());
        }
    }

    public String getViewContent(){
        EditText editText = findViewById(R.id.editText5);
        String message = editText.getText().toString();
        return message;
    }
}
