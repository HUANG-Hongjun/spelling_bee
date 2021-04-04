package com.example.spelling_bee;

public class Vocabulary {
    private String user_name;
    private String spelling;
    private String meaning;


    public Vocabulary(String user_name, String spelling, String meaning) {
        this.user_name = user_name;
        this.spelling = spelling;
        this.meaning = meaning;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
