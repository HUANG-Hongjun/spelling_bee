/**
 * Menu activity
 * can go to word bank, review, or log out (back to the login activity)
 * Author : HUANG Hongjun && YUAN Mengcheng
 * ISE-OC
 * ESIGELEC
 */
package com.example.spelling_bee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    String user_name;
    private ArrayList<Vocabulary> userWordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            user_name=extras.getString("user_name");
            setTitle("Hello, "+user_name);
        }
        VocabList.getInstance().loadContactsFromFile(Menu.this);

    }

    public void logOutButtonClicked(View view){
        //ask if the user is sure to quit
        AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
        builder.setTitle(android.R.string.dialog_alert_title);
        builder.setMessage("Are you sure to log out?");
        builder.setNegativeButton(android.R.string.no,null);
        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        VocabList.getInstance().clearData();
                        Intent intent = new Intent(Menu.this,MainActivity.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //gp to word bank activity
    public void wordBankButtonClicked(View view){
        Intent intent = new Intent(Menu.this, WordBank.class);
        intent.putExtra("user_name",user_name);
        startActivity(intent);
    }

    //go to review activity
    public void reviewButtonClicked(View view){
        loadUserWord();
        if(userWordList.size()!=0){
            Intent intent = new Intent(Menu.this, Review.class);
            intent.putExtra("user_name",user_name);
            startActivity(intent);
        }
        else{
            Toast.makeText(Menu.this, "No vocabulary for you to review!", Toast.LENGTH_SHORT).show();
        }

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


}