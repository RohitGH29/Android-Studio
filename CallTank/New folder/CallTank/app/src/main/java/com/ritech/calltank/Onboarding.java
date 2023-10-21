package com.ritech.calltank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ritech.calltank.Adapters.SlideAdapter;

public class Onboarding extends AppCompatActivity {

    private ViewPager viewPager;

    private SlideAdapter slideAdapterl;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        getSupportActionBar().hide();

        viewPager = findViewById(R.id.pageView);

        slideAdapterl = new SlideAdapter(this);

        viewPager.setAdapter(slideAdapterl);

        viewPager.addOnPageChangeListener(viewListner);

    }


    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            mCurrentPage = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}