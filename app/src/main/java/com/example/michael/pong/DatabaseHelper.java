package com.example.michael.pong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ScoreBoard";
    private static final String TABLE_SCORES = "scores";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "score";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SCORES_TABLE = "CREATE TABLE " + TABLE_SCORES + " ( "
                + KEY_NAME + " TEXT PRIMARY KEY," + KEY_SCORE + " INTEGER" + " )";
        db.execSQL(CREATE_SCORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);

        onCreate(db);
    }

    void addScore(Score score){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, score.getName());
        values.put(KEY_SCORE, score.getScore());

        db.insert(TABLE_SCORES, null, values);
        db.close();
    }

    Score getScore(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SCORES, new String[] {KEY_NAME, KEY_SCORE}, KEY_NAME + " =? ",
                new String[]{name}, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }

        Score score = new Score(cursor.getString(0), Integer.parseInt(cursor.getString(1)));

        return score;
    }

    public List<Score> getAllScores(){
        List<Score> scoreList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_SCORES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                Score score = new Score();
                score.setName(cursor.getString(0));
                score.setScore(Integer.parseInt(cursor.getString(1)));

                scoreList.add(score);
            } while (cursor.moveToNext());

        }
        return scoreList;

    }

    public int updateScore(Score score){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, score.getName());
        values.put(KEY_SCORE, score.getScore());

        return db.update(TABLE_SCORES, values, KEY_NAME + " =? ",
                new String[] {score.getName()});
    }

    public void deleteScore(Score score){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCORES, KEY_NAME + " =? ",
                new String[]{score.getName()});
        db.close();
    }

    public int getScoreCount(){
        String countQuery = "SELECT  * FROM " + TABLE_SCORES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
