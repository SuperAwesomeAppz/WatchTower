package com.example.michael.pong;

/**
 * Created by Michael on 18/02/2017.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap image;
    private int x, y, dx;

    public Background(Bitmap res)
    {
        image = res;
    }
    public void update()
    {
        x+=dx;
        if(x<-GamePanel.WIDTH){
            x=0;
        }
    }
    public void draw(Canvas canvas)
    {
        //System.out.print("hhhhhhhhhhhhhhhhh");
        Bitmap scaled = Bitmap.createScaledBitmap(image, 900, 500, true);



        canvas.drawBitmap(scaled, x, y,null);
        if(x<0)
        {
            //Bitmap resized = Bitmap.createScaledBitmap(image,(int)(image.getWidth()), (int)(image.getHeight()), true);
            canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
        }
    }
    public void setVector(int dx)
    {
        this.dx = dx;
    }
}
