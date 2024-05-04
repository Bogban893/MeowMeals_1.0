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
    private final DataHelper DBconnector;
    private int bestScore;
    public int points; // счёт

    public int viewWidth; // экран
    public int viewHeight;
    private Bitmap image_fon = BitmapFactory.decodeResource(getResources(), R.drawable.img_fon);

    int timerInterval = 30;
    int timerInterval_f = 30;
    int spawn_fish;
    int Hp = 3;


    public Bitmap image_hp_1 = BitmapFactory.decodeResource(getResources(), R.drawable.img_hp);
    private Stats image_hp = new Stats(500,500,0,0,image_hp_1);

    private Bitmap image_gun_1 =  BitmapFactory.decodeResource(getResources(),R.drawable.img_gun);
    private Gun image_gun = new Gun(viewWidth/2,1000 - viewHeight/10, 0, 0,image_gun_1);

    private Bitmap image_cat_1 = BitmapFactory.decodeResource(getResources(), R.drawable.img_cat);
    private Cats image_cats1 = new Cats(0, -500, 0, 150, image_cat_1);
    private Cats image_cats2 = new Cats(200, -700, 0, 150, image_cat_1);
    private Cats image_cats3 = new Cats(500, -900, 0, 150, image_cat_1);


    private Restart rest = new Restart(viewWidth/2,viewHeight/2, 0, 0, BitmapFactory.decodeResource(getResources(),R.drawable.img_rest));
    private Restart exit = new Restart(viewWidth/2,viewHeight/2, 0, 0, BitmapFactory.decodeResource(getResources(),R.drawable.img_exit));


    private ArrayList<Cats> image_cats_m = new ArrayList<>();

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
        DBconnector = new DataHelper(context);
        bestScore = DBconnector.select();
    }

    @Override
    protected void onDraw(Canvas canvas) { // Рисование
        Paint p= new Paint();
        image_gun.setY(viewHeight-viewHeight/8);
        super.onDraw(canvas);
        canvas.drawBitmap(image_fon,null,new Rect(0,0,viewWidth,viewHeight),null);
        image_hp.draw_Hp(canvas, Hp, viewWidth);
        image_gun.draw(canvas);
        image_cats1.draw(canvas);
        for (Fish fish:image_fish){
            if (image_cats1.intersect(fish) | fish.getY() < 0){image_cats1.eat(viewWidth);points += 1;image_fish.remove(fish);break;}
            fish.draw(canvas);fish.update(timerInterval);}
        p.setTextSize(70.0f); p.setColor(Color.WHITE);
        canvas.drawText(points+"", 50, 100, p); // счёт
        p.setTextSize(50.0f); canvas.drawText(bestScore+"",50, 180, p); // рекорд
        if (Hp == 0) {
            timerInterval=0;
            rest.draw_rest(canvas, viewWidth, viewHeight, 6);
            exit.draw_exit(canvas, viewWidth, viewHeight, 6);
        }
    }

    protected void update(){
        invalidate();
        image_cats1.update(timerInterval);image_hp.update(timerInterval);//image_fish.update(timerInterval);
        if (image_cats1.intersect(image_gun)){
            image_cats1.eat(viewWidth);
            Hp -= 1;
        }
        if (image_cats1.getY() > viewHeight) {
            image_cats1.eat(viewWidth);
            Hp -= 1;
            points = 0;
        }
        if (bestScore<(int)points){
            //сохранение нового рекорда
            DBconnector.updateRecord((int) points);
            bestScore=(int) points;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int eventAction = event.getAction();
        if (Hp > 0) {image_gun.setX((int) event.getX() - 150);
            if (spawn_fish == 0) {spawn_fish = 32;image_fish.add(new Fish(image_gun.getX()+100, image_gun.getY(), 0, -120, BitmapFactory.decodeResource(getResources(), R.drawable.img_fish)));
            } else spawn_fish--;}
        if (event.getX()>viewWidth/5 && event.getX()<viewWidth/5*4 && event.getY()>viewHeight/6*2 && event.getY()<viewHeight/6*3-(viewHeight/40)) {Hp = 3; points = 0; timerInterval = 30;}
        if (event.getX()>viewWidth/5 && event.getX()<viewWidth/5*4 && event.getY()>viewHeight/6*3 && event.getY()<viewHeight/6*4-(viewHeight/40)) {System.exit(0);}


        Log.d("XXXXXXXXXXXXXXXXX", image_fish.size()+"   "+viewHeight+"");
        return true;
    }
}