package com.example.steel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayTextActivity extends AppCompatActivity {
    TextView txtView ;
    EditText resultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_text);
        Intent intent = getIntent();
        txtView = findViewById(R.id.textView2);
        resultView = findViewById(R.id.resultView);
        String messageFromIntent = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        txtView.setText(messageFromIntent);
    }

    public void updateNumberView(View view){
        String number = "";
        if(isEmpty(resultView)){
            number = ((Button)view).getText().toString();
        }else{
             number = resultView.getText().toString()+ ((Button)view).getText().toString();
        }

        resultView.setText(number);
    }

    public boolean isEmpty(EditText editText){
        if(editText.getText().toString().trim().length() > 0){
            return false;
        }
        return true;
    }

    public void prevActivity(View view){
        finish();
    }
}
