package com.ddq.braintrain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

}
