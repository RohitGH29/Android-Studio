package com.ritech.mcqquestionpractice;

import java.util.ArrayList;

public class Db {
    public static String[] quesId;


    public static ArrayList<Modal> questionArrayList = new ArrayList<>();

    public static void main(String[] args){

        questions();
    }

    public static void questions() {
     questionArrayList = new ArrayList<>();

     quesId=new String[]{
             "1.",
             "2",
             "3",
             "4",
             "5"

     };



        for(int i=0;i<quesId.length;i++){

            Modal ques= new Modal(quesId[i]);
            questionArrayList.add(ques);
        }



    }


}
