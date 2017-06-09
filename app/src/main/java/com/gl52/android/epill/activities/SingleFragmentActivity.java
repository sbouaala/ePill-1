package com.gl52.android.epill.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gl52.android.epill.R;

/**
 * Created by dc on 2017/5/16.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }
}