package com.example.michael.pong;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;



import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
//import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
//import android.widget.ImageView;

//Coyne was here
import android.graphics.Matrix;
import android.widget.ImageView;
import android.view.MotionEvent;
 /* Created by Michael on 23/02/2017.
 */

public class bastion extends GameObject{
    private Bitmap spritesheet;
    private int score;
    private int radius;
    private int cost;
    private int frame;
    private double dya;
    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;
    public static Canvas canvas;
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;

    public bastion(Bitmap res, int w, int h, int numFrames,int  xCoord, int yCoord) {
        frame = 0;
        radius = 300;
        cost = 100;
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
        //GamePanel ll = new GamePanel(context);
        //GamePanel.updateBastion();
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100)
        {
            score++;
            startTime = System.nanoTime();
        }
        animation.update();



        //canvas.save();
        //canvas.rotate(45, x + (111 / 2), y + (158 / 2));
        //canvas.drawBitmap((BitmapFactory.decodeResource(getResources(), R.drawable.bastion)), 10, 5, null);
        //canvas.restore();



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







    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }
    public int getScore(){return score;}
    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){playing = b;}
    public int getCost(){
        if(frame ==0)
            cost = 250;
        if (frame == 1)
            cost = 500;
        return cost;
    }
    public int getRadius(){return radius;}
    public int getX(){return x;}
    public int getY(){return y;}
    public void resetDYA(){dya = 0;}
    public void resetScore(){score = 0;}
    public int getFrame(){return frame;}
    public void setFrame(int newFrame){frame = newFrame;}
    public void upgrade() { animation.setToOneFrame();animation.setFrame(frame);}
    public double getPrice(){
        return 100;
    }
}
