package com.ddq.braintrain.models;

public class CompareModel {
    private int level;
    private int score;
    private String Expression1;
    private String Expression2;
    private int ExpressionResult1;
    private int ExpressionResult2;
    private int completeStatus;

    public CompareModel(int level, int score, String Expression1, String Expression2, int ExpressionResult1, int ExpressionResult2, int completeStatus) {
        this.level = level;
        this.score = score;
        this.Expression1 = Expression1;
        this.Expression2 = Expression2;
        this.ExpressionResult1 = ExpressionResult1;
        this.ExpressionResult2 = ExpressionResult2;
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

    public String getExpression1() {
        return Expression1;
    }

    public String getExpression2() {
        return Expression2;
    }

    public int getExpressionResult1() {
        return ExpressionResult1;
    }

    public int getExpressionResult2() {
        return ExpressionResult2;
    }

    @Override
    public String toString() {
        return "CompareModel{" +
                "level=" + level +
                ", score=" + score +
                ", Expression1='" + Expression1 + '\'' +
                ", Expression2='" + Expression2 + '\'' +
                ", ExpressionResult1=" + ExpressionResult1 +
                ", ExpressionResult2=" + ExpressionResult2 +
                ", completeStatus=" + completeStatus +
                '}';
    }
}
