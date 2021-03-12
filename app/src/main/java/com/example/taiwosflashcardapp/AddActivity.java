package com.example.taiwosflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        findViewById(R.id.exit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.download_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((EditText) findViewById(R.id.input_question)).getText().toString();
                String answer = ((EditText) findViewById(R.id.input_answer)).getText().toString();
                Intent data = new Intent();
                data.putExtra("question", question);
                data.putExtra("answer", answer);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }


}