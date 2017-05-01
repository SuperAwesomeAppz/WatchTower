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

        db.addScore(new Score("Jerry", 780234));
        db.addScore(new Score("Michael", 684567));
        db.addScore(new Score("Billy", 574360));
        db.addScore(new Score("Chester", 484797));
        db.addScore(new Score("Jackson", 483688));
        db.addScore(new Score("Tiberius", 420420));
        db.addScore(new Score("Jack", 413789));
        db.addScore(new Score("Luther", 393809));
        db.addScore(new Score("Ricky", 379009));
        db.addScore(new Score("Micky", 113789));
        db.addScore(new Score("Harley", 3709));

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
