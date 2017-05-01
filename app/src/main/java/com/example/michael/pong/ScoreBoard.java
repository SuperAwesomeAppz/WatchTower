package com.example.michael.pong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class ScoreBoard extends AppCompatActivity{

    TextView textView;

    String text = "NAME     SCORE\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);

        textView = (TextView) findViewById(R.id.textView);

        DatabaseHelper db = new DatabaseHelper(this);

        //inserting scores
        db.addScore(new Score("Michael", 32007));
        db.addScore(new Score("Daniel", 44382));
        db.addScore(new Score("Stephen", 11234));
        db.addScore(new Score("Trevor", 2313));

        //reading and displaying all scores
        List<Score> scores = db.getAllScores();

        for(Score s : scores){
            String log = s.getName() + "     " + s.getScore() + "\n";
            text = text + log;
        }

        textView.setText(text);
    }
}
