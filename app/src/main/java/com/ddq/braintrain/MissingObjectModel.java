package com.ddq.braintrain;

public class MissingObjectModel {
    private int level;
    private int numberOfCards;
    private int hideCard;
    private int score;
    private int time;
    private int completeStatusEasy;
    private int completeStatusMedium;
    private int completeStatusHard;

    public MissingObjectModel(int level, int numberOfCards, int hideCard, int score, int time, int completeStatusEasy, int completeStatusMedium, int completeStatusHard) {
        this.level = level;
        this.numberOfCards = numberOfCards;
        this.hideCard = hideCard;
        this.score = score;
        this.time = time;
        this.completeStatusEasy = completeStatusEasy;
        this.completeStatusMedium = completeStatusMedium;
        this.completeStatusHard = completeStatusHard;
    }

    public MissingObjectModel() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    public int getHideCard() {
        return hideCard;
    }

    public void setHideCard(int hideCard) {
        this.hideCard = hideCard;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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
