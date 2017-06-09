package com.gl52.android.epill.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.gl52.android.epill.R;

import com.gl52.android.epill.activities.OrdonnanceInfoActivity;
import com.gl52.android.epill.entities.Medicament;
import com.gl52.android.epill.entities.Ordonnance;
import com.gl52.android.epill.entities.OrdonnanceLab;
import com.gl52.android.epill.utils.dbconnection.DbHelper;

import static com.gl52.android.epill.fragments.MedicamentListFragment.EXTRA_ORDONNANCE_ID;

/**
 * Created by dc on 2017/5/18.
 */

public class MedicamentInfoFragment extends Fragment {
    public static String EXTRA_MEDICAMENT_ID = "com.gl52.android.epill.medicament_id";
    public static String EXTRA_MEDICAMENT_EDITABLE = "com.gl52.android.epill.medicament_editable";
    private String mOrdonnanceId;
    private Medicament mMedicament;
    private Boolean mEditable;
    private EditText mNameField;
    private EditText mFrequenceField;
    private EditText mDurationField;
    private Button mConfirmButton;

    public static MedicamentInfoFragment newInstance(String ordonnanceId, String medicamentId, Boolean editable){
        Bundle args = new Bundle();
        args.putString(EXTRA_MEDICAMENT_ID, medicamentId);
        args.putString(EXTRA_ORDONNANCE_ID, ordonnanceId);
        args.putBoolean(EXTRA_MEDICAMENT_EDITABLE,editable);
        MedicamentInfoFragment fragment = new MedicamentInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Medicine's Information");
        //Get the medicine with the id
        String medicamentId = (String)getArguments().getString(EXTRA_MEDICAMENT_ID);
        mOrdonnanceId = (String)getArguments().getString(EXTRA_ORDONNANCE_ID);
        mEditable = (Boolean)getArguments().getBoolean(EXTRA_MEDICAMENT_EDITABLE);
        if(medicamentId == null){
            mMedicament = new Medicament();
            getActivity().setTitle("New Medicine");
        } else {
            mMedicament = OrdonnanceLab.get(getActivity())
                                        .getOrdonnance(this.mOrdonnanceId)
                                        .getMedicament(medicamentId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.info_medicament, parent,false);
        mNameField = (EditText)v.findViewById(R.id.medicament_name);
        mFrequenceField = (EditText)v.findViewById(R.id.medicament_frequence);
        mConfirmButton =(Button)v.findViewById(R.id.medicament_confirm);
        mDurationField = (EditText)v.findViewById(R.id.medicament_duration);

        mNameField.setText(mMedicament.getName());
        mFrequenceField.setText(mMedicament.getFrequence());
        mDurationField.setText(mMedicament.getDuration());
        if(!mEditable){
            mNameField.setEnabled(false);
            mFrequenceField.setEnabled(false);
            mDurationField.setEnabled(false);
            mConfirmButton.setVisibility(View.GONE);
        }


        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMedicament.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mFrequenceField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMedicament.setFrequence(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDurationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMedicament.setDuration(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verify the information
                String name = mMedicament.getName();
                String frequence = mMedicament.getFrequence();
                String duration = mMedicament.getDuration();
                Boolean verified = (name != null)&&(!name.equals(""))
                        &&(frequence != null)&&(!frequence.equals(""))
                        &&(duration != null)&&(!duration.equals(""));
                if(verified){
                    Ordonnance ordonnance = OrdonnanceLab.get(getActivity()).getOrdonnance(mOrdonnanceId);
                    if(ordonnance == null){
                        ordonnance = OrdonnanceLab.get(getActivity()).getTemporaryOrdonnance();
                    }
                    ordonnance.getMedicaments().add(mMedicament);
                    Intent i = new Intent(getActivity(), OrdonnanceInfoActivity.class);
                    i.putExtra(MedicamentListFragment.EXTRA_ORDONNANCE_ID, mOrdonnanceId);
                    i.putExtra(OrdonnanceInfoFragment.EXTRA_ORDONNANCE_EDITABLE,true);
                    startActivity(i);
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity())
                            .setTitle("Error")
                            .setPositiveButton("OK",null);
                    if((name == null)||(name.equals(""))){
                        alert.setMessage("Please input the medicine's name");
                    } else if((frequence == null)||(frequence.equals("")||(frequence.equals("0")))){
                        alert.setMessage("Please input the appropriate frequence");
                    } else if((duration== null)||duration.equals("")||(duration.equals("0"))){
                        alert.setMessage("Please input the appropriate duration");
                    }
                    alert.show();
                }
            }
        });
        return v;
    }

}
