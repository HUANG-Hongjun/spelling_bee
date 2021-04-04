package com.example.spelling_bee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Create_account extends AppCompatActivity {

    EditText add_name;
    EditText add_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        setTitle("Create Account");
    }



    public void createButtonClicked(View view){
        add_name=findViewById(R.id.add_name);
        add_password=findViewById(R.id.add_password);
        boolean idExist =false;
        int i;

        if((!add_name.getText().toString().equals(""))&&(!add_password.getText().toString().equals(""))){
            //test if id already exist
            for(i=0;i<AccountList.getInstance().accountList.size();i++){
                if(add_name.getText().toString().equals(AccountList.getInstance().accountList.get(i).getName())) {
                    idExist = true;
                }
            }
            if(idExist){
                Toast.makeText(Create_account.this, "user id already exist", Toast.LENGTH_LONG).show();
            }
            else{
                //verify user want to create
                AlertDialog.Builder builder = new AlertDialog.Builder(Create_account.this);
                builder.setTitle(android.R.string.dialog_alert_title);
                builder.setMessage("Are you sure to create this account?");
                builder.setNegativeButton(android.R.string.no,null);
                builder.setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //create account
                                Account add_account =new Account(add_name.getText().toString(),add_password.getText().toString());
                                AccountList.getInstance().accountList.add(add_account);
                                AccountList.getInstance().saveContactsToFile(Create_account.this);
                                finish();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
        else if (add_name.getText().toString().equals("")){
            Toast.makeText(Create_account.this, "user id can't be null", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(Create_account.this, "password can't be null", Toast.LENGTH_LONG).show();
        }


    }
}