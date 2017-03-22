package com.example.michael.pong;

/**
 * Created by Michael on 18/02/2017.
 */

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
//import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
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
    public static final int MOVESPEED = -5;
    private MainThread thread;
    private Background bg;
    private bastion player;
    private Player player1;
    private Bullet bullet;
    private int targetX = 0;
    private int targetY= 0;
    private int b = 500;
    private int c = 200;
    private boolean done = true;
    private boolean found = false;
    private ArrayList<Player> ArrayOfReapers = new ArrayList<Player>();
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
        //int y = GamePanel.HEIGHT / 2;
        int y = 200;
        //players arguments are width of frame, height, number of frames, x and y coords
        //player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 145, 126, 4, 150, y);
        player = new bastion(BitmapFactory.decodeResource(getResources(), R.drawable.bastion), 111, 158, 3, 500, 200);
        //player.setAlpha(50);
        int counter = 0;
        for(int i = 0; i < 12; i++) {
            if(i % 4 == 0)
                counter += 100;
            int x = -1300 + counter;
            counter+= 80;
            //player1 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 145, 126, 4, x, y);
            player1 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 72, 63, 4, x, y);
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

            if(!player.getPlaying())
            {
                player.setPlaying(true);
            }
            else
            {
                player.setUp(true);
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
            player.update();
            //bullet.update();
            //player1.update();
            for(int i =0; i < ArrayOfReapers.size(); i ++)
            {
                ArrayOfReapers.get(i).update();
            }
        }
        boolean inside = false;
        inside = contains();
    }
    public boolean contains() {
        boolean contains = false;
        for (int i = 0; i < ArrayOfReapers.size() ;i ++) {
            contains = (Math.pow(( ArrayOfReapers.get(i).getX() - player.getX()), 2)) + (Math.pow(( ArrayOfReapers.get(i).getY() - player.getY()), 2)) < (Math.pow((player.getRadius()), 2));
            if (contains == true) {
                targetX =  ArrayOfReapers.get(i).getX();
                targetY =  ArrayOfReapers.get(i).getY();
                if (done == true) {
                    //System.out.println("Inside");
                    bullet = new Bullet(BitmapFactory.decodeResource(getResources(), R.drawable.bullets), 72, 63, 4, player.getX(), player.getY());
                    //final int savedState = canvas.save();
                    //bullet.draw(canvas);
                    draw(canvas);
                    //canvas.restoreToCount(savedState);
                    done = false;
                    //canvas.rotate(90, bullet.getX() + (115 / 2), bullet.getY() + (160 / 2));
                    //System.out.println("done");
                }
                boolean hit = bullet.getCurrentPoint(targetX, targetY);
                if(hit == true)
                {
                    player1.minusHealth();
                    done = true;
                }
            }
        }
        return contains;
    }
    @Override
    public void draw(Canvas canvas)
    {
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);



        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            for(int i =0; i < ArrayOfReapers.size(); i ++)
            {
                //player1.draw(canvas);
                ArrayOfReapers.get(i).draw(canvas);
            }
            //canvas.rotate(90, player.getX() + (115 / 2), player.getY() + (160 / 2));
            player.draw(canvas);
            //canvas.drawBitmap((BitmapFactory.decodeResource(getResources(), R.drawable.bastion)), 100, 50, null);
            if(done == false) {
                bullet.draw(canvas);
            }

            canvas.restoreToCount(savedState);
        }

    }


}
