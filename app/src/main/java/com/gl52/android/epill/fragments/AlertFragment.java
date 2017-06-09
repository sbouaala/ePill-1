package com.gl52.android.epill.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.WindowManager;

import com.gl52.android.epill.activities.AlertActivity;

/**
 * Created by dc on 2017/6/6.
 */

public class AlertFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /** Turn Screen On and Unlock the keypad when this alert dialog is displayed */
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        /** Creating a alert dialog builder */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        /** Setting title for the alert dialog */
        builder.setTitle("PillApp");

        /** Making it so notification can only go away by pressing the buttons */
        setCancelable(false);



        builder.setMessage("Did you take your ?");

        return builder.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }
}
