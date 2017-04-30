package com.example.michael.pong;

/**
 * Created by Michael on 18/02/2017.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Enemy extends GameObject{
    private Bitmap spritesheet;
    private int score;
    private int health;
    private int fullHealth;
    private double dya;
    private boolean up;
    private boolean dead = false;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;
    private int countX = 0;
    private int countY = 0;
    private boolean Finished = false;
    //private int value1;
    //private int value2;

    public Enemy(Bitmap res, int w, int h, int numFrames, int  xCoord, int yCoord) {

        health = 100;
        fullHealth = 100;
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
        animation.setReaper();
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
    public void getCurrentPoint()
    {
        //move from current x,y to point 200,50
        /*int Xpoints[] = new int[16];
        Xpoints[0] = 80;
        Xpoints[1] = 80;
        Xpoints[2] = 160;
        Xpoints[3] = 160;
        Xpoints[4] = 260;
        Xpoints[5] = 260;
        Xpoints[6] = 390;
        Xpoints[7] = 390;
        Xpoints[8] = 290;
        Xpoints[9] = 290;
        Xpoints[10] = 500;
        Xpoints[11] = 500;
        Xpoints[12] = 650;
        Xpoints[13] = 650;
        Xpoints[14] = 900;
        Xpoints[15] = 950;
        int Ypoints[] = new int[16];
        Ypoints[0] = 200;
        Ypoints[1] = 60;
        Ypoints[2] = 60;
        Ypoints[3] = 240;
        Ypoints[4] = 240;
        Ypoints[5] = 400;
        Ypoints[6] = 400;
        Ypoints[7] = 110;
        Ypoints[8] = 110;
        Ypoints[9] = 40;
        Ypoints[10] = 40;
        Ypoints[11] = 300;
        Ypoints[12] = 300;
        Ypoints[13] = 180;
        Ypoints[14] = 180;
        Ypoints[15] = 180;*/
        int Xpoints[] = new int[16];
        Xpoints[0] = 143;
        Xpoints[1] = 139;
        Xpoints[2] = 449;
        Xpoints[3] = 453;
        Xpoints[4] = 689;
        Xpoints[5] = 701;
        Xpoints[6] = 1036;
        Xpoints[7] = 1032;
        Xpoints[8] = 771;
        Xpoints[9] = 771;
        Xpoints[10] = 1288;
        Xpoints[11] = 1296;
        Xpoints[12] = 1627;
        Xpoints[13] = 1635;
        Xpoints[14] = 1900;
        Xpoints[15] = 2000;
        int Ypoints[] = new int[16];
        Ypoints[0] = 488;
        Ypoints[1] = 178;
        Ypoints[2] = 174;
        Ypoints[3] = 595;
        Ypoints[4] = 612;
        Ypoints[5] = 868;
        Ypoints[6] = 872;
        Ypoints[7] = 377;
        Ypoints[8] = 364;
        Ypoints[9] = 112;
        Ypoints[10] = 108;
        Ypoints[11] = 678;
        Ypoints[12] = 690;
        Ypoints[13] = 401;
        Ypoints[14] = 401;
        Ypoints[15] = 401;

        int pointsEast [] = {0,2,4,6,10,12,14,15};
        int pointsWest [] = {8};
        int pointsNorth [] = {1,7,9,13};
        int pointsSouth [] = {3,5,11};

        double sx = x;
        double sy = y;
        int L = GamePanel.HEIGHT / 2;
        double deltaX =  Xpoints[countX] - sx;//800 - sx;
        double deltaY = Ypoints[countY] - sy;//L - sy;
        double angle = Math.atan2( deltaY, deltaX );
        int speed = 7;
        if(Finished != true) {
            x += speed * Math.cos(angle);
            y += speed * Math.sin(angle);
        }

        /*if(x > (Xpoints[countX] -5) && x < (Xpoints[countX] + 5) && y > (Ypoints[countY] -5) && y < (Ypoints[countY] + 5)) {
            countX++;
            countY++;
            for(int i = 0; i < 7;i++) {

                if (countX == pointsEast[i]) {
                    System.out.println("oh shit waddup");
                    this.animation.setDirection("east");
                }
                else
                    this.animation.setDirection("south");
                    //System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
            }
            //System.out.println("MATCH!!!    " + countX);
        }*/
        if(x > (Xpoints[countX] -5) && x < (Xpoints[countX] + 5) && y > (Ypoints[countY] -5) && y < (Ypoints[countY] + 5)) {
            countX++;
            countY++;

            for(int i = 0; i < pointsEast.length;i++) {
                if (countX == pointsEast[i]) {
                    System.out.println("going east");
                    this.animation.setDirection("east");
                }
            }
            for(int i = 0; i < pointsWest.length;i++) {
                if (countX == pointsWest[i]) {
                    System.out.println("going west");
                    this.animation.setDirection("west");
                }
            }
            for(int i = 0; i < pointsNorth.length;i++) {
                if (countX == pointsNorth[i]) {
                    System.out.println("going north");
                    this.animation.setDirection("north");
                }
            }
            for(int i = 0; i < pointsSouth.length;i++) {
                if (countX == pointsSouth[i]) {
                    System.out.println("going south");
                    this.animation.setDirection("south");
                }
            }
        Canvas canvas = new Canvas();
        if(countX == 1) {
            //System.out.print("Here");
            canvas.rotate(90, x + (115 / 2), y + (160 / 2));
        }

        if(countX == 16)
            Finished = true;

    }
    }
    public void minusHealth(int damage)
    {
        health -= damage;
    }
    public boolean isDead()
    {
        if(health <= 0) {
            dead = true;
            //System.out.println("Dead!!!!!!!!!!!!!!!!!!!!!!!!");
            return dead;
        }
            else
            return dead;
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
    public int getHealth()
    {
        return health;
    }
    public int getFullHealth()
    {
        return fullHealth;
    }
    public void addHealth(int newHealth) {
        health += newHealth;
        fullHealth = health;
    }
    public void kill(){ health = 0;}
    public void setX(int ReaperX)
    {
        this.x = ReaperX;
    }
    public void setY(int ReaperY)
    {
        this.y = ReaperY;
    }

}