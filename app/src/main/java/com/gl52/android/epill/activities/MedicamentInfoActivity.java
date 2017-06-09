package com.gl52.android.epill.activities;

import android.app.Fragment;

import com.gl52.android.epill.fragments.MedicamentInfoFragment;
import com.gl52.android.epill.fragments.MedicamentListFragment;

/**
 * Created by dc on 2017/5/18.
 */

public class MedicamentInfoActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        String ordonnanceId = (String)getIntent().getStringExtra(MedicamentListFragment.EXTRA_ORDONNANCE_ID);
        String medicamentId = (String)getIntent().getStringExtra(MedicamentInfoFragment.EXTRA_MEDICAMENT_ID);
        Boolean editable = (Boolean)getIntent().getBooleanExtra(MedicamentInfoFragment.EXTRA_MEDICAMENT_EDITABLE,false);
        return MedicamentInfoFragment.newInstance(ordonnanceId,medicamentId,editable);
    }
}
