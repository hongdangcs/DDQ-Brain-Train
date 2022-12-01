package com.ddq.braintrain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BrainTrainDAO {

    public ProgressModel getProgressStatus(BrainTrainDatabase db) {
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String query = "select * from progress";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int id = cursor.getInt(0);
        int maxScore = cursor.getInt(2);
        int userScore = cursor.getInt(3);
        boolean completeStatus = cursor.getInt(4)==0;
        ProgressModel model = new ProgressModel(id, maxScore, userScore, completeStatus);
        return model;
    }
    public int getInt(BrainTrainDatabase fd){
        SQLiteDatabase sqLiteDatabase = fd.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from progress where game_id = 11", null);
        return cursor.getCount();
    }
}
