package com.ritech.fruitsandvegetables;

import java.util.ArrayList;

public class Db  {

    public static int[] imageId;
    public static int[] audioId;
    public static String[] nameId;

    public static ArrayList<Modal> vegitableArrayList= new ArrayList<>();

    public static  ArrayList<Modal> fruitArrayList = new ArrayList<>();


    public static void main (String[] args){

        fruits();
        vegitable();

    }

    public static void vegitable() {

        audioId = new int[]{
                R.raw.cabbage,
                R.raw.carrot,
                R.raw.chilli,
                R.raw.tomato,
                R.raw.potato,
                R.raw.lemon,
                R.raw.okra,
                R.raw.onion

        };

        imageId = new int[]{
                R.drawable.cabbage,
                R.drawable.carrot,
                R.drawable.chilli,
                R.drawable.tomato,
                R.drawable.potatoe,
                R.drawable.lemon,
                R.drawable.okra,
                R.drawable.onion


        };

        nameId = new String[]{
                "Cycle",
                "Bike",
                "Car",
                "Train",
                "Truck",
                "Boat",
                "Airship",
                "Jet"

        };

        for (int i = 0; i < imageId.length; i++) {

            Modal news = new Modal(imageId[i], audioId[i], nameId[i]);
            Db.vegitableArrayList.add(news);

        }

    }

    public static void fruits() {

        audioId = new int[]{
                R.raw.apple,
                R.raw.banana,
                R.raw.watermelon,
                R.raw.strawberry,
                R.raw.pomegranate,
                R.raw.papaya,
                R.raw.orange,
                R.raw.mango,
                R.raw.pineapple,
                R.raw.cherry

        };

        imageId = new int[]{
                R.drawable.apple,
                R.drawable.banana,
                R.drawable.watermelon,
                R.drawable.strawberry,
                R.drawable.pomegranate,
                R.drawable.papaya,
                R.drawable.orange,
                R.drawable.mango,
                R.drawable.pineapple,
                R.drawable.cherry

        };

        nameId = new String[]{
                "Zero",
                "One",
                "Two",
                "Three",
                "Four",
                "Five",
                "Six",
                "Seven",
                "Eight",
                "Nine"
        };

        for(int i=0;i<imageId.length;i++){

            Modal news= new Modal(imageId[i],audioId[i],nameId[i]);
            Db.fruitArrayList.add(news);


        }

    }



}
