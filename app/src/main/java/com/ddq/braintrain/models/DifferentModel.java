package com.ddq.braintrain.models;

public class DifferentModel {
    int imageID, completeStatus;
    String imageName, image;

    public DifferentModel() {
    }

    public DifferentModel(int imageID, String imageName, String image, int completeStatus) {
        this.imageID = imageID;
        this.completeStatus = completeStatus;
        this.imageName = imageName;
        this.image = image;
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
}
