package com.gl52.android.epill.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.gl52.android.epill.fragments.AlertFragment;

/**
 * Created by dc on 2017/6/6.
 */

public class AlertActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    /** Creating an Alert Dialog Window */
        AlertFragment alert = new AlertFragment();

        /** Opening the Alert Dialog Window. This will be opened when the alarm goes off */
        alert.show(getFragmentManager(), "AlertAlarm");
    }
    public void doPositiveClick(String name){

    }
    public void doNeutralClick(String name){

    }
    public void doNegativeClick(){

    }

}
