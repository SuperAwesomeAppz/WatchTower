package com.example.michael.pong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class ScoreBoard extends AppCompatActivity{

    DatabaseHelper db = new DatabaseHelper(this);
    TextView textView;

    String text = "NAME     SCORE\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);

        textView = (TextView) findViewById(R.id.textView);


        List<Score> scores = db.getAllScores();

        for(Score s : scores){
            String log = s.getName() + "     " + s.getScore() + "\n";
            text = text + log;
        }

        textView.setText(text);
    }

    public void addToDB(String name, int score){
        db.addScore(new Score(name, score));

        List<Score> scores = db.getAllScores();

        for(Score s : scores){
            String log = s.getName() + "     " + s.getScore() + "\n";
            text = text + log;
        }

        textView.setText(text);
    }
}
