package com.example.steel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.steel.myapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this,DisplayTextActivity.class);
        EditText editText = findViewById(R.id.editText);
        TextView txtView = findViewById(R.id.textView);
        String message = editText.getText().toString();

        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
}
