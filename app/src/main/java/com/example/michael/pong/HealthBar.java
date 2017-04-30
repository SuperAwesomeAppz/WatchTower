package com.example.michael.pong;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Michael on 30/04/2017.
 */

public class HealthBar{
        private Bitmap image;
        private int xCoord;
        private int yCoord;
        private int width;
        private int frame = 0;
        private int height;
        private long startTime;
        private Bitmap spritesheet;
        private Animation animation = new Animation();

        public HealthBar(Bitmap res, int w, int h, int numFrames,int  x, int y)
        {
            image = res;
            xCoord = x;
            yCoord = y;
            width = w;
            height = h;

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

        public void update() {
            //GamePanel ll = new GamePanel(context);
            //GamePanel.updateBastion();
            long elapsed = (System.nanoTime() - startTime) / 1000000;
            if (elapsed > 100) {
                startTime = System.nanoTime();
            }
            animation.update();
        }
        public void draw(Canvas canvas)
        {
            canvas.drawBitmap(animation.getImage(),xCoord,yCoord,null);
        }
        public void setCoords(int newX, int newY)
        {
            xCoord = newX;
            yCoord = newY;
        }
        public int getFrame(){return frame;}
        public void setFrame(int newFrame){frame = newFrame;}
        public void upgrade() {
            animation.setToOneFrame();
            animation.setFrame(frame);
        }
        public int getX() {return xCoord;}
        public int getY() {return yCoord;}

    }

