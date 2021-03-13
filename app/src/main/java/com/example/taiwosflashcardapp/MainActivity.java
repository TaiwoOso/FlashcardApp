package com.example.taiwosflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String Q = ((TextView) findViewById(R.id.flashcard_question)).getText().toString();
        String A = ((TextView) findViewById(R.id.flashcard_answer)).getText().toString();

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        flashcardDatabase.insertCard(new Flashcard(Q, A));
        allFlashcards = flashcardDatabase.getAllCards();
        idx = 0;

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }

        TextView button = findViewById(R.id.button);
        TextView question = findViewById(R.id.flashcard_question);
        TextView answer = findViewById(R.id.flashcard_answer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.getText().equals("Click to view answer")) {
                    question.setVisibility(View.INVISIBLE);
                    answer.setVisibility(View.VISIBLE);
                    button.setText("Click to view question");
                } else {
                    question.setVisibility(View.VISIBLE);
                    answer.setVisibility(View.INVISIBLE);
                    button.setText("Click to view answer");
                }
            }
        });

        findViewById(R.id.plus_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFlashcards != null && idx < allFlashcards.size()) {
                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(idx).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(idx).getAnswer());
                    idx = idx + 1;
                }
            }
        });

        findViewById(R.id.trash_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView)findViewById(R.id.flashcard_question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();
                if (allFlashcards != null && allFlashcards.size() > 0) {
                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
                } else {
                    ((TextView) findViewById(R.id.flashcard_question)).setText("");
                    ((TextView) findViewById(R.id.flashcard_answer)).setText("");
                }
                idx = 0;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");

            flashcardDatabase.insertCard(new Flashcard(question, answer));
            allFlashcards = flashcardDatabase.getAllCards();

        }
    }
}