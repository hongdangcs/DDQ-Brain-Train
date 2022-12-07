package com.ddq.braintrain.models;

public class SortingCharGameModel {
    private int id;
    private String word;
    private int complete_status;

    public SortingCharGameModel(int id, String word, int complete_status) {
        this.id = id;
        this.word = word;
        this.complete_status = complete_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getComplete_status() {
        return complete_status;
    }

    public void setComplete_status(int complete_status) {
        this.complete_status = complete_status;
    }

    @Override
    public String toString() {
        return "SortingCharGameModel{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", complete_status=" + complete_status +
                '}';
    }
}
