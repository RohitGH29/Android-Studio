package com.ritech.fruitsandvegitablesforkids;

import java.util.ArrayList;

public class Db {

    public static int[] imageId;

    public static int[] audioId;
    public static String[] nameId;

    public static ArrayList<Modal> vehicleArrayList= new ArrayList<>();

    public static  ArrayList<Modal> animalArrayList = new ArrayList<>();

    public static  ArrayList<Modal> alphabetsArrayList = new ArrayList<>();

    public static  ArrayList<Modal> numberArrayList = new ArrayList<>();
    public static ArrayList<Modal> vegetableArrayList= new ArrayList<>();

    public static  ArrayList<Modal> fruitArrayList = new ArrayList<>();


    public static void main(String[] args){

        animal();

        alphabets();

        number();
        
        vehicle();

        fruits();

        vegetable();

}

    public static void vehicle() {

        audioId = new int[]{
                R.raw.cycle,
                R.raw.bike,
                R.raw.car,
                R.raw.train,
                R.raw.truck,
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

    public static void number() {

        audioId = new int[]{
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
        Db.numberArrayList.add(news);


    }

}

    public static void alphabets() {

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
                R.raw.z


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
                R.drawable.z


        };

        nameId = new String[]{
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        };

        for(int i=0;i<imageId.length;i++){

            Modal news= new Modal(imageId[i],audioId[i],nameId[i]);
            Db.alphabetsArrayList.add(news);


    }

    }

    public static void animal() {

            audioId = new int[]{
                    R.raw.cat,
                    R.raw.cow,
                    R.raw.crow,
                    R.raw.dog,
                    R.raw.duck,
                    R.raw.eagle,
                    R.raw.elephant,
                    R.raw.fox,
                    R.raw.hen,
                    R.raw.horse,
                    R.raw.lion,
                    R.raw.monkey,
                    R.raw.ostrich,
                    R.raw.parrot,
                    R.raw.pig,
                    R.raw.pigeon,
                    R.raw.rabbit,
                    R.raw.sheep


            };

            imageId = new int[]{
                    R.drawable.cat,
                    R.drawable.cow,
                    R.drawable.crow,
                    R.drawable.dog,
                    R.drawable.duck,
                    R.drawable.eagle,
                    R.drawable.elephant,
                    R.drawable.fox,
                    R.drawable.hen,
                    R.drawable.horse,
                    R.drawable.lion,
                    R.drawable.monkey,
                    R.drawable.ostrich,
                    R.drawable.parrot,
                    R.drawable.pig,
                    R.drawable.pigeon,
                    R.drawable.rabbit,
                    R.drawable.sheep
            };

            nameId = new String[]{
                   "Cat",
                   "Cow",
                   "Crow",
                   "Dog",
                   "Duck",
                   "Eagle",
                   "Elephant",
                   "Fox",
                   "Hen",
                   "Horse",
                   "Lion",
                   "Monkey",
                   "Ostrich",
                   "Parrot",
                   "Pig",
                   "Pigeon",
                   "Rabbit",
                   "Sheep"

            };

            for(int i=0;i<imageId.length;i++){

                Modal news= new Modal(imageId[i],audioId[i],nameId[i]);
                Db.animalArrayList.add(news);

        }
    }

    public static void vegetable() {

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
            Db.vegetableArrayList.add(news);

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


