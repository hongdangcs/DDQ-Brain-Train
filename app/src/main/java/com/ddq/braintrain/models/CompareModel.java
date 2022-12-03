package com.ddq.braintrain.models;

public class CompareModel {
    private int level;
    private int score;
    private int completeStatus;

    public CompareModel(int level, int score, int completeStatus) {
        this.level = level;
        this.score = score;
        this.completeStatus = completeStatus;
    }

    public CompareModel() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(int completeStatus) {
        this.completeStatus = completeStatus;
    }
}
