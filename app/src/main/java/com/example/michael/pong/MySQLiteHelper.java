package com.example.michael.pong;

/**
 * Created by Michael on 30/04/2017.
 */

import java.util.LinkedList;
import java.util.List;

//import com.hmkcode.android.model.ScoreBoard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    List<ScoreBoard> scores = new LinkedList<ScoreBoard>();
    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "ScoreBoardDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create ScoreBoard table
        String CREATE_ScoreBoard_TABLE = "CREATE TABLE ScoreBoard ( " +
                "score INTEGER PRIMARY KEY, " +
                "name TEXT  )";

        // create ScoreBoard table
        db.execSQL(CREATE_ScoreBoard_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older ScoreBoard table if existed
        db.execSQL("DROP TABLE IF EXISTS ScoreBoard");

        // create fresh ScoreBoard table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) ScoreBoard + get all ScoreBoard + delete all ScoreBoard
     */

    // ScoreBoard table name
    private static final String TABLE_ScoreBoard = "ScoreBoard";

    // ScoreBoard Table Columns names
    private static final String KEY_SCORE = "score";
    private static final String KEY_NAME = "name";

    private static final String[] COLUMNS = {KEY_NAME,KEY_SCORE};

    public void addScore(ScoreBoard newScore){
        Log.d("addScore", newScore.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newScore.getName()); // get title
        //System.out.println("this is the damn name " + newScore.getName() );

        // 3. insert
        db.insert(TABLE_ScoreBoard, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public ScoreBoard getScore(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_ScoreBoard, // a. table
                        COLUMNS, // b. column names
                        " Score = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build scoreboard object
        ScoreBoard newScore = new ScoreBoard();
        newScore.setScore(Integer.parseInt(cursor.getString(0)));
        newScore.setName(cursor.getString(1));

        Log.d("getScoreBoard("+id+")", newScore.toString());

        // 5. return book
        return newScore;
    }

    // Get All Books
    public List<ScoreBoard> getAllScores() {


        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_ScoreBoard;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        ScoreBoard newScores = null;
        if (cursor.moveToFirst()) {
            do {
                newScores = new ScoreBoard();
                newScores.setScore(Integer.parseInt(cursor.getString(0)));
                newScores.setName(cursor.getString(1));
                //newScores.getScore();
                //newScores.getName();
                // Add book to books
                scores.add(newScores);
            } while (cursor.moveToNext());
        }

        Log.d("getAllScores()", scores.toString());

        // return books
        return scores;
    }


}
