/**
 * Update activity
 * if the spelling is not in user's word bank, it will be added
 * if it's already exist, it will be updated
 * ISE-OC
 * Author : HUANG Hongjun && YUAN Mengcheng
 * ESIGELEC
 */
package com.example.spelling_bee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Update_vocabularies extends AppCompatActivity {

    private EditText spelling;
    private EditText meaning;
    private String user_name;
    private String spellingToUpdate;
    private  String meaningToUpdate;
    private boolean wordExist;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_vocabularies);
        setTitle("Add vocabulary");
        spelling = findViewById(R.id.Add_spelling);
        meaning = findViewById(R.id.add_meaning);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            user_name=extras.getString("user_name");
            spellingToUpdate=extras.getString("spelling");
            meaningToUpdate=extras.getString("meaning");
            spelling.setText(spellingToUpdate);
            meaning.setText(meaningToUpdate);
        }

    }

    public void AddButtonClicked(View view){
        Intent intent = getIntent();
        int i;
        setResult(RESULT_OK,intent);
        spelling = findViewById(R.id.Add_spelling);
        meaning = findViewById(R.id.add_meaning);
        Vocabulary newWord = new Vocabulary(user_name,spelling.getText().toString(),meaning.getText().toString());
        //test if word is already exist
        wordExist = false;
        for(i=0;i<VocabList.getInstance().vocabList.size();i++){
            if(VocabList.getInstance().vocabList.get(i).getSpelling().equals(newWord.getSpelling())&&
                    VocabList.getInstance().vocabList.get(i).getUser_name().equals(newWord.getUser_name())){
                wordExist=true;
                position=i;
            }
        }
        if (!wordExist){
            VocabList.getInstance().vocabList.add(newWord);
            VocabList.getInstance().saveContactsToFile(Update_vocabularies.this);
            finish();
        }
        else{
            VocabList.getInstance().vocabList.set(position,newWord);
            VocabList.getInstance().saveContactsToFile(Update_vocabularies.this);
            finish();
        }

    }

    public void CancleButtonClicked(View view){
        finish();
    }
}