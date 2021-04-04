package com.example.spelling_bee;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class VocabList {
    private static VocabList instance = new VocabList();
    public ArrayList<Vocabulary> vocabList = new ArrayList<>();


    public VocabList(){

    }

    public static VocabList getInstance(){
        return instance;
    }

    public void saveContactsToFile(Context context){
        int i;
        try {
            OutputStream stream = context.openFileOutput(
                    "vocabulary.txt",
                    Context.MODE_PRIVATE
            );
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            for (i=0;i<vocabList.size();i++) {
                writer.write(vocabList.get(i).getUser_name()+";"+
                        vocabList.get(i).getSpelling()+";"+vocabList.get(i).getMeaning()+"\n");
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
            InputStream inputStream = context.openFileInput("vocabulary.txt");
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine())!=null){
                String []vocab = line.split(";");
                vocabList.add(
                        new Vocabulary(vocab[0],vocab[1],vocab[2])
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
