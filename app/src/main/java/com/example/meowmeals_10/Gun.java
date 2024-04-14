package com.example.meowmeals_10;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Gun extends Sprite{
    private int x,y,Vx,Vy ;
    private Bitmap bitmap;
    private Rect rect;
    Paint paint = new Paint();
    public Gun(int x, int y, int velocityX, int velocityY, Bitmap bitmap) {
        super(x, y, velocityX, velocityY, bitmap);
        this.bitmap = bitmap;
        this.x = x;

    }
//    public void draw_gun(Canvas canvas, int x, int y){
//        rect = new Rect(x/2, (y - y/2), x/2+this.x, (y - y/2)+this.y);
//        canvas.drawBitmap(bitmap, 2*x/11+x/5, y-y/6, paint);
//    }
    public void movement(int x) { // Зачем?
         }
    public void update (int ms) {
        x =  (x + Vx * ms/1000);
    }
}
