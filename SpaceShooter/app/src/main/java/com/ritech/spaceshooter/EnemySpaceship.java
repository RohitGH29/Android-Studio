package com.ritech.spaceshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.contentcapture.ContentCaptureCondition;

import java.util.Random;

public class EnemySpaceship {

    Context context;
    Bitmap enemySpaceShip;
    int ex, ey;
    int enemyVelocity;
    Random random;

    public EnemySpaceship(Context context){
        this.context=context;
        enemySpaceShip= BitmapFactory.decodeResource(context.getResources(),R.drawable.rocket2);
        random = new Random();
        ex= 200 + random.nextInt(400);
        ey= 0;
        enemyVelocity = 14 + random.nextInt(10);
    }

    public Bitmap getEnemySpaceship(){
        return enemySpaceShip;
    }

    int getEnemySpaceshipWidth(){
        return enemySpaceShip.getWidth();
    }

    int getEnemySpaceshipHeight(){
        return enemySpaceShip.getHeight();
    }

}
