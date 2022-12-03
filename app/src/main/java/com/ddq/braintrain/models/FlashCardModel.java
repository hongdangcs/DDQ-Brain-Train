package com.ddq.braintrain.models;

public class FlashCardModel {
    int level, pair, time, score, completeStatusEasy, completeStatusMedium, completeStatusHard;

    public FlashCardModel() {
    }

    public FlashCardModel(int level, int pair, int time, int score, int completeStatusEasy, int completeStatusMedium, int completeStatusHard) {
        this.level = level;
        this.pair = pair;
        this.time = time;
        this.score = score;
        this.completeStatusEasy = completeStatusEasy;
        this.completeStatusMedium = completeStatusMedium;
        this.completeStatusHard = completeStatusHard;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPair() {
        return pair;
    }

    public void setPair(int pair) {
        this.pair = pair;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCompleteStatusEasy() {
        return completeStatusEasy;
    }

    public void setCompleteStatusEasy(int completeStatusEasy) {
        this.completeStatusEasy = completeStatusEasy;
    }

    public int getCompleteStatusMedium() {
        return completeStatusMedium;
    }

    public void setCompleteStatusMedium(int completeStatusMedium) {
        this.completeStatusMedium = completeStatusMedium;
    }

    public int getCompleteStatusHard() {
        return completeStatusHard;
    }

    public void setCompleteStatusHard(int completeStatusHard) {
        this.completeStatusHard = completeStatusHard;
    }
}
