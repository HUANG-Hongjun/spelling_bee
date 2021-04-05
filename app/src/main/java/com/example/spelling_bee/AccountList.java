/**
 * A class of all the users
 * Author : HUANG Hongjun && YUAN Mengcheng
 * ISE-OC
 * ESIGELEC
 */
package com.example.spelling_bee;

import android.app.Application;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class AccountList {
    public ArrayList<Account> accountList = new ArrayList<>();
    private static AccountList instance = new AccountList();

    public AccountList(){

    }

    public static AccountList getInstance(){
        return instance;
    }



    public void saveContactsToFile(Context context){
        int i;
        try {
            OutputStream stream = context.openFileOutput(
                    "account.txt",
                    Context.MODE_PRIVATE
            );
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            for (i=0;i<accountList.size();i++) {
                writer.write(accountList.get(i).getName()+";"+
                        accountList.get(i).getPassword()+"\n");
            }
            writer.flush();
            writer.close();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadContactsFromFile(Context context){
        try {
            InputStream inputStream = context.openFileInput("account.txt");
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine())!=null){
                String []account = line.split(";");
                accountList.add(
                        new Account(account[0],account[1])
                );
            }
            reader.close();
            streamReader.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
