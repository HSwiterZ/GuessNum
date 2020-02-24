package com.example.figureguessinggame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.*;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.*;
public class MainActivity extends AppCompatActivity {
    int random_num = 0;
    int user_num = 0;
    TextView resultText;
    EditText inputText;
    Button cnf_btn_click, get_btn_click;
    String bigger = " is bigger than the answer.";
    String smaller = " is smaller than the answer.";
    String answer = " is the answer, congratulation!";
    String big_error = " is more than 1000, error!";
    String small_error = " is less than 0, error!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = (EditText) findViewById(R.id.Answer_Input);
        resultText = (TextView) findViewById(R.id.Result_Show);
        get_btn_click = (Button) findViewById(R.id.GetNum_Button);
        cnf_btn_click = (Button) findViewById(R.id.Confirm_Button);

        get_btn_click.setOnClickListener(GuessProgram);
        cnf_btn_click.setOnClickListener(GuessProgram);
        inputText.addTextChangedListener(textWatch);
    }

    TextWatcher textWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        //一般我们都是在这个里面进行我们文本框的输入的判断，上面两个方法用到的很少
        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                if (Integer.parseInt(inputText.getText().toString()) > 1000)
                    cnf_btn_click.setVisibility(View.INVISIBLE);
                else
                    cnf_btn_click.setVisibility(View.VISIBLE);
            }
        }
    };
    OnClickListener GuessProgram = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.GetNum_Button:
                    if((user_num == random_num)||(random_num == 0)) {
                        random_num = (int) (Math.random() * 1000);
                        resultText.setText("Random number generated");
                    }
                    else
                    {
                        resultText.setText("You haven't guess the answer!");
                    }
                    break;
                case R.id.Confirm_Button:
                    String result;
                    if(inputText.length()!=0) {
                        user_num = Integer.parseInt(inputText.getText().toString());
                        if (user_num < random_num) {
                            if (user_num < 0) {
                                result = small_error;
                            } else {
                                result = smaller;
                            }
                        } else if (user_num > random_num) {
                            if (user_num > 1000) {
                                result = big_error;
                            } else {
                                result = bigger;
                            }
                        } else {
                            result = answer;
                        }
                    }
                    else{
                        result = "Please input a number you guess!";
                    }
                    resultText.setText(inputText.getText().toString()+result);
                    break;
            }
        }
    };
}
