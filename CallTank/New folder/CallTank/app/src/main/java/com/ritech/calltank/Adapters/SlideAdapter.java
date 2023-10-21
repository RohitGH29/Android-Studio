package com.ritech.calltank.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ritech.calltank.AudioActivity;
import com.ritech.calltank.Constant;
import com.ritech.calltank.MainActivity;
import com.ritech.calltank.ModelClass;
import com.ritech.calltank.R;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    RelativeLayout relativeLayout1 ,relativeLayout2 ;

    File[] files;
    ArrayList<ModelClass> fileslist = new ArrayList<>();


    public SlideAdapter(Context context) {
        this.context = context;

    }


    //Array
    public int[] slide_images = {
            R.drawable.intro_call,
            R.drawable.intro_call4,
            R.drawable.intro_call4
    };

    public String[] slide_heading = {

            "Please allow auto call recording on your device to get better experience",
            "Please make a dummy call",
            "Is this is your recorded dummy call ?"
    };

    public String[] slide_btn = {

            "I Got it, Continue",
            "Please Make a dummy call",
            "No"
    };

    public String[] slide_des = {

            "",
            "It helps us to take accurate location of your recorded calls",
            ""
    };


    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.intro_item_layout, container, false);

        // fetch data
        getData();


        TextView head = (TextView) view.findViewById(R.id.introtext);
        TextView head2 = (TextView) view.findViewById(R.id.introtext2);
        TextView des = (TextView) view.findViewById(R.id.introdesc);
        TextView recHead = (TextView) view.findViewById(R.id.recText);
        TextView recTime = (TextView) view.findViewById(R.id.recTime);
        TextView recSource = (TextView) view.findViewById(R.id.recSource);
        ImageView img = (ImageView) view.findViewById(R.id.introImg);
        Button btn = (Button) view.findViewById(R.id.introB);
        Button noB = (Button) view.findViewById(R.id.noB);
        Button yesB = (Button) view.findViewById(R.id.yesB);

        ImageView playB = (ImageView) view.findViewById(R.id.play);
        ImageView pauseB = (ImageView) view.findViewById(R.id.pause);

        relativeLayout1 = (RelativeLayout) view.findViewById(R.id.relative1);
        relativeLayout2 = (RelativeLayout) view.findViewById(R.id.relativ2);

        relativeLayout1.setVisibility(View.VISIBLE);
        relativeLayout2.setVisibility(View.GONE);



        img.setImageResource(slide_images[position]);
        head.setText(slide_heading[position]);
        des.setText(slide_des[position]);
        btn.setText(slide_btn[position]);




        if (position == 1) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"));//change the number
                    context.startActivity(callIntent);
                }
            });
        }

        if (position == 2) {
            relativeLayout2.setVisibility(View.VISIBLE);
            relativeLayout1.setVisibility(View.INVISIBLE);


            if (fileslist.size() !=0) {
                // Fetch data from adapter
                final ModelClass modelClass = fileslist.get(1);

                MediaPlayer mediaPlayer = new MediaPlayer();
                String audioPath = modelClass.getUri().toString();
                try {
                    mediaPlayer.setDataSource(audioPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String file = modelClass.getFilename();
                Long dateTime = modelClass.getDate();
                String currentDate = DateFormat.getDateInstance().format(dateTime);

                head2.setText(slide_heading[position]);
                recHead.setText(file);
                recTime.setText(currentDate);
                recSource.setText("Call");

                playB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mP) {
                                    mP.start();
                                    playB.setVisibility(View.INVISIBLE);
                                    pauseB.setVisibility(View.VISIBLE);
                                }
                            });

                            mediaPlayer.prepare();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });

                pauseB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.stop();
                       playB.setVisibility(View.VISIBLE);
                       pauseB.setVisibility(View.INVISIBLE);
                    }
                });


            }
            else
            {
                Toast.makeText(context, "No files", Toast.LENGTH_SHORT).show();
            }



        }

        container.addView(view);

        return view;
    }





    public ArrayList<ModelClass> getData() {

        ModelClass f;
        String targetpath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.FOLDER_NAME;
        File targetdir = new File(targetpath);
        files = targetdir.listFiles();
        if (files.length != 0) {
            for (int i = files.length - 1; i > files.length - 2; i--) {
                File file = files[i];
                f = new ModelClass();
                f.setUri(Uri.fromFile(file));
                f.setPath(files[i].getAbsolutePath());
                f.setDate((file.lastModified()));
                f.setFilename(file.getName());
                if (!f.getUri().toString().endsWith(".nomedia")) {
                    fileslist.add(f);
                }
            }
        } else {
            Toast.makeText(context, "Recording", Toast.LENGTH_SHORT).show();

        }
        return fileslist;
    }




    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //container.removeView(container);
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);


    }


}
