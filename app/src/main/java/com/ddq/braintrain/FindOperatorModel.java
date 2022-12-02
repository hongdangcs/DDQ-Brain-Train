package com.ddq.braintrain;

public class FindOperatorModel {
    private int level, option, time, point, completeStatus;

    public FindOperatorModel() {
    }

    public FindOperatorModel(int level, int option, int time, int point, int completeStatus) {
        this.level = level;
        this.option = option;
        this.time = time;
        this.point = point;
        this.completeStatus = completeStatus;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(int completeStatus) {
        this.completeStatus = completeStatus;
    }
}
