package com.example.meowmeals_10;

import android.graphics.Bitmap;
import android.graphics.Paint;

public class CatsSprite extends Sprite {
    private int x,y,Vx,Vy ;
    private Bitmap bitmap;
    Paint paint = new Paint();

    public CatsSprite(int x, int y, int velocityX, int velocityY, Bitmap bitmap) {
        super(x, y, velocityX, velocityY, bitmap);
    }
}
