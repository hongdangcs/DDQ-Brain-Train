package com.ddq.braintrain;

public class NotInPreviousModel {

    private int level;
    private int numberOfItems;
    private int completedStatus;

    public NotInPreviousModel() {
    }

    public NotInPreviousModel(int level, int numberOfItems, int completedStatus) {
        this.level = level;
        this.numberOfItems = numberOfItems;
        this.completedStatus = completedStatus;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public int getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(int completedStatus) {
        this.completedStatus = completedStatus;
    }
}
