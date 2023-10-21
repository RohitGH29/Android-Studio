package com.ritech.abcdkids;

import java.util.ArrayList;

public class Db {

    public static int[] imageId;
    public static int[] audioId;

    public static ArrayList<Modal> alphabetsArrayList = new ArrayList<>();
    public static ArrayList<Modal> numberArrayList = new ArrayList<>();

    public static void main(String[] args){

        alphbet();
        number();

    }

    public static void number() {

        numberArrayList = new ArrayList<>();

        audioId=new int[]{
                R.raw.zero,
                R.raw.one,
                R.raw.two,
                R.raw.three,
                R.raw.four,
                R.raw.five,
                R.raw.six,
                R.raw.seven,
                R.raw.eight,
                R.raw.nine
        };

        imageId = new int[]{
                R.drawable.zero,
                R.drawable.one,
                R.drawable.two,
                R.drawable.three,
                R.drawable.four,
                R.drawable.five,
                R.drawable.six,
                R.drawable.seven,
                R.drawable.eight,
                R.drawable.nine
        };
        for(int i=0;i<imageId.length;i++){

            Modal news= new Modal(imageId[i],audioId[i]);
            numberArrayList.add(news);
        }

    }

    public static void alphbet(){

        alphabetsArrayList = new ArrayList<>();

        audioId = new int[]{
                R.raw.a,
                R.raw.b,
                R.raw.c,
                R.raw.d,
                R.raw.e,
                R.raw.f,
                R.raw.g,
                R.raw.h,
                R.raw.i,
                R.raw.j,
                R.raw.k,
                R.raw.l,
                R.raw.m,
                R.raw.n,
                R.raw.o,
                R.raw.p,
                R.raw.q,
                R.raw.r,
                R.raw.s,
                R.raw.t,
                R.raw.u,
                R.raw.v,
                R.raw.w,
                R.raw.x,
                R.raw.y,
                R.raw.z,
        };

        imageId = new int[]{
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e,
                R.drawable.f,
                R.drawable.g,
                R.drawable.h,
                R.drawable.i,
                R.drawable.j,
                R.drawable.k,
                R.drawable.l,
                R.drawable.m,
                R.drawable.n,
                R.drawable.o,
                R.drawable.p,
                R.drawable.q,
                R.drawable.r,
                R.drawable.s,
                R.drawable.t,
                R.drawable.u,
                R.drawable.v,
                R.drawable.w,
                R.drawable.x,
                R.drawable.y,
                R.drawable.z,
        };

        for(int i=0;i<imageId.length;i++){

            Modal news= new Modal(imageId[i],audioId[i]);
            alphabetsArrayList.add(news);
        }



}


}
