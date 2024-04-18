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

import java.util.ArrayList;

public class GameView extends View {
    public int viewWidth; // экран
    public int viewHeight;
    private Bitmap image_fon = BitmapFactory.decodeResource(getResources(), R.drawable.img_fon);

    int timerInterval = 30;
    int spawn_fish;
    int Hp = 3;
    public int points; // счёт
    public int pointsmax; // счёт макс

    public Bitmap image_hp_1 = BitmapFactory.decodeResource(getResources(), R.drawable.img_hp);
    private Stats image_hp = new Stats(500,500,0,0,image_hp_1);

    private Bitmap image_gun_1 =  BitmapFactory.decodeResource(getResources(),R.drawable.img_gun);
    private Gun image_gun = new Gun(viewWidth/2,1000 - viewHeight/10, 0, 0,image_gun_1);

    private Bitmap image_cat_1 = BitmapFactory.decodeResource(getResources(), R.drawable.img_cat);
    private Cats image_cats = new Cats(0, -500, 0, 100, image_cat_1);

    private ArrayList<Fish> image_fish = new ArrayList<>();



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
//        if (Hp==0){t.onFinish();}
    }

    @Override
    protected void onDraw(Canvas canvas) { // Рисование
        Paint p= new Paint();
        image_gun.setY(viewHeight-viewHeight/6);
        super.onDraw(canvas);
        canvas.drawBitmap(image_fon,null,new Rect(0,0,viewWidth,viewHeight),null);
        image_hp.draw_Hp(canvas, Hp, viewWidth);
        image_gun.draw(canvas);
        image_cats.draw(canvas);
        for (Fish fish:image_fish){
            if (image_cats.intersect(fish)){
                image_cats.eat(viewWidth);
                points += 10;
                image_fish.remove(fish);
            }
            fish.draw(canvas);
            fish.update(timerInterval);
        }
//        image_fish.draw(canvas);
        p.setTextSize(70.0f); p.setColor(Color.WHITE);
        canvas.drawText(points+"", viewWidth - 100, 100, p); // счёт
        p.setTextSize(50.0f); canvas.drawText(pointsmax+"",viewWidth - 100, 180, p); // рекорд
    }

    protected void update(){
        invalidate();
        image_cats.update(timerInterval);image_hp.update(timerInterval);//image_fish.update(timerInterval);
        if (image_cats.intersect(image_gun)){
            image_cats.eat(viewWidth);
            Hp -= 1;
        }
        if (image_cats.getY() > viewHeight) {
            image_cats.eat(viewWidth);
            Hp -= 1;
            points = 0;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int eventAction = event.getAction();
        image_gun.setX((int) event.getX() - 150);
        if (spawn_fish==0) {
            spawn_fish=5;
            image_fish.add(new Fish(image_gun.getX(), 1860, 0, -75, BitmapFactory.decodeResource(getResources(),R.drawable.img_fish)));
        } else spawn_fish--;
        image_gun.update(timerInterval);
        Log.d("XXXXXXXXXXXXXXXXX", image_cats.getY()+"");
        return true;
    }
}