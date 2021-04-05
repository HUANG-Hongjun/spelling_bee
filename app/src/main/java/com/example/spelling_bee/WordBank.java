/**
 * Word bank activity
 * show all the words of user
 * click add button can go to update activity
 * short click one word can also go to update activity
 * long click one word can delete it
 * ISE-OC
 * Author : HUANG Hongjun && YUAN Mengcheng
 * ESIGELEC
 */
package com.example.spelling_bee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordBank extends AppCompatActivity {


    private ListView listView;  //list of words
    private String user_name;   //user id
    private ArrayList<Vocabulary> userWordList; //user's own words list
    final int RESULT_TO_ADD_ACTIVITY = 1001;    //add result
    final int RESULT_TO_UPDATE = 1002;     //update result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_bank);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            user_name=extras.getString("user_name");
        }
        setTitle("Word Bank");
        //VocabList.getInstance().clearData();
        //VocabList.getInstance().saveContactsToFile(WordBank.this);
        loadUserWord();
        listView =findViewById(R.id.wordList);
        LayoutInflater inflater =getLayoutInflater();
        WordAdapter adapter = new WordAdapter(inflater,userWordList);
        listView.setAdapter(adapter);
        //update a word by short click, go to update activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int i, long l) {
                Intent intent = new Intent(
                        WordBank.this,
                        Update_vocabularies.class);
                intent.putExtra("user_name",user_name);
                intent.putExtra("spelling",userWordList.get(i).getSpelling());
                intent.putExtra("meaning",userWordList.get(i).getMeaning());
                startActivityForResult(intent,RESULT_TO_UPDATE);
            }
        });
        //delete one word by long click
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(
                            WordBank.this);
                builder.setTitle(android.R.string.dialog_alert_title);
                builder.setMessage("Do you want to delete this word?");
                builder.setNegativeButton(android.R.string.no,null);
                builder.setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                VocabList.getInstance().vocabList.remove(position);
                                updateList();
                                VocabList.getInstance().saveContactsToFile(WordBank.this);
                                Toast.makeText(WordBank.this, "Delete successful!", Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    //go to update activity
    public void addButtonClicked(View view){
        Intent intent = new Intent(WordBank.this, Update_vocabularies.class);
        intent.putExtra("user_name",user_name);
        startActivityForResult(intent,RESULT_TO_ADD_ACTIVITY);
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

    //when add or update is finished, update the list
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_TO_ADD_ACTIVITY){
            if(resultCode == RESULT_OK){
                updateList();
            }
        }
        else if(requestCode == RESULT_TO_UPDATE){
            if(resultCode == RESULT_OK){
                updateList();
                Toast.makeText(WordBank.this, "Update successful!", Toast.LENGTH_LONG).show();
            }
        }
    }

    //update the list
    public void updateList(){
        loadUserWord();
        listView =findViewById(R.id.wordList);
        LayoutInflater inflater =getLayoutInflater();
        WordAdapter adapter = new WordAdapter(inflater,userWordList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //back to menu activity
    public void backButtonClicked(View view){
        finish();
    }
}