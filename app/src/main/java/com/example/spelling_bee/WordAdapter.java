/**
 * A class of adapter
 * used to adapte a word with the listview
 * Author : HUANG Hongjun && YUAN Mengcheng
 * ISE-OC
 * ESIGELEC
 */
package com.example.spelling_bee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends BaseAdapter {
    private ArrayList<Vocabulary> vocabList;
    private LayoutInflater Inflater;

    public WordAdapter(LayoutInflater inflater,ArrayList<Vocabulary> data){
        Inflater = inflater;
        vocabList = data;
    }

    @Override
    public int getCount() {
        return vocabList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewlist = Inflater.inflate(R.layout.singleword,null);
        Vocabulary vocabulary =vocabList.get(position);
        TextView word =(TextView)viewlist.findViewById(R.id.single_spelling);
        TextView meaning = (TextView)viewlist.findViewById(R.id.single_meaning);
        word.setText(vocabulary.getSpelling());
        meaning.setText(vocabulary.getMeaning());
        return viewlist;
    }
}
