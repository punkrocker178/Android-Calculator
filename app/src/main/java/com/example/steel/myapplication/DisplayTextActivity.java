package com.example.steel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayTextActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtView,txtView_Operator ;
    EditText resultView, resultView_Operand,resultView_Answer;
    String operator ="";
    String op1 ="0";
    String op2 = "";
    String result ="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_text);
        addControl();
        addEvents();
        Intent intent = getIntent();
        String messageFromIntent = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        txtView.setText(messageFromIntent);
    }

    private void addControl(){
        txtView = findViewById(R.id.textView2);
        txtView_Operator = findViewById(R.id.textView_operator);
        resultView = findViewById(R.id.resultView);
        resultView_Operand = findViewById(R.id.resultView_operand);
        resultView_Answer = findViewById(R.id.resultView_answer);
        resultView.setInputType(0);
        resultView_Operand.setInputType(0);
        resultView_Answer.setInputType(0);
    }

    public void prevActivity(View view){
        finish();
    }

    private boolean isEmpty(View view){
        if(((EditText)view).getText().toString().trim().length() >0){
            return false;
        }
        return true;
    }

    private String calculate(String op1,String op2,String operator){
        Double a = Double.parseDouble(op1);
        Double b = Double.parseDouble(op2);
        Double ans = 0.0;
        switch (operator){
            case "x":
                ans = a*b;
                break;
            case "/":
                ans = a/b;
                break;
            case "+":
                ans = a+b;
                break;
            case "-":
                ans = a-b;
                break;
        }
        return Double.toString(ans);
    }


    private void addEvents() {
        txtView_Operator.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        String textValue = ((Button)view).getText().toString();

        //Number button 1-9
        switch (view.getId()){
            default:
                if(op1.equals("0")){
                    op1 = textValue;
                    Log.d("Number","Value: "+ op1);
                    resultView.setText(op1);
                }else if(!op1.equals("0") && operator.equals("")){
                    op1 = op1 +textValue;
                    Log.d("Number","Value: "+ op1);
                    resultView.setText(op1);
                }else if(!operator.equals("")){ //Second operand will show on resultView_operator
                    op2 = op2+textValue;
                    Log.d("Operator and op2","Value: "+ operator+op2);
                    resultView_Operand.setText(op2);
                }
                break;

            case R.id.button_mul: case R.id.button_div : case R.id.button_add : case R.id.button_sub:
                    operator = textValue;
                    Log.d("Operator","Value: "+ operator);
                    txtView_Operator.setText(operator);
                break;

                //Delete all button
            case R.id.button_ce:
                op1 = "0";
                op2 = "";
                operator = "";
                result = "0";

                Log.d("Number after clear all","Value: "+ op1+" "+op2+" "+operator+" "+result);

                resultView.setText(op1);
                txtView_Operator.setText(operator);
                resultView_Operand.setText(op2);
                resultView_Answer.setText(result);
                break;

                //Delete Button
            case R.id.button_c:
                op1 = op1.substring(0, op1.length()-1);
                if(op1.equals("")){
                    op1 = "0";
                }
                resultView.setText(op1);
                break;

            case R.id.button_equals:
                if(!isEmpty(resultView)&&!isEmpty(resultView_Operand)){
                    result = calculate(op1,op2,operator);
                }
                resultView_Answer.setText(result);
                break;

        }

    }
}
