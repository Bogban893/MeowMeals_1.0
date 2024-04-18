package com.example.meowmeals_10;

import android.graphics.Bitmap;
import android.graphics.Paint;

public class Cats extends Sprite {
    private int x,y,Vx,Vy ;
    private Bitmap bitmap;
    Paint paint = new Paint();

    public Cats(int x, int y, int velocityX, int velocityY, Bitmap bitmap) {
        super(x, y, velocityX, velocityY, bitmap);
        this.y = y; this.x = x; this.bitmap = bitmap;
    }

}
