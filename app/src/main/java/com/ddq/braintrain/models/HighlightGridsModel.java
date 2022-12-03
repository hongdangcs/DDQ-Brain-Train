package com.ddq.braintrain.models;

public class HighlightGridsModel {

    private int level;
    private int tiles;
    private int gridx;
    private int gridy;
    private int score;
    private int completeStatus;

    public HighlightGridsModel(int level, int tiles, int gridx, int gridy, int score, int completeStatus) {
        this.level = level;
        this.tiles = tiles;
        this.gridx = gridx;
        this.gridy = gridy;
        this.score = score;
        this.completeStatus = completeStatus;
    }

    public HighlightGridsModel() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTiles() {
        return tiles;
    }

    public void setTiles(int tiles) {
        this.tiles = tiles;
    }

    public int getGridx() {
        return gridx;
    }

    public void setGridx(int gridx) {
        this.gridx = gridx;
    }

    public int getGridy() {
        return gridy;
    }

    public void setGridy(int gridy) {
        this.gridy = gridy;
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
