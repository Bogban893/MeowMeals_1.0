package com.example.meowmeals_10;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Sprite {
    private Bitmap bitmap;
    private int x,y,velocityX,velocityY;
    private int padding;
    public Sprite (int x, int y, int velocityX, int velocityY, Bitmap bitmap){
        this.x=x;
        this.y=y;
        this.velocityX=velocityX;
        this.velocityY=velocityY;
        this.bitmap = bitmap;
        this.padding = 30;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setB(Bitmap b) {
        this.bitmap = b;
    }
    public int getVx() {
        return velocityX;
    }
    public void setVx(int velocityX) {
        this.velocityX = velocityX;
    }
    public int getVy() {
        return velocityY;
    }
    public void setVy(int velocityY) {
        this.velocityY = velocityY;
    }
    public int getBx() {
        return bitmap.getWidth();
    }
    public int getBy() {
        return bitmap.getHeight();
    }

    public void eat(int x){
        this.y = -500;
        int xi = (int) (Math.random()*(x-100))-100;
        this.x = xi;
//         if (100<xi | xi>x-100) {while (100<xi | xi>x-100) {
//            xi = (int) (Math.random()*(x))-100;
//        }
//         } else this.x = xi;
    }

    public Rect getBoundingBoxRect () {
        return new Rect((int)x+padding, (int)y+padding, (int)(x + bitmap.getWidth() - 2 * padding), (int)(y + bitmap.getHeight() - 2 * padding));
    }
    public boolean intersect (Sprite s) {
        return getBoundingBoxRect().intersect(s.getBoundingBoxRect());
    }
    public void draw (Canvas canvas) {
        Paint p = new Paint();
        canvas.drawBitmap(bitmap, x, y,p);
    }
    public void update (int ms) {
        x =  (x + velocityX * ms/100);
        y = y + velocityY * ms/100;
    }
}