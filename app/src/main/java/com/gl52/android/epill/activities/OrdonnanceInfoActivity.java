package com.gl52.android.epill.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gl52.android.epill.R;
import com.gl52.android.epill.fragments.MedicamentListFragment;
import com.gl52.android.epill.fragments.OrdonnanceInfoFragment;

/**
 * Created by dc on 2017/5/23.
 */

public class OrdonnanceInfoActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ordonnance);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        String ordonnanceId = getIntent().getStringExtra(OrdonnanceInfoFragment.EXTRA_ORDONNANCE_ID);
        Boolean editable = getIntent().getBooleanExtra(OrdonnanceInfoFragment.EXTRA_ORDONNANCE_EDITABLE,false);
        
        //// TODO: 2017/5/24  not null judgement
        Fragment infoFragment = OrdonnanceInfoFragment.newInstance(ordonnanceId,editable);
        Fragment listFragment = MedicamentListFragment.newInstance(ordonnanceId);
        transaction.add(R.id.info_ordonnance,infoFragment);
        transaction.add(R.id.list_item_medicament,listFragment).commit();
    }
}

