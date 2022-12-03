package com.ddq.braintrain.models;

public class SharkBoatModel {
    int level, numberOfShark, numberOfBoat, pointPerBoat, levelPassScore, allowableNumberOfBite, score, completeStatus;

    public SharkBoatModel() {
    }

    public SharkBoatModel(int level, int numberOfShark, int numberOfBoat, int pointPerBoat, int levelPassScore, int allowableNumberOfBite, int score, int completeStatus) {
        this.level = level;
        this.numberOfShark = numberOfShark;
        this.numberOfBoat = numberOfBoat;
        this.pointPerBoat = pointPerBoat;
        this.levelPassScore = levelPassScore;
        this.allowableNumberOfBite = allowableNumberOfBite;
        this.score = score;
        this.completeStatus = completeStatus;

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNumberOfShark() {
        return numberOfShark;
    }

    public void setNumberOfShark(int numberOfShark) {
        this.numberOfShark = numberOfShark;
    }

    public int getNumberOfBoat() {
        return numberOfBoat;
    }

    public void setNumberOfBoat(int numberOfBoat) {
        this.numberOfBoat = numberOfBoat;
    }

    public int getPointPerBoat() {
        return pointPerBoat;
    }

    public void setPointPerBoat(int pointPerBoat) {
        this.pointPerBoat = pointPerBoat;
    }

    public int getLevelPassScore() {
        return levelPassScore;
    }

    public void setLevelPassScore(int levelPassScore) {
        this.levelPassScore = levelPassScore;
    }

    public int getAllowableNumberOfBite() {
        return allowableNumberOfBite;
    }

    public void setAllowableNumberOfBite(int allowableNumberOfBite) {
        this.allowableNumberOfBite = allowableNumberOfBite;
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
