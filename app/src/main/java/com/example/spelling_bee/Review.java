/**
 * Review activity
 * user here can try to spell all the word that in his word bank
 * if he does not know how to spell it, he can click help to see the answer
 * if all the words have been shown once, it will start again with the first word
 * ISE-OC
 * Author : HUANG Hongjun && YUAN Mengcheng
 * ESIGELEC
 */
package com.example.spelling_bee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Review extends AppCompatActivity {
    private String user_name;
    private ArrayList<Vocabulary> userWordList;
    private EditText spelling;
    private TextView meaning;
    private int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            user_name=extras.getString("user_name");
        }
        setTitle("Review");
        loadUserWord();
        spelling=findViewById(R.id.review_spelling);
        meaning=findViewById(R.id.review_meaning);
        updateWord();
    }

    //get all the words that belongs to this user
    public void loadUserWord(){
        int i;
        userWordList = new ArrayList<>();
        for(i=0;i<VocabList.getInstance().vocabList.size();i++){
            if(user_name.equals(VocabList.getInstance().vocabList.get(i).getUser_name())){
                userWordList.add(VocabList.getInstance().vocabList.get(i));
            }
        }
    }

    //update two texts
    public void updateWord(){
        spelling.setText("");
        meaning.setText(userWordList.get(position).getMeaning());
    }

    //show how to spell it
    public void helpButtonClicked(View view){
        spelling.setText(userWordList.get(position).getSpelling());
        Toast.makeText(Review.this, "You need to work harder!", Toast.LENGTH_SHORT).show();
    }

    public void nextButtonClicked(View view){
        //next word
        position++;
        if(position<userWordList.size()){
            updateWord();
        }
        //if there is no more word, back to the head
        else{
            Toast.makeText(Review.this, "Let's do it again!", Toast.LENGTH_SHORT).show();
            position=0;
        }
        updateWord();
    }

    public void checkButtonClicked(View view){
        //if right go to next
        if(spelling.getText().toString().equals(userWordList.get(position).getSpelling())){
            Toast.makeText(Review.this, "You are right!", Toast.LENGTH_SHORT).show();
            position++;
            if(position<userWordList.size()){
                updateWord();
            }
            else{
                Toast.makeText(Review.this, "Let's do it again!", Toast.LENGTH_SHORT).show();
                position=0;
            }
            updateWord();
        }
        //f wrong stay here
        else{
            Toast.makeText(Review.this, "Ohhhh you are wrong!", Toast.LENGTH_SHORT).show();
        }
    }
}