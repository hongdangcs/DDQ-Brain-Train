package com.ddq.braintrain.models;

public class DifferentModel {
    int imageID, completeStatus;
    String imageName, image;
    int xCoordinate, yCoordinate;

    public DifferentModel() {
    }

    public DifferentModel(int imageID, String imageName, String image, int completeStatus) {
        this.imageID = imageID;
        this.completeStatus = completeStatus;
        this.imageName = imageName;
        this.image = image;
    }

    public DifferentModel(int imageID, String imageName, String image, int completeStatus, int xCoordinate, int yCoordinate) {
        this.imageID = imageID;
        this.completeStatus = completeStatus;
        this.imageName = imageName;
        this.image = image;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(int completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
