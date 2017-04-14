package com.example.michael.pong;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Michael on 22/03/2017.
 */

public class Bullet extends GameObject{
    private Bitmap spritesheet;
    private int score;
    private int health;
    private double dya;
    private boolean up;
    private boolean hit = false;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;
    private int countX = 0;
    private int countY = 0;
    private boolean Finished = false;
    //private boolean hit = false;
    //private int value1;
    //private int value2;

    public Bullet(Bitmap res, int w, int h, int numFrames,int  xCoord, int yCoord) {

        health = 250;
        x = xCoord;
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

        //getCurrentPoint();//constantly check what point each reaper is going for



       /* if(up){
            dy = (int)(dya-=1.1);

        }
        else{
            dy = (int)(dya+=1.1);
        }

        if(dy>14)dy = 14;
        if(dy<-14)dy = -14;

       // y += dy*2;
        dy = 0;*/
    }
    public boolean getCurrentPoint(int targetX, int targetY)
    {
        //int BastionX = player.getX();
        //int BastionY = player.getY();
       // bullet = new Bullet(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 72, 63, 4, 500, 200);
        //if (done == true) {
            //System.out.println("Inside");
            //final int savedState = canvas.save();
            //bullet.draw(canvas);
            //canvas.restoreToCount(savedState);
            //done = false;
            //canvas.rotate(90, bullet.getX() + (115 / 2), bullet.getY() + (160 / 2));
          //  System.out.println("done");
        //}
        //bullet.update();

        double sx = x;
        double sy = y;
        int L = GamePanel.HEIGHT / 2;
        double deltaX =  targetX - sx;//800 - sx;
        double deltaY = targetY - sy;//L - sy;
        double angle = Math.atan2( deltaY, deltaX );
        int speed = 20;


        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);

        if(x > (targetX -8) && x < (targetX + 8) && y > (targetY -8) && y < (targetY + 8)) {
            hit = true;
        }

        return hit;
    }
    public boolean isFinished()
    {
        if(hit == true)
            return true;
        else
            return false;
    }

    public int minusHealth()
    {
        health -= 10;
        return health;
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