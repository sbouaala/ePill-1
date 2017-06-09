package com.gl52.android.epill.fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gl52.android.epill.R;
import com.gl52.android.epill.activities.OrdonnanceInfoActivity;
import com.gl52.android.epill.entities.Ordonnance;
import com.gl52.android.epill.entities.OrdonnanceLab;

import java.util.ArrayList;

/**
 * Created by dc on 2017/5/16.
 */

public class OrdonnanceListFragment extends ListFragment {
    private ArrayList<Ordonnance> mOrdonnances;


    private class OrdonnanceAdapter extends ArrayAdapter<Ordonnance> {

        public OrdonnanceAdapter(ArrayList<Ordonnance> ordonnances){
            super(getActivity(),0,ordonnances);
        };

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_ordonnance, null);
            }
            Ordonnance o = getItem(position);

            TextView name = (TextView)convertView.findViewById(R.id.ordonnance_list_item_name);

            name.setText(o.getName());

            TextView description = (TextView)convertView.findViewById(R.id.ordonnance_list_item_description);
            description.setText(o.getDescription());


            return convertView;
        }
    }


    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.ordonnanceList_title);
        mOrdonnances = OrdonnanceLab.get(getActivity()).getOrdonnances();
        OrdonnanceAdapter adapter = new OrdonnanceAdapter(mOrdonnances);
        setListAdapter(adapter);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_ordonnance_list, menu);
    }

    //When an ordonnance is selected
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
       Ordonnance o = ((OrdonnanceAdapter)getListAdapter()).getItem(position);
        //Start ordonnance activity
        Intent i = new Intent(getActivity(), OrdonnanceInfoActivity.class);
        i.putExtra(OrdonnanceInfoFragment.EXTRA_ORDONNANCE_ID, o.getId());
        i.putExtra(OrdonnanceInfoFragment.EXTRA_ORDONNANCE_EDITABLE,false);
        startActivity(i);
    }

    //When the add button is pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_new_ordonnance:
                Intent i = new Intent(getActivity(), OrdonnanceInfoActivity.class);
                i.putExtra(OrdonnanceInfoFragment.EXTRA_ORDONNANCE_EDITABLE,true);
                startActivity(i);
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }

    //Refresh the ordonnance list data when resume to the ordonnance list page
    @Override
    public void onResume() {
        super.onResume();
        ((OrdonnanceAdapter)getListAdapter()).notifyDataSetChanged();
    }

}
