package com.example.henrik.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Henrik on 11/24/2017.
 */

public class EndActivity extends AppCompatActivity implements View.OnClickListener{
    int numCorrect;
    TextView result;
    Button restart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        numCorrect = getIntent().getIntExtra("numCorrect", 0);
        restart = findViewById(R.id.restart_button);
        result = findViewById(R.id.result_text);
        result.setText(numCorrect + "/ 5 Correct");
        restart.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
