package com.example.michael.pong;

/**
 * Created by Michael on 18/02/2017.
 */

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
//import android.graphics.Matrix;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff; 
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


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    //public static final int WIDTH = 1280;
    //public static final int HEIGHT = 720;
    public static final int MOVESPEED = -5;
    private MainThread thread;
    private Background bg;
    private Background c1;
    private bastion player;
    private Player player1;
    private Bullet bullet;
    private int coins = 200;
    private int targetX = 0;
    private int targetY= 0;
    private int b = 500;
    private int c = 200;
    private boolean hit = false;
    private boolean done = true;
    private boolean found = false;
    private ArrayList<Player> ArrayOfReapers = new ArrayList<Player>();
    private ArrayList<bastion> ArrayOfBastions = new ArrayList<bastion>();
    private ArrayList<Bullet> ArrayOfBullets = new ArrayList<Bullet>();
    public static Canvas canvas;




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
    public void surfaceCreated(SurfaceHolder holder){


        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));
        c1 = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.circles));
        //int y = GamePanel.HEIGHT / 2;
        int y = 200;
        //players arguments are width of frame, height, number of frames, x and y coords
        //player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 145, 126, 4, 150, y);
        player = new bastion(BitmapFactory.decodeResource(getResources(), R.drawable.bastion), 111, 158, 3, 500, 200);
        //player.setAlpha(50);
        int counter = 0;
        for (int i = 0; i < 12; i++) {
                if (i % 4 == 0)
                    counter += 150;
                int x= 0 - counter;
                counter += 80;
                //player1 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 145, 126, 4, x, y);
                //player1 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 72, 63, 4, x, y);
                player1 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 147, 128, 4, x, y);
                ArrayOfReapers.add(player1);
            }
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
            spotsX[0] = 60;
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

            for(int i = 0; i < spotsX.length ; i++) {

                boolean contains = (Math.pow((touchX - spotsX[i]), 2)) + (Math.pow((touchY - spotsY[i]), 2)) < (Math.pow((radius), 2));
                if(contains == true && coins > player.getPrice()) {
                    player = new bastion(BitmapFactory.decodeResource(getResources(), R.drawable.bastion), 111, 158, 3, spotsX[i] - 50, spotsY[i] - 90);
                    ArrayOfBastions.add(player);
                    coins -= player.getPrice();
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
                    ArrayOfReapers.get(i).update();
                }
            }
        }
        boolean inside = false;
        inside = contains();
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
                        bullet = new Bullet(BitmapFactory.decodeResource(getResources(), R.drawable.bullets), 72, 63, 4, player.getX(), player.getY());
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
                        hit = bullet.getCurrentPoint(targetX, targetY);
                        if (hit == true) {
                            ArrayOfReapers.get(i).minusHealth();
                            coins += 20;
                            System.out.println("You hit a reaper 20 coins have been added.... Current balance: " + coins);

                            done = true;
                            //if (player1.isDead() == true)
                            //ArrayOfReapers.remove(player1);
                        }
                    }


                }
            }
        }
        return contains;
    }
    @Override
    public void draw(Canvas canvas)
    {
        final float scaleFactorX = 1;//getWidth()/(WIDTH*1.f);
        final float scaleFactorY = 1;//getHeight()/(HEIGHT*1.f);
        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            c1.draw(canvas);
            for(int i =0; i < ArrayOfReapers.size(); i ++)
            {
                //player1.draw(canvas);
                if(ArrayOfReapers.get(i).isDead() == false)
                    ArrayOfReapers.get(i).draw(canvas);
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
            canvas.restoreToCount(savedState);
        }
    }
}
