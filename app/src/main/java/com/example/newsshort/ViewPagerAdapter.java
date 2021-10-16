package com.example.newsshort;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    List<Slideritems> sliderItems;
    LayoutInflater mLayoutInflater;
    Context context;
    ArrayList<String> titles;
    ArrayList<String> desc;
    ArrayList<String> newslinks;
    ArrayList<String> heads;
    VerticalViewPager verticalViewPager;

    int newposition;
    float x1,x2;

    public ViewPagerAdapter(Context context, List<Slideritems> sliderItems, ArrayList<String> titles, ArrayList<String> heads, ArrayList<String> newslinks, ArrayList<String> desc, VerticalViewPager verticalViewPager) {
        this.context = context;
        this.sliderItems = sliderItems;
        this.titles = titles;
        this.desc = desc;
        this.newslinks = newslinks;
        this.heads = heads;
        this.verticalViewPager = verticalViewPager;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return sliderItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout)object);
    }
    @SuppressLint("ClickableViewAccessibility")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_container,container,false);
        ImageView imageView = itemView.findViewById(R.id.imageView);
        ImageView imageView2 = itemView.findViewById(R.id.imageView2);
        TextView title = itemView.findViewById(R.id.headine);
        TextView desctv = itemView.findViewById(R.id.desc);
        TextView head = itemView.findViewById(R.id.head);

        title.setText(titles.get(position));
        desctv.setText(desc.get(position));
        head.setText(heads.get(position));

        Glide.with(context)
                .load(sliderItems.get(position).getImage())
                .into(imageView);
        Glide.with(context)
                .load(sliderItems.get(position).getImage())
                .centerCrop()
                .override(12,12)
                .into(imageView2);

        verticalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                newposition = position;

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        verticalViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        x1 = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = motionEvent.getX();
                        float deltaX = x1-x2;

                        if (deltaX>300) {
                            Intent i = new Intent(context, NewsDetailActivity.class);
                            if (position == 1) {
                                i.putExtra("url", newslinks.get(0));
                                context.startActivity(i);
                            } else {
                                i.putExtra("url", newslinks.get(newposition));
                                context.startActivity(i);
                            }
                        }
                        break;
                }return false;
            }
        });

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
