package com.ritech.spaceshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class OurSpaceship {

    Context context;
    Bitmap ourSpaceShip;
    int ox, oy;
    Random random;

    public OurSpaceship(Context context){
        this.context=context;
        ourSpaceShip= BitmapFactory.decodeResource(context.getResources(),R.drawable.rocket1);
        random = new Random();
        ox= random.nextInt(SpaceShooter.screenWidth);
        oy= SpaceShooter.screenHeight - ourSpaceShip.getHeight();

    }


    public Bitmap getOurSpaceship(){
        return ourSpaceShip;
    }

    int getOurSpaceshipWidth(){
        return ourSpaceShip.getWidth();
    }

}
