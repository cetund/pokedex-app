package com.ctun.pokeapi.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

import com.ctun.pokeapi.R;

public class  LoadingDialog{
    // 2 objects activity and dialog
    private Activity activity;
    private AlertDialog dialog;

    // constructor of dialog class
    // with parameter activity
        public LoadingDialog(Activity myActivity) {
        activity = myActivity;
    }

    @SuppressLint("InflateParams")
    public void startLoadingdialog() {

        // adding ALERT Dialog builder object and passing activity as parameter
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // layoutinflater object and use activity to get layout inflater

        LayoutInflater inflater = activity.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_loading, null);
        builder.setView(dialoglayout);
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //dialog.getWindow().setLayout(400, 400);

    }

    // dismiss method
    public void dismissdialog() {
        dialog.dismiss();
    }

}
