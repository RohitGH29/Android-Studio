package com.ritech.shapeslearningforkids;

import java.util.ArrayList;

public class Db {

    public static int[] imageId;
    public static int[] audioId;
    public static String[] nameId;

    public static ArrayList<Modal> shapesArrayList= new ArrayList<>();




    public static void main (String[] args){


        shapes();

    }

    public static void shapes() {

        audioId = new int[]{
                R.raw.star,
                R.raw.triangle,
                R.raw.square,
                R.raw.rectangle,
                R.raw.oval,
                R.raw.rhombus,
                R.raw.cube,
                R.raw.cuboid,
                R.raw.circle

        };

        imageId = new int[]{
                R.drawable.star,
                R.drawable.traingle,
                R.drawable.square,
                R.drawable.rectangle,
                R.drawable.oval,
                R.drawable.rhombus,
                R.drawable.cube,
                R.drawable.cuboid,
                R.drawable.circle


        };

        nameId = new String[]{
                "Cycle",
                "Bike",
                "Car",
                "Train",
                "Truck",
                "Boat",
                "Airship",
                "Jet",
                "Jet"

        };

        for (int i = 0; i < imageId.length; i++) {

            Modal news = new Modal(imageId[i], audioId[i], nameId[i]);
            Db.shapesArrayList.add(news);

        }

    }





}
