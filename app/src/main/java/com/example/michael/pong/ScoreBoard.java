package com.example.michael.pong;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class ScoreBoard extends Activity {
    private String name;
    private int score;
    private String m_Text = "";



    public ScoreBoard() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }

    public ScoreBoard(String name, int score) {
        //super();
        this.name = name;
        this.score = score;
    }

    //getters & setters

    @Override
    public String toString() {
        return "Player name: " + this.name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String title) {
        this.name = name;
    }


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
        }
    }


}


