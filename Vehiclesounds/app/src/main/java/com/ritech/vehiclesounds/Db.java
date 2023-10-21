package com.ritech.vehiclesounds;

import java.util.ArrayList;

public class Db {

    public static int[] imageId;

    public static int[] audioId;
    public static String[] nameId;

    public static ArrayList<Modal> vehicleArrayList = new ArrayList<>();

    public static void main(String[] args) {

        vehicle();

    }

    public static void vehicle() {

        audioId = new int[]{
                R.raw.cycle,
                R.raw.bike,
                R.raw.car,
                R.raw.train,
                R.raw.truck,
                R.raw.boat,
                R.raw.airship,
                R.raw.jet,
                R.raw.firetruck,
                R.raw.towtruck,
                R.raw.excavator,
                R.raw.rocket


        };

        imageId = new int[]{
                R.drawable.cycle,
                R.drawable.bike,
                R.drawable.car,
                R.drawable.train,
                R.drawable.truck,
                R.drawable.boat,
                R.drawable.airship,
                R.drawable.jet,
                R.drawable.firetruck,
                R.drawable.towtruck,
                R.drawable.excavator,
                R.drawable.rocket


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
                "Fire Truck",
                "Tow Truck",
                "Excavator",
                "Rocket"

        };

        for (int i = 0; i < imageId.length; i++) {

            Modal news = new Modal(imageId[i], audioId[i], nameId[i]);
            Db.vehicleArrayList.add(news);

        }

    }


}
