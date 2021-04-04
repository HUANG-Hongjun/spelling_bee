package com.example.spelling_bee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            user_name=extras.getString("userName");
            setTitle("Hello, "+user_name);
        }

    }

    public void logOutButtonClicked(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
        builder.setTitle(android.R.string.dialog_alert_title);
        builder.setMessage("Are you sure to log out?");
        builder.setNegativeButton(android.R.string.no,null);
        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Menu.this,MainActivity.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void addButtonClicked(View view){
        Intent intent = new Intent(Menu.this, Add_vocabularies.class);
        intent.putExtra("user_name",user_name);
        startActivity(intent);

    }


}