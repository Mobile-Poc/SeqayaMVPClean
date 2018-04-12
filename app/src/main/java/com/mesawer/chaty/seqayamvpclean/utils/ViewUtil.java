package com.mesawer.chaty.seqayamvpclean.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mesawer.chaty.seqayamvpclean.R;

/**
 * Created by Sara Elmoghazy on 26/03/2018.
 */

public class ViewUtil {
    /**
     * Add shadow to view by setting background in pre LOLLIPOP devices otherwise add elevation
     *
     * @param context context
     * @param view    the view to add shadow for it.
     */
    public static void addShadowToView(Context context, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(context.getResources().getDimension(R.dimen.card_recycler_elevation));
        } else {
            int paddingBottom = view.getPaddingBottom();
            int paddingStart = view.getPaddingStart();
            int paddingEnd = view.getPaddingEnd();
            int paddingTop = view.getPaddingTop();
            view.setBackground(ContextCompat.getDrawable(context, R.drawable.card_bg));
            view.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom);
        }
    }

    public static void setupActionBar(Activity activity, String title) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);
        }
    }
}
