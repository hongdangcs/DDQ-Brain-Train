package com.ddq.braintrain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

}
