package com.example.spelling_bee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText user_name ;
    private EditText user_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //load accounts information
        AccountList.getInstance().loadContactsFromFile(MainActivity.this);

    }

    public void createButtonClicked(View view){
        //go to create_account activity
        Intent intent = new Intent(
        MainActivity.this, Create_account.class);
        startActivity(intent);

    }

    public void loginButtonClicked(View view){
        user_name=findViewById(R.id.log_name);
        user_password=findViewById(R.id.log_password);
        String name =user_name.getText().toString();
        String password = user_password.getText().toString();
        boolean idExist = false;
        boolean passwordRight = false;
        int i;
        //check id and password
        for(i=0;i<AccountList.getInstance().accountList.size();i++){
            if(name.equals(AccountList.getInstance().accountList.get(i).getName())) {
                idExist = true;
                if ( password.equals(AccountList.getInstance().accountList.get(i).getPassword())){
                    passwordRight = true;
                }
            }
        }
        //all right
        if(idExist&&passwordRight){
            Intent intent = new Intent(
                    MainActivity.this, Menu.class);
            intent.putExtra("userName",name);
            startActivity(intent);
        }
        //no account
        else if (!idExist){
            Toast.makeText(MainActivity.this, "user id does not exist", Toast.LENGTH_LONG).show();
        }
        //password incorrect
        else{
            Toast.makeText(MainActivity.this, "password incorrect", Toast.LENGTH_LONG).show();
        }
    }

    /*
    * cleaer the information in the EditText when user is back */
    @Override
    protected void onResume() {
        super.onResume();
        user_name=findViewById(R.id.log_name);
        user_password=findViewById(R.id.log_password);
        user_name.setText("");
        user_password.setText("");
    }


}