package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyApplication extends Application {

    private static FirebaseDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();

    }
    public static String[] parseTimeStamp(Long pickupTimestamp){
        Date date = new Date(pickupTimestamp);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault());
        String formattedDate = dateFormat.format(date);

        String[] parts = formattedDate.split(" ");
        return parts;
    }

    public static String formatTimestamp(long timestamp) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timestamp);
        return DateFormat.format("dd/MM/yyyy", cal).toString();
    }



}
