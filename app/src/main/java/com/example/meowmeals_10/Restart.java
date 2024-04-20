package com.example.meowmeals_10;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Restart extends Sprite{
    private int x,y,Vx,Vy ;
    private Bitmap bitmap;
    Paint paint = new Paint();

    public Restart(int x, int y, int velocityX, int velocityY, Bitmap bitmap) {
        super(x, y, velocityX, velocityY, bitmap);
        this.y = y; this.x = x; this.bitmap = bitmap;
    }
    public void draw_rest(Canvas canvas, int x, int y, int c){
        Rect rect = new Rect(x/5,y/c*2,x/5*4,y/c*3);
        canvas.drawBitmap(bitmap,  null, rect, this.paint);
    }
    public void draw_exit(Canvas canvas, int x, int y, int c){
        Rect rect = new Rect(x/5,y/c*3,x/5*4,y/c*4-y/20);
        canvas.drawBitmap(bitmap,  null, rect, this.paint);
    }
}
