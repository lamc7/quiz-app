package com.example.henrik.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button stringButton;
    Button arrayButton;
    Button miscButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stringButton = findViewById(R.id.string_button);
        arrayButton = findViewById(R.id.array_button);
        miscButton = findViewById(R.id.misc_button);
        stringButton.setOnClickListener(this);
        arrayButton.setOnClickListener(this);
        miscButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        Intent intent = new Intent(this,QuestionActivity.class);
        if (view.getId() == R.id.string_button){
            intent.putExtra("topic", "string");
        }
        else if (view.getId() == R.id.array_button){
            intent.putExtra("topic", "array");
        }
        else if (view.getId() == R.id.misc_button){
            intent.putExtra("topic", "misc");
        }
        startActivity(intent);
    }
}
