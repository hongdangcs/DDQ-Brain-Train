package com.ddq.braintrain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ddq.braintrain.models.CompareModel;
import com.ddq.braintrain.models.DifferentModel;
import com.ddq.braintrain.models.FindOperatorModel;
import com.ddq.braintrain.models.FlashCardModel;
import com.ddq.braintrain.models.HighlightGridsModel;
import com.ddq.braintrain.models.MissingObjectModel;
import com.ddq.braintrain.models.NotInPreviousModel;
import com.ddq.braintrain.models.ProgressModel;
import com.ddq.braintrain.models.SharkBoatModel;

import java.util.ArrayList;
import java.util.List;

public class BrainTrainDAO {

    public ProgressModel getProgressStatus(BrainTrainDatabase db, int gameID) {
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String query = "select * from progress where game_id = " + gameID;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null)
            cursor.moveToFirst();
        int maxScore = cursor.getInt(2);
        int userScore = cursor.getInt(3);
        boolean completeStatus = cursor.getInt(4)==1;
        ProgressModel model = new ProgressModel(gameID, maxScore, userScore, completeStatus);
        return model;
    }

    public List<HighlightGridsModel> highlightGridsModels(BrainTrainDatabase db){
        List<HighlightGridsModel> returnList = new ArrayList<>();

         int level;
         int tiles;
         int gridx;
         int gridy;
         int score;
         int completeStatus;

        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String query = "select * from memory_game_one";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                level = cursor.getInt(0);
                tiles = cursor.getInt(1);
                gridx = cursor.getInt(2);
                gridy = cursor.getInt(3);
                score = cursor.getInt(4);
                completeStatus = cursor.getInt(5);

                returnList.add(new HighlightGridsModel(level, tiles, gridx, gridy, score, completeStatus));

            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }

    public List<NotInPreviousModel> notInPreviousModels(BrainTrainDatabase db){
        List<NotInPreviousModel> returnList = new ArrayList<>();

        int level;
        int numberOfItems;
        int completeStatus;

        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String query = "select * from memory_game_two";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                level = cursor.getInt(0);
                numberOfItems = cursor.getInt(1);
                completeStatus = cursor.getInt(2);

                returnList.add(new NotInPreviousModel(level, numberOfItems, completeStatus));

            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }

    public List<MissingObjectModel> missingObjectModels(BrainTrainDatabase db){
        List<MissingObjectModel> returnList = new ArrayList<>();

         int level;
         int numberOfCards;
         int hideCard;
         int score;
         int time;
         int completeStatusEasy;
         int completeStatusMedium;
         int completeStatusHard;

        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String query = "select * from memory_game_three";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                level = cursor.getInt(0);
                numberOfCards = cursor.getInt(1);
                hideCard = cursor.getInt(2);
                score = cursor.getInt(3);
                time = cursor.getInt(4);
                completeStatusEasy = cursor.getInt(5);
                completeStatusMedium = cursor.getInt(6);
                completeStatusHard = cursor.getInt(7);

                returnList.add(new MissingObjectModel(level, numberOfCards, hideCard, score, time, completeStatusEasy, completeStatusMedium, completeStatusHard));

            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }

    public List<CompareModel> compareModels(BrainTrainDatabase db){
        List<CompareModel> returnList = new ArrayList<>();

        int level;
        int score;
        int completeStatus;

        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String query = "select * from math_game_one";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                level = cursor.getInt(0);
                score = cursor.getInt(1);
                completeStatus = cursor.getInt(2);

                returnList.add(new CompareModel(level, score, completeStatus));

            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }

    public List<FindOperatorModel> findOperatorOfTenModels(BrainTrainDatabase db){
        List<FindOperatorModel> returnList = new ArrayList<>();

        int level, option, time, point, completeStatus;

        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String query = "select * from math_game_two_multiple_of_ten";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                level = cursor.getInt(0);
                option = cursor.getInt(1);
                time = cursor.getInt(2);
                point = cursor.getInt(3);
                completeStatus = cursor.getInt(4);

                returnList.add(new FindOperatorModel(level,option,time,point, completeStatus));

            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }

    public List<FindOperatorModel> findOperatorOfHundredModels(BrainTrainDatabase db){
        List<FindOperatorModel> returnList = new ArrayList<>();

        int level, option, time, point, completeStatus;

        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String query = "select * from math_game_two_multiple_of_hundred";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                level = cursor.getInt(0);
                option = cursor.getInt(1);
                time = cursor.getInt(2);
                point = cursor.getInt(3);
                completeStatus = cursor.getInt(4);

                returnList.add(new FindOperatorModel(level,option,time,point, completeStatus));

            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }
    public List<FindOperatorModel> findOperatorOfThousandModels(BrainTrainDatabase db){
        List<FindOperatorModel> returnList = new ArrayList<>();

        int level, option, time, point, completeStatus;

        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String query = "select * from math_game_two_multiple_of_thousand";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                level = cursor.getInt(0);
                option = cursor.getInt(1);
                time = cursor.getInt(2);
                point = cursor.getInt(3);
                completeStatus = cursor.getInt(4);

                returnList.add(new FindOperatorModel(level,option,time,point, completeStatus));

            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }

    public List<DifferentModel> differentModels(BrainTrainDatabase db){
        List<DifferentModel> returnList = new ArrayList<>();

        int imageID, completeStatus;
        String imageName, image;

        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String query = "select * from attention_game_one";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                imageID = cursor.getInt(0);
                imageName = cursor.getString(1);
                image = cursor.getString(2);
                completeStatus = cursor.getInt(3);

                returnList.add(new DifferentModel(imageID,imageName,image, completeStatus));

            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }

    public List<FlashCardModel> flashCardModels(BrainTrainDatabase db){
        List<FlashCardModel> returnList = new ArrayList<>();

        int level, pair, time, score, completeStatusEasy, completeStatusMedium, completeStatusHard;

        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String query = "select * from attention_game_two";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                level = cursor.getInt(0);
                pair = cursor.getInt(1);
                time = cursor.getInt(2);
                score = cursor.getInt(3);
                completeStatusEasy = cursor.getInt(4);
                completeStatusMedium = cursor.getInt(5);
                completeStatusHard = cursor.getInt(6);

                returnList.add(new FlashCardModel(level, pair, time, score, completeStatusEasy, completeStatusMedium, completeStatusHard));

            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }

    public List<SharkBoatModel> sharkBoatModels(BrainTrainDatabase db){
        List<SharkBoatModel> returnList = new ArrayList<>();

        int level, numberOfShark, numberOfBoat, pointPerBoat, levelPassScore, allowableNumberOfBite, score, completeStatus;

        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String query = "select * from attention_game_three";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                level = cursor.getInt(0);
                numberOfShark = cursor.getInt(1);
                numberOfBoat = cursor.getInt(2);
                pointPerBoat = cursor.getInt(3);
                levelPassScore = cursor.getInt(4);
                allowableNumberOfBite = cursor.getInt(5);
                score = cursor.getInt(6);
                completeStatus = cursor.getInt(7);

                returnList.add(new SharkBoatModel(level, numberOfShark, numberOfBoat, pointPerBoat, levelPassScore, allowableNumberOfBite, score, completeStatus));

            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }

}
