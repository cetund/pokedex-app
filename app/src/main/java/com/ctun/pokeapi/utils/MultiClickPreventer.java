package com.ctun.pokeapi.utils;

import android.view.View;

public class MultiClickPreventer {
    private static final long DELAY_IN_MS = 1500;

    private MultiClickPreventer(){
        //empty
    }

    public static void preventMultiClick(final View view) {
        if (!view.isClickable()) {
            return;
        }
        view.setEnabled(false);
        view.setClickable(false);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
                view.setClickable(true);
            }
        }, DELAY_IN_MS);
    }
}