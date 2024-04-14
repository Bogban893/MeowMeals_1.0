package com.example.meowmeals_10;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
    public int viewWidth; // экран
    public int viewHeight;
    private Bitmap image_fon = BitmapFactory.decodeResource(getResources(), R.drawable.img_fon);

    int timerInterval = 30;
    int gun_y;
    int Hp = 3;
    public int points; // счёт
    public int pointsmax; // счёт макс

    public Bitmap image_hp_1 = BitmapFactory.decodeResource(getResources(), R.drawable.img_hp);
    private Stats image_hp = new Stats(500,500,0,0,image_hp_1);

    private Bitmap image_gun_1 =  BitmapFactory.decodeResource(getResources(),R.drawable.img_gun);
    private Gun image_gun = new Gun(viewWidth/2,1000 - viewHeight/10, 0, 0,image_gun_1);


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { // экран
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    class Timer extends CountDownTimer {
        public Timer() {super(Integer.MAX_VALUE, timerInterval);}
        @Override
        public void onTick(long millisUntilFinished) {update();}
        @Override
        public void onFinish() {}
    }

    public GameView(Context context) {
        super(context);
        Timer t = new Timer();
        t.start();
        if (Hp==0){t.onFinish();}
    }

    @Override
    protected void onDraw(Canvas canvas) { // Рисование
        Paint p= new Paint();
        image_gun.setY(viewHeight-viewHeight/6);
        super.onDraw(canvas);
        canvas.drawBitmap(image_fon,null,new Rect(0,0,viewWidth,viewHeight),null);
        image_hp.draw_Hp(canvas, 3, viewWidth);
        image_gun.draw(canvas);
        p.setTextSize(70.0f); p.setColor(Color.WHITE);
        canvas.drawText(points+"", viewWidth - 100, 100, p); // счёт
        p.setTextSize(50.0f); canvas.drawText(pointsmax+"",viewWidth - 100, 180, p); // рекорд
    }

    protected void update(){
        image_gun.update(timerInterval);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int eventAction = event.getAction();
        image_gun.setX((int) event.getX() - 150);
        image_gun.update(timerInterval);
        Log.d("XXXXXXXXXXXXXXXXX", image_gun.getX()+"");
//        fish_a = true;
//        img_fish = new Sprite(image_gun.getX()+image_gun.getBx()/5, image_gun.getY(), 0, -1000, BitmapFactory.decodeResource(getResources(),R.drawable.img_fish));
        return false;
    }
}
