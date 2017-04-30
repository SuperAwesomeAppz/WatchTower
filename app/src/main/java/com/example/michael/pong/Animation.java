package com.example.michael.pong;

/**
 * Created by Michael on 18/02/2017.
 */

import android.graphics.Bitmap;

public class Animation {
    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    private boolean reaper = false;
    private boolean playedOnce;
    private boolean playOneFrame = false;
    private String direction = "east";

    public void setFrames(Bitmap[] frames)
    {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }
    public void setDelay(long d){delay = d;}
    public void setFrame(int i){currentFrame= i;}

    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;

        if(elapsed>delay && playOneFrame == false && reaper == false)
        {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length && playOneFrame == false && reaper == false){
            currentFrame = 0;
            playedOnce = true;
        }
        if(elapsed>delay && reaper == true)
        {
            if(direction.equalsIgnoreCase("east"))
            {
                currentFrame++;
                if(currentFrame >= 4)
                    currentFrame = 0;
                startTime = System.nanoTime();

            }
            if(direction.equalsIgnoreCase("south"))
            {
                currentFrame++;
                if(currentFrame <=4)
                    currentFrame = 4;
                if(currentFrame >= 8)
                    currentFrame = 4;
                currentFrame++;
                startTime = System.nanoTime();
                if(currentFrame == 8)
                    currentFrame = 4;
            }

        }
    }
    public Bitmap getImage(){
        return frames[currentFrame];
    }
    public int getFrame(){return currentFrame;}
    public boolean playedOnce(){return playedOnce;}
    public void setToOneFrame() { playOneFrame = true;}
    public void setReaper() { reaper = true;}
    public void setDirection(String dir){direction = dir; }
}
