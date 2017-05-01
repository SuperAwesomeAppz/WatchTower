package com.example.michael.pong;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Michael on 22/03/2017.
 */

public class Bullet extends GameObject{
    private Bitmap spritesheet;
    private int score;
    private int health;
    private int targetX;
    private int targetY;
    private double dya;
    private boolean up;
    private boolean hit = false;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;
    private int countX = 0;
    private int countY = 0;
    private boolean Finished = false;
    private Enemy reaper;

    public Bullet(Bitmap res, int w, int h, int numFrames,int  xCoord, int yCoord, int targetX, int targetY, Enemy reaper) {

        health = 250;
        x = xCoord;
        this.targetX =targetX;
        this.targetY = targetY;
        this.reaper = reaper;
        y = yCoord;
        dy = 100;
        score = 0;
        height = h;
        width = w;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;
        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(360);
        startTime = System.nanoTime();

    }

    public void setUp(boolean b){up = b;}

    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100)
        {
            score++;
            startTime = System.nanoTime();
        }
        animation.update();

    }
    public boolean getCurrentPoints()
    {

        if(this.reaper.isDead() == false) {
            double sx = x;
            double sy = y;
            double deltaX = this.reaper.getX() - sx;
            double deltaY = this.reaper.getY() - sy;
            double angle = Math.atan2(deltaY, deltaX);
            int speed = 8;


            x += speed * Math.cos(angle);
            y += speed * Math.sin(angle);
        }


        if(x > (this.reaper.getX() -10) && x < (this.reaper.getX()+ 10) && y > (this.reaper.getY() -10) && y < (this.reaper.getY() + 10)) {
            hit = true;
        }
        else
            hit = false;
        if(this.reaper.isDead() == true)
            hit = true;


        return hit;
    }
    public boolean getCurrentPoint()
    {
        if(x > (this.reaper.getX() -8) && x < (this.reaper.getX()+ 8) && y > (this.reaper.getY() -8) && y < (this.reaper.getY() + 8)) {
            hit = true;
        }
        else
            hit = false;
        return hit;
    }
    public boolean isFinished()
    {
        boolean enemyInRadius = (Math.pow((this.reaper.getX() - x), 2)) + (Math.pow((this.reaper.getY() - y), 2)) < (Math.pow((300), 2));
        if(enemyInRadius == false) {
            hit = true;;
        }
        if(hit == true)
            return true;
        else
            return false;
    }



    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }
    public int getScore(){return score;}
    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){playing = b;}
    public void resetDYA(){dya = 0;}
    public void resetScore(){score = 0;}
    public int getX(){return x;}
    public int getY(){return y;}

    public void setX(int ReaperX)
    {
        this.x = ReaperX;
    }
    public void setY(int ReaperY)
    {
        this.y = ReaperY;
    }
}