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

<<<<<<< HEAD
        textView = (TextView) findViewById(R.id.textView);
=======
    public ScoreBoard(String name, int score) {
        //super();
        this.name = name;
        this.score = score;
    }
>>>>>>> origin/merging-shit

        DatabaseHelper db = new DatabaseHelper(this);

        //inserting scores
        db.addScore(new Score("Michael", 32007));
        db.addScore(new Score("Daniel", 44382));
        db.addScore(new Score("Stephen", 11234));
        db.addScore(new Score("Trevor", 2313));

        //reading and displaying all scores
        List<Score> scores = db.getAllScores();

<<<<<<< HEAD
        for(Score s : scores){
            String log = s.getName() + "     " + s.getScore() + "\n";
            text = text + log;
=======
    public void sendMessage(View view) {


        switch (view.getId()) {
            case R.id.button_input: {

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
                builderSingle.setIcon(R.drawable.buy);
                builderSingle.setTitle("Select One Name:-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice);

                /*MySQLiteHelper board = new MySQLiteHelper(this);
                List<ScoreBoard> scores = new LinkedList<ScoreBoard>();
                board.addScore(new ScoreBoard("Trevor", 50));
                //board.getScore(1);
                scores = board.getAllScores();

                //arrayAdapter.add(board.getScore(1).toString());
                System.out.println(scores.size());*/
                MySQLiteHelper db = new MySQLiteHelper(this);

                db.addScore(new ScoreBoard("trevor", 5000));
                //db.addScore(new Book("Android Programming: The Big Nerd Ranch Guide", "Bill Phillips and Brian Hardy"));
                //db.addScore(new Book("Learn Android App Development", "Wallace Jackson"));

                // get all books
                List<ScoreBoard> list = db.getAllScores();

                for(int i =0; i < list.size(); i++)
                {
                    System.out.println(list.get(i).getScore());
                }
                System.out.println(list.get(0).getName());
                for(int i =0; i < list.size(); i++)
                {
                    arrayAdapter.add(list.get(i).toString());
                }
                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                    }
                });
                builderSingle.show();

                break;
            }
>>>>>>> origin/merging-shit
        }

        textView.setText(text);
    }
}
