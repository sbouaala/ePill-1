package com.gl52.android.epill.fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gl52.android.epill.R;
import com.gl52.android.epill.activities.MedicamentInfoActivity;
import com.gl52.android.epill.entities.Medicament;
import com.gl52.android.epill.entities.Ordonnance;
import com.gl52.android.epill.entities.OrdonnanceLab;

import java.util.ArrayList;

/**
 * Created by dc on 2017/5/17.
 */

public class MedicamentListFragment extends ListFragment {
    private ArrayList<Medicament> mMedicaments;
    private Ordonnance mOrdonnance;
    public static final String EXTRA_ORDONNANCE_ID = "com.gl52.android.epill.ordonnance_id";

    //Register data in the Bundle
    public static MedicamentListFragment newInstance(String ordonnanceId){
        Bundle args = new Bundle();
        args.putString(EXTRA_ORDONNANCE_ID, ordonnanceId);
        MedicamentListFragment fragment = new MedicamentListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get the ordonnance with the id
        String ordonnanceId = (String)getArguments().getString(EXTRA_ORDONNANCE_ID);
        mOrdonnance = OrdonnanceLab.get(getActivity()).getOrdonnance(ordonnanceId);

        if(mOrdonnance == null){
            mOrdonnance = OrdonnanceLab.get(getActivity()).getTemporaryOrdonnance();
        }
        ArrayList<Medicament> medicamentArray = mOrdonnance.getMedicaments();
        MedicamentAdapter adapter = new MedicamentAdapter(medicamentArray);
        setListAdapter(adapter);
    }

    private class MedicamentAdapter extends ArrayAdapter<Medicament> {

        public MedicamentAdapter(ArrayList<Medicament> medicaments){
            super(getActivity(),0,medicaments);
        };

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_medicament, null);
            }
            Medicament m = getItem(position);

            TextView name = (TextView)convertView.findViewById(R.id.medicament_list_item_name);

            name.setText(m.getName());

            TextView description = (TextView)convertView.findViewById(R.id.medicament_list_item_frequence);
            description.setText(m.getFrequence()+" times/day in "+m.getDuration()+" Days");

            return convertView;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Medicament m = ((MedicamentAdapter)getListAdapter()).getItem(position);
        //Start ordonnance activity
        Intent i = new Intent(getActivity(), MedicamentInfoActivity.class);
        i.putExtra(MedicamentInfoFragment.EXTRA_MEDICAMENT_ID, m.getId());
        i.putExtra(MedicamentListFragment.EXTRA_ORDONNANCE_ID,mOrdonnance.getId());
        startActivity(i);
    }

    public void onResume() {
        super.onResume();
        ((MedicamentListFragment.MedicamentAdapter)getListAdapter()).notifyDataSetChanged();
    }

}
