package com.example.meowmeals_10;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Stats extends Sprite{
    private int x,y,Vx,Vy ;
    private Bitmap bitmap;
//    private Rect rect;
    Paint paint = new Paint();

    public Stats(int x, int y, int Vx, int Vy,Bitmap b){
        super(x,y,Vx,Vy,b);
        this.bitmap = b;
//        rect = new Rect(30,30, 30+this.x, this.y);

    }
    public void draw_Hp(Canvas canvas, int Hp,int viewHeight){
        int e = viewHeight/20;
        for (int i = 0; i != Hp; i++) {
            canvas.drawBitmap(bitmap,  (viewHeight)-e, this.y+30, paint);
//            rect = new Rect(30+e,30, 30+this.x+e, this.y);
            e += viewHeight/20;
        }
    }
}
