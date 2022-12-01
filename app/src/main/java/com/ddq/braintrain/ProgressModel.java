package com.ddq.braintrain;

public class ProgressModel {
    private int id;
    private int maxScore;
    private int userScore;
    private boolean completedStatus;

    public ProgressModel() {
    }

    public ProgressModel(int id, int maxScore, int userScore, boolean completedStatus) {
        this.id = id;
        this.maxScore = maxScore;
        this.userScore = userScore;
        this.completedStatus = completedStatus;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public boolean isCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(boolean completedStatus) {
        this.completedStatus = completedStatus;
    }

    @Override
    public String toString() {
        return "ProgressModel{" +
                "id=" + id +
                ", maxScore=" + maxScore +
                ", userScore=" + userScore +
                ", completedStatus=" + completedStatus +
                '}';
    }
}
