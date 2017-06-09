package com.gl52.android.epill.fragments;



import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.gl52.android.epill.R;
import com.gl52.android.epill.entities.Medicament;
import com.gl52.android.epill.entities.Ordonnance;
import com.gl52.android.epill.entities.OrdonnanceLab;
import com.gl52.android.epill.entities.PriseMedicament;
import com.gl52.android.epill.entities.Schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dc on 2017/6/5.
 */

public class SuiviListFragment extends ListFragment {
    private ArrayList<PriseMedicament> mPriseMedicaments;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPriseMedicaments = Schedule.get(getActivity()).getPrise(Calendar.getInstance());
        PriseMedicamentAdapter adapter = new PriseMedicamentAdapter(mPriseMedicaments);
        setListAdapter(adapter);

    }
    public void setDate(Calendar calendar){
        mPriseMedicaments = Schedule.get(getActivity()).getPrise(calendar);
        PriseMedicamentAdapter adapter = new PriseMedicamentAdapter(mPriseMedicaments);
        setListAdapter(adapter);
    }

    private class PriseMedicamentAdapter extends ArrayAdapter<PriseMedicament>{
        public PriseMedicamentAdapter(ArrayList<PriseMedicament> priseMedicaments){
            super(getActivity(),0,priseMedicaments);
        };

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_suivi, null);
            }
            PriseMedicament p = getItem(position);
            Ordonnance ordonnance = OrdonnanceLab.get(getActivity()).getOrdonnance(p.getOrdonnanceId());
            Medicament medicament = ordonnance.getMedicament(p.getMedicamentId());
            TextView ordonnanceName = (TextView)convertView.findViewById(R.id.suivi_list_ordonnance_name);
            ordonnanceName.setText(ordonnance.getName());
            TextView description = (TextView)convertView.findViewById(R.id.suivi_list_medicament_info);
            description.setText(medicament.getName()+" to be taken at "+p.getHour()+"h "+p.getMinute()+"m" );
            return convertView;
        }
    }

}
