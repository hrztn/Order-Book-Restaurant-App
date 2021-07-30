package com.example.makanat.modules;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.makanat.R;

public class SlideViewAdapter extends PagerAdapter {

    Context ctx;

    public SlideViewAdapter(Context context) {
        this.ctx = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_screen, container, false);


        ImageView mainImage = view.findViewById(R.id.ss_logoImage);
        TextView title = view.findViewById(R.id.ss_titleText);
        TextView body = view.findViewById(R.id.ss_bodyText);

        switch (position) {
            case 0:
                mainImage.setImageResource(R.drawable.logo_transparent);
                title.setText("Welcome");
                body.setText("We are now available ONLINE!!");

                break;

            case 1:
                mainImage.setImageResource(R.drawable.order_ss);
                title.setText("Order Now!");
                body.setText("Order with us online now! All of your favourites are now available at the tip of your finger!");
                break;
            case 2:
                mainImage.setImageResource(R.drawable.book_ss);
                title.setText("Booking Now!");
                body.setText("Why wait for the long queue when you are now able to cut lines by booking with us here!");
                break;

        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
