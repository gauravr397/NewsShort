package com.example.newsshort;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Slideritems> slideritems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.verticalViewPager);

        slideritems.add(new Slideritems(R.drawable.ic_launcher_background));
        slideritems.add(new Slideritems(R.drawable.ic_launcher_background));
        slideritems.add(new Slideritems(R.drawable.ic_launcher_background));

        verticalViewPager.setAdapter(new ViewPagerAdapter(MainActivity.this,slideritems));
    }
}