package com.example.michael.pong;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Trevor on 17/04/2017.
 */


public class MainMenu extends Activity {

    private String userName = "";
    private boolean canPlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        MySQLiteHelper db = new MySQLiteHelper(this);

        /**
         * CRUD Operations
         * */
        // add Books
        db.addScore(new ScoreBoard("Trevor", 50));
        db.addScore(new ScoreBoard("Michael", 50));
        db.addScore(new ScoreBoard("Dan", 3000));

        // get all books
        List<ScoreBoard> list = db.getAllScores();

        // delete one book
        //db.deleteScore(list.get(0));

        // get all books
        db.getAllScores();

        setContentView(R.layout.main_menu);
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

    public void sendMessage(View view) {


        switch (view.getId()) {
            case R.id.button_play: {
                if(canPlay == true) {
                    Intent intent = new Intent(this.getString(R.string.CUSTOM_ACTION_PLAYBUTTON));
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "You have to enter a name in 'profile' to play a game", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.button_score: {
                Intent intent = new Intent(this.getString(R.string.CUSTOM_ACTION_SCOREBUTTON));
                startActivity(intent);
                break;
            }
            case R.id.button_profile: {
                profile();
                break;
            }
        }
    }

    public void profile()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

        // Set up the input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userName = input.getText().toString();
                if(userName.length() >= 4)
                    canPlay =true;
                else
                    Toast.makeText(getApplicationContext(), "Username must be at least 4 characters in length", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    public String getUserName()
    {
        return userName;
    }
}
