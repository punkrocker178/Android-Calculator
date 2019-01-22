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

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DisplayTextActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtView_Operator ;
    EditText resultView_operand, resultView_operand2,resultView_Answer;
    String operator ="";
    String op1 ="";
    String op2 = "";
    String result ="";
    NumberFormat formatter;
    Double ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_text);
        addControl();
        addEvents();

    }

    private void addControl(){
        txtView_Operator = findViewById(R.id.textView_operator);
        resultView_operand = findViewById(R.id.resultView);
        resultView_operand2 = findViewById(R.id.resultView_operand);
        resultView_Answer = findViewById(R.id.resultView_answer);

        resultView_operand.setInputType(0);
        resultView_operand2.setInputType(0);
        resultView_Answer.setInputType(0);

        resultView_operand.requestFocus();
    }

    private boolean isEmpty(View view){
        if(((EditText)view).getText().toString().trim().length() >0){
            return false;
        }
        return true;
    }

    private Double calculate(String op1,String op2,String operator){
        Double a = Double.parseDouble(op1);
        Double b = Double.parseDouble(op2);
        Double ans = 0.0;
        switch (operator){
            case "x":
                ans = a*b;
                break;
            case "÷":
                ans = a/b;
                break;
            case "+":
                ans = a+b;
                break;
            case "-":
                ans = a-b;
                break;
        }
        return ans;
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
                if(resultView_operand.isFocused()){
                    op1 += textValue;
                    resultView_operand.setText(op1);
                }else if(resultView_operand2.isFocused()){
                    op2 += textValue;
                    resultView_operand2.setText(op2);
                }

                break;

            case R.id.button_mul: case R.id.button_div : case R.id.button_add : case R.id.button_sub:
                    operator = textValue;
                    Log.d("Operator","Value: "+ operator);
                    txtView_Operator.setText(operator);
                    resultView_operand2.requestFocus();
                break;

                //Delete all button
            case R.id.button_ce:
                op1 = "";
                op2 = "";
                operator = "";
                result = "";

                Log.d("Number after clear all","Value: "+ op1+" "+op2+" "+operator+" "+result);

                resultView_operand.setText(op1);
                txtView_Operator.setText(operator);
                resultView_operand2.setText(op2);
                resultView_Answer.setText(result);
                resultView_operand.requestFocus();
                break;

                //Delete Button
            case R.id.button_c:

                if(resultView_operand.isFocused() && !isEmpty(resultView_operand)){
                    op1 = op1.substring(0, op1.length()-1);
                    resultView_operand.setText(op1);
                }else if(resultView_operand2.isFocused() && !isEmpty(resultView_operand2)){
                    op2 = op2.substring(0, op2.length()-1);
                    resultView_operand2.setText(op2);
                }

                break;

            case R.id.button_equals:
                if(!isEmpty(resultView_operand)&&!isEmpty(resultView_operand2)){
                    ans = calculate(op1,op2,operator);
                }
                formatter = new DecimalFormat("#,###,##0.00");
                result = formatter.format(ans);
                Log.d("answer",Double.toString(ans));
                Log.d("formattedAns",result);

                resultView_Answer.setText(result);
                break;

        }
    }
}
