package com.example.michael.pong;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Michael on 30/04/2017.
 */

public class button {
    private Bitmap image;
    private int xCoord;
    private int yCoord;
    public button(Bitmap res, int x, int y)
    {
        image = res;
        xCoord = x;
        yCoord = y;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, xCoord, yCoord,null);
    }
    public int getX() {return xCoord;}
    public int getY() {return yCoord;}

}
