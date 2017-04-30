package com.example.michael.pong;

/**
 * Created by Michael on 18/02/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import java.util.*;
//import android.graphics.Matrix;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.lang.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
//import android.widget.ImageView;


import static com.example.michael.pong.R.drawable.brick;
//import com.example.michael.pong.Player.Player;


public class GamePanel extends SurfaceView  implements SurfaceHolder.Callback
{
    boolean TargetStillAlive = true;
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    private int bastionPlace = 0;
    //public static final int WIDTH = 1280;
    //public static final int HEIGHT = 720;
    public static final int MOVESPEED = -5;
    private MainThread thread;
    private Background bg;
    private Background darken;
    private HealthBar enemyHealth;
    private button buyTurret;
    private button pause;
    private button upgradeTower;
    private button resumeGame;
    private button exitToMain;
    private boolean currentRoundFinished = false;
    private Background c1;
    private bastion player;
    private Player player1;
    private Bullet bullet;
    private boolean selectingTower = false;
    private boolean upgradingTower = false;
    private boolean pauseGame = false;
    private int coins = 20000;
    private int score = 0;
    private int health = 5000;
    private int upgradeCost;
    private int countDead = 0;
    private int round = 0;
    private int frame= 0;
    private int roundCount = 0;
    private int targetX = 1;
    private int targetY= 1;
    private int b = 500;
    private int c = 200;
    private boolean hit = false;
    private int upgradeXValue;
    private int upgradeYValue;
    private boolean done = true;
    private boolean found = false;
    private ArrayList<Player> ArrayOfReapers = new ArrayList<Player>();
    private ArrayList<bastion> ArrayOfBastions = new ArrayList<bastion>();
    private ArrayList<Bullet> ArrayOfBullets = new ArrayList<Bullet>();

    private ArrayList<HealthBar> ArrayOfHealthBars = new ArrayList<HealthBar>();
    public static Canvas canvas;
    private int [] roundNumber = {12,20,28,36,44,56,68,80,88,100};




    public GamePanel(Context context)
    {
        super(context);


        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

   @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

   @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry)
        {
            try{thread.setRunning(false);
                thread.join();

            }catch(InterruptedException e){e.printStackTrace();}
            retry = false;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {


        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));
        darken = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.darken));
        c1 = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.circles));
        buyTurret = new button(BitmapFactory.decodeResource(getResources(), R.drawable.buy), 1650, 20);
        pause = new button(BitmapFactory.decodeResource(getResources(), R.drawable.pause), 1800, 20);
        resumeGame = new button(BitmapFactory.decodeResource(getResources(), R.drawable.resumeoption), 775, 475);
        exitToMain = new button(BitmapFactory.decodeResource(getResources(), R.drawable.exitoption), 775, 575);
        //buy = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.buy));
        //int y = GamePanel.HEIGHT / 2;

        //players arguments are width of frame, height, number of frames, x and y coords
        //player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 145, 126, 4, 150, y);
        player = new bastion(BitmapFactory.decodeResource(getResources(), R.drawable.bastion), 111, 158, 3, 500, 200);
        //player.setAlpha(50);

        System.out.println("Amount of reapers: " + ArrayOfReapers.size());
        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            int touchX = (int)event.getX();
            int touchY = (int)event.getY();



            int [] spotsX = new int [41];
            int [] spotsY = new int [41];
            spotsX[0] = 60; //all possible x and y values for spots where towers can be placed. Each spot has a radius of 70.
            spotsX[1] = 57;
            spotsX[2] = 127;
            spotsX[3] = 259;
            spotsX[4] = 391;
            spotsX[5] = 524;
            spotsX[6] = 656;
            spotsX[7] = 660;
            spotsX[8] = 664;
            spotsX[9] = 647;
            spotsX[10] = 796;
            spotsX[11] = 949;
            spotsX[12] = 912;
            spotsX[13] = 916;
            spotsX[14] = 937;
            spotsX[15] = 1069;
            spotsX[16] = 1205;
            spotsX[17] = 1205;
            spotsX[18] = 1205;
            spotsX[19] = 1205;
            spotsX[20] = 1205;
            spotsX[21] = 1205;
            spotsX[22] = 1338;
            spotsX[23] = 1470;
            spotsX[24] = 1602;
            spotsX[25] = 1466;
            spotsX[26] = 1466;
            spotsX[27] = 1466;
            spotsX[28] = 1540;
            spotsX[29] = 1466;
            spotsX[30] = 1569;
            spotsX[31] = 1701;
            spotsX[32] = 346;
            spotsX[33] = 346;
            spotsX[34] = 346;
            spotsX[35] = 346;
            spotsX[36] = 76;
            spotsX[37] = 226;
            spotsX[38] = 453;
            spotsX[39] = 594;
            spotsX[40] = 594;

            spotsY[0] = 405;
            spotsY[1] = 245;
            spotsY[2] = 88;
            spotsY[3] = 96;
            spotsY[4] = 96;
            spotsY[5] = 100;
            spotsY[6] = 137;
            spotsY[7] = 265;
            spotsY[8] = 393;
            spotsY[9] = 538;
            spotsY[10] = 538;
            spotsY[11] = 529;
            spotsY[12] = 657;
            spotsY[13] = 789;
            spotsY[14] = 282;
            spotsY[15] = 278;
            spotsY[16] = 278;
            spotsY[17] = 410;
            spotsY[18] = 546;
            spotsY[19] = 686;
            spotsY[20] = 818;
            spotsY[21] = 950;
            spotsY[22] = 855;
            spotsY[23] = 855;
            spotsY[24] = 855;
            spotsY[25] = 117;
            spotsY[26] = 257;
            spotsY[27] = 401;
            spotsY[28] = 500;
            spotsY[29] = 608;
            spotsY[30] = 323;
            spotsY[31] = 315;
            spotsY[32] = 348;
            spotsY[33] = 480;
            spotsY[34] = 608;
            spotsY[35] = 744;
            spotsY[36] = 682;
            spotsY[37] = 682;
            spotsY[38] = 826;
            spotsY[39] = 830;
            spotsY[40] = 967;

            int radius = 70;

            boolean checkIfPaused = (Math.pow((touchX - pause.getX()), 2)) + (Math.pow((touchY - pause.getY()), 2)) < (Math.pow((70), 2));
            if (checkIfPaused == true) {
                pauseGame = true;
            }
            if (pauseGame = true)
            {
                if ((touchX >= resumeGame.getX()) && ((resumeGame.getX() + 450) > touchX ) && (touchY >= resumeGame.getY()) && (touchY < (resumeGame.getY() + 85)))
                {
                    pauseGame = false;
                }
                if ((touchX >= exitToMain.getX()) && ((exitToMain.getX() + 450) > touchX ) && (touchY >= exitToMain.getY()) && (touchY < (exitToMain.getY() + 85)))
                {
                    pauseGame = false;
                    //exit();
                    Game buffer = new Game();
                    //MainMenu ok = new MainMenu();
                    buffer.returnToMenu();
                    //thread.interrupt();
                    //surfaceDestroyed(getHolder());
                    //surfaceCreated(getHolder());
                }
            }



            boolean checkIfPressedBuy = (Math.pow((touchX - buyTurret.getX()), 2)) + (Math.pow((touchY - buyTurret.getY()), 2)) < (Math.pow((70), 2));
            if (checkIfPressedBuy == true && coins >= 100) {
                selectingTower = true;
                if(roundCount != 0)
                    roundCount ++;
            }

            if(upgradingTower == true)
            {
                if ((touchX >= upgradeXValue) && ((upgradeXValue + 382) > touchX ) && (touchY >= upgradeYValue) && (touchY < (upgradeYValue + 107))) //if the user pressed the upgrade button
                {
                    System.out.println("the mouse x ============= " + touchX);
                    System.out.println("the mouse y ============= " + touchY);
                    System.out.println("the upgrade image x ============= " + upgradeXValue);
                    System.out.println("the upgrade image y ============= " + upgradeYValue);
                    System.out.println("the upgrade image x ============= " + (upgradeXValue + 382));
                    System.out.println("the upgrade image y ============= " + (upgradeYValue + 107));

                    //System.out.println("the upgrade tower x ============= " + upgradeTower.getX());

                    if(coins >= ArrayOfBastions.get(bastionPlace).getCost() && ArrayOfBastions.get(bastionPlace).getFrame() != 2 ) {
                        coins -= ArrayOfBastions.get(bastionPlace).getCost();
                        int currentFrame = ArrayOfBastions.get(bastionPlace).getFrame();
                        ArrayOfBastions.get(bastionPlace).setFrame(currentFrame + 1);
                        ArrayOfBastions.get(bastionPlace).upgrade();
                    }
                    upgradingTower = false;
                }
            }
            for(int i = 0; i < ArrayOfBastions.size(); i++) {


                boolean checkIfPressedExistingTower = (Math.pow((touchX - ArrayOfBastions.get(i).getX()), 2)) + (Math.pow((touchY -ArrayOfBastions.get(i).getY()), 2)) < (Math.pow((70), 2));
                if (checkIfPressedExistingTower == true && ArrayOfBastions.get(i).getFrame() != 2) {
                    bastionPlace = i;
                    upgradeXValue = ArrayOfBastions.get(i).getX() -120 ;
                    upgradeYValue = ArrayOfBastions.get(i).getY() + 110 ;
                    upgradeTower = new button(BitmapFactory.decodeResource(getResources(), R.drawable.upgradetower), ArrayOfBastions.get(i).getX()-120,ArrayOfBastions.get(i).getY() + 110);
                    upgradeCost = ArrayOfBastions.get(i).getCost();
                    upgradingTower = true;

                }

            }



                for (int i = 0; i < spotsX.length && selectingTower == true; i++) {

                    boolean contains = (Math.pow((touchX - spotsX[i]), 2)) + (Math.pow((touchY - spotsY[i]), 2)) < (Math.pow((radius), 2));
                    if (contains == true && coins >= player.getPrice()) {
                        //player = new bastion(BitmapFactory.decodeResource(getResources(), R.drawable.bastion), 111, 158, 3, spotsX[i] - 50, spotsY[i] - 90);
                        player = new bastion(BitmapFactory.decodeResource(getResources(), R.drawable.tower), 122, 122, 3, spotsX[i] -53 , spotsY[i] - 62);
                        ArrayOfBastions.add(player);
                        coins -= player.getPrice();
                        selectingTower = false;
                        checkIfPressedBuy = false;
                    }
                }



            if(!player.getPlaying())
            {
                for(int i =0; i < ArrayOfBastions.size(); i ++)
                {
                    ArrayOfBastions.get(i).setPlaying(true);
                    //player1.setUp(true);
                }
                //player.setPlaying(true);
            }
            else
            {
                //player.setUp(true);
                for(int i =0; i < ArrayOfReapers.size(); i ++)
                {
                    ArrayOfReapers.get(i).setUp(true);

                    //player1.setUp(true);
                }
            }
            return true;
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            player.setUp(false);
            for(int i =0; i < ArrayOfReapers.size(); i ++)
            {
                ArrayOfReapers.get(i).setUp(false);
                //player1.setUp(false);
            }
            return true;
        }

        return super.onTouchEvent(event);
    }

    public void update()
    {
        for(int i = 0; i < ArrayOfBastions.size(); i++)
        {
            ArrayOfBastions.get(i).upgrade();
        }
        for(int i = 0; i < ArrayOfHealthBars.size();i++)
        {
            ArrayOfHealthBars.get(i).upgrade();
        }
        if(countDead == roundNumber[roundCount]) {
            currentRoundFinished = false;
            countDead = 1;
            roundCount++;
            round++;
            for(int i = 0; i < ArrayOfReapers.size(); i++)
            {
                ArrayOfReapers.get(i).addHealth(50);
            }
        }
        int counter = 0;
        int y = 465;
        for (int i = 0; i < roundNumber[roundCount] && currentRoundFinished == false; i++) {
            if (i % 4 == 0)
                counter += 150;
            int x= 0 - counter;
            counter += 120;
            //player1 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 145, 126, 4, x, y);
            //player1 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 72, 63, 4, x, y);
            player1 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.enemy), 141, 152, 16, x, y);

            ArrayOfReapers.add(player1);

            enemyHealth = new HealthBar(BitmapFactory.decodeResource(getResources(), R.drawable.health), 85, 10, 3, x, y + 20);
            ArrayOfHealthBars.add(enemyHealth);

        }
        currentRoundFinished = true;

        if(player.getPlaying()) {
            bg.update();
            c1.update();
            for(int i =0; i < ArrayOfBastions.size(); i ++)
            {
                ArrayOfBastions.get(i).update();
            }
            //player.update();
            //bullet.update();
            //player1.update();
            for(int i =0; i < ArrayOfReapers.size(); i ++)
            {
                if(ArrayOfReapers.get(i).isDead() != true)
                {
                    ArrayOfReapers.get(i).getCurrentPoint();
                    ArrayOfHealthBars.get(i).setCoords(ArrayOfReapers.get(i).getX() + 10,ArrayOfReapers.get(i).getY() + 20);
                    ArrayOfReapers.get(i).update();

                    double currentHealth = ArrayOfReapers.get(i).getHealth();
                    double fullHealth = ArrayOfReapers.get(i).getFullHealth();
                    double percentOfFullHealth = (currentHealth/fullHealth) * 100;
                    if(percentOfFullHealth <= 66 && percentOfFullHealth >= 33)
                        ArrayOfHealthBars.get(i).setFrame(1);
                    else if(percentOfFullHealth <= 33) {
                        System.out.println(percentOfFullHealth +" is definitly less than or equal to " + 33);
                        ArrayOfHealthBars.get(i).setFrame(2);
                    }

                    //ArrayOfHealthBars.get(i).upgrade();
                    //ArrayOfHealthBars.get(i).update();
                }
                if(ArrayOfReapers.get(i).getX()> 1900 && ArrayOfReapers.get(i).isDead() == false ) //if the enemy gets to the end, minus health.
                {
                    health -= 500;
                    ArrayOfReapers.get(i).kill();
                }
            }
        }
        boolean inside = false;
        inside = contains();
        for(int i = 0; i < ArrayOfBullets.size(); i++) {
            boolean check = ArrayOfBullets.get(i).isFinished();
        }
    }
    public boolean contains() {
        boolean contains = false;
        for(int j = 0; j < ArrayOfBastions.size(); j++) {
            for (int i = 0; i < ArrayOfReapers.size(); i++) {
                contains = (Math.pow((ArrayOfReapers.get(i).getX() - ArrayOfBastions.get(j).getX()), 2)) + (Math.pow((ArrayOfReapers.get(i).getY() - ArrayOfBastions.get(j).getY()), 2)) < (Math.pow((ArrayOfBastions.get(j).getRadius()), 2));
                if (contains == true) {
                    if (ArrayOfReapers.get(i).isDead() != true) {
                        targetX = ArrayOfReapers.get(i).getX();
                        targetY = ArrayOfReapers.get(i).getY();
                    }
                    if (done == true) {
                        //System.out.println("Inside");
                        //bullet = new Bullet(BitmapFactory.decodeResource(getResources(), R.drawable.bullets), 72, 63, 4, player.getX(), player.getY());
                        bullet = new Bullet(BitmapFactory.decodeResource(getResources(), R.drawable.bullets), 72, 63, 4,ArrayOfBastions.get(j).getX(),  ArrayOfBastions.get(j).getY(),targetX,targetY, ArrayOfReapers.get(i));
                        //hit = bullet.getCurrentPoint(targetX, targetY);
                        ArrayOfBullets.add(bullet);
                        //final int savedState = canvas.save();
                        //bullet.draw(canvas);
                        draw(canvas);
                        //canvas.restoreToCount(savedState);
                        done = false;
                        //canvas.rotate(90, bullet.getX() + (115 / 2), bullet.getY() + (160 / 2));
                        //System.out.println("done");
                    }
                        if (ArrayOfReapers.get(i).isDead() == false) {
                            for(int k =0; k < ArrayOfBullets.size(); k++) {
                                hit = ArrayOfBullets.get(k).getCurrentPoints();
                                if(hit == true)
                                    ArrayOfBullets.remove(ArrayOfBullets.get(k));
                            }
                            if (hit == true) {
                                int damage = 10;
                                int currentFrame = ArrayOfBastions.get(j).getFrame();
                                if(currentFrame == 0)
                                    damage = 10;
                                if(currentFrame == 1)
                                    damage = 20;
                                if(currentFrame == 3)
                                    damage = 30;

                                ArrayOfReapers.get(i).minusHealth(damage);
                                if(ArrayOfReapers.get(i).getHealth() <= 0)
                                {
                                    countDead ++;
                                    coins += 20;
                                }
                                score += 20;

                                done = true;
                                //if (player1.isDead() == true)
                                //ArrayOfReapers.remove(player1);
                            }
                        //
                        }
                }
            }
        }
        return contains;
    }
    //@Override
    public void draw(Canvas canvas)
    {
        final float scaleFactorX = 1;//getWidth()/(WIDTH*1.f);
        final float scaleFactorY = 1;//getHeight()/(HEIGHT*1.f);
        if(canvas!=null) {



            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            buyTurret.draw(canvas);
            pause.draw(canvas);
            if(upgradingTower == true) {
                upgradeTower.draw(canvas);
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                paint.setTextSize(50);
                canvas.drawText("UPGRADE: " + upgradeCost, upgradeXValue + 20, upgradeYValue + 70, paint);

            }
            if(selectingTower == true)
                c1.draw(canvas);



            for(int i =0; i < ArrayOfReapers.size(); i ++)
            {
                //player1.draw(canvas);
                if(ArrayOfReapers.get(i).isDead() == false) {
                    ArrayOfReapers.get(i).draw(canvas);
                    ArrayOfHealthBars.get(i).draw(canvas);
                }
            }
            //canvas.rotate(90, player.getX() + (115 / 2), player.getY() + (160 / 2));
           // player.draw(canvas);
            for(int i =0; i < ArrayOfBastions.size(); i ++)
            {
                //player1.draw(canvas);
                ArrayOfBastions.get(i).draw(canvas);
            }
            //canvas.drawBitmap((BitmapFactory.decodeResource(getResources(), R.drawable.bastion)), 100, 50, null);
           if(done == false) {

                for(int i =0; i < ArrayOfBullets.size(); i ++)
                {
                    //player1.draw(canvas);
                    if(ArrayOfBullets.get(i).isFinished() == false)
                        ArrayOfBullets.get(i).draw(canvas);
                }


                //bullet.draw(canvas);
                //canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            }

            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setTextSize(100);
            canvas.drawText("Health: " + String.valueOf(health), 1200, 1050, paint);
            canvas.drawText("Round: " + String.valueOf(round + 1), 40, 1050, paint);
            paint.setColor(Color.YELLOW);
            paint.setTextSize(80);
            canvas.drawText("Gold: " + String.valueOf(coins), 700, 80, paint);
            canvas.restoreToCount(savedState);
            if(health <= 0) {
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.MULTIPLY);
                paint.setTextSize(200);
                paint.setColor(Color.RED);
                canvas.drawText("GAME OVER" , 300, 400, paint);
                canvas.drawText("Final Score: " + score , 300, 600, paint);

             }

            if(pauseGame == true)
            {
                darken.draw(canvas);
                resumeGame.draw(canvas);
                exitToMain.draw(canvas);
            }
        }

    }
    private void exit()
    {
        //Activity.finish();
    }
}
