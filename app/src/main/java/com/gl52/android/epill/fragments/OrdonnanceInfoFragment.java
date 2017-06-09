package com.gl52.android.epill.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
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
import com.gl52.android.epill.activities.MainActivity;
import com.gl52.android.epill.activities.MedicamentInfoActivity;
import com.gl52.android.epill.entities.Ordonnance;
import com.gl52.android.epill.entities.OrdonnanceLab;

/**
 * Created by dc on 2017/5/23.
 */

public class OrdonnanceInfoFragment extends Fragment {
    private Ordonnance mOrdonnance;
    private Boolean mEditable;
    private EditText mNameField;
    private EditText mMailDocField;
    private EditText mDescriptionField;
    private Button mConfirmButton;
    private Button mAddButton;
    public static final String EXTRA_ORDONNANCE_ID = "com.gl52.android.epill.ordonnance_id";
    public static final String EXTRA_ORDONNANCE_EDITABLE = "com.gl52.android.epill.ordonnance_editable";


    public static Fragment newInstance(String ordonnanceId,Boolean editable){

        Bundle args = new Bundle();
        args.putString(EXTRA_ORDONNANCE_ID, ordonnanceId);
        args.putBoolean(EXTRA_ORDONNANCE_EDITABLE,editable);
        OrdonnanceInfoFragment fragment = new OrdonnanceInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String ordonnanceId = (String) getArguments().getString(EXTRA_ORDONNANCE_ID);
        Boolean editable = (Boolean) getArguments().getBoolean(EXTRA_ORDONNANCE_EDITABLE);
        mEditable = editable;
        mOrdonnance = OrdonnanceLab.get(getActivity()).getOrdonnance(ordonnanceId);
        getActivity().setTitle("Prescription's information");
        if(editable) {
            if(ordonnanceId == null){
                getActivity().setTitle("New Prescription");
                mOrdonnance = OrdonnanceLab.get(getActivity()).getTemporaryOrdonnance();
                if(mOrdonnance == null){
                    mOrdonnance = new Ordonnance();
                    OrdonnanceLab.get(getActivity()).setTemporaryOrdonnance(mOrdonnance);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.info_ordonnance, parent, false);
        mNameField = (EditText) v.findViewById(R.id.ordonnance_name);
        mMailDocField = (EditText) v.findViewById(R.id.ordonnance_mailDoc);
        mDescriptionField = (EditText) v.findViewById(R.id.ordonnance_description);
        mConfirmButton = (Button) v.findViewById(R.id.medicament_confirm);
        mAddButton = (Button) v.findViewById(R.id.medicament_add);

        // When the present activity is to show the ordonnance's information
        mNameField.setText(mOrdonnance.getName());
        mMailDocField.setText(mOrdonnance.getMailDoc());
        mDescriptionField.setText(mOrdonnance.getDescription());
        if(!mEditable){
            // Disable the input
            mNameField.setEnabled(false);
            mMailDocField.setEnabled(false);
            mDescriptionField.setEnabled(false);
            //Hide the confirm and add buttons
            mConfirmButton.setVisibility(View.GONE);
            mAddButton.setVisibility(View.GONE);
        }

        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mOrdonnance.setName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        mMailDocField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mOrdonnance.setMailDoc(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mOrdonnance.setDescription(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verify the information
                String name = mOrdonnance.getName();
                String mail = mOrdonnance.getMailDoc();
                Boolean verified = (name != null)&&(!name.equals(""))
                                &&(mail != null)&&(!mail.equals(""))
                                &&(mOrdonnance.getMedicaments()!= null)
                                &&(mOrdonnance.getMedicaments().size()>0);
                if(verified){
                    OrdonnanceLab.get(getActivity()).addOrdonnance(mOrdonnance);
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity())
                                                                .setTitle("Error")
                                                                .setPositiveButton("OK",null);
                    if((name == null)||(name.equals(""))){
                        alert.setMessage("Please input the prescription's name");
                    } else if((mail == null)||(mail.equals(""))) {
                        alert.setMessage("Please input your doctor's E-mail");
                    } else if(((mOrdonnance.getMedicaments()== null)||mOrdonnance.getMedicaments().size() == 0)){
                        alert.setMessage("Please input the medicines in this prescription");
                    }
                    alert.show();
                }
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(getActivity(), MedicamentInfoActivity.class);
            i.putExtra(MedicamentListFragment.EXTRA_ORDONNANCE_ID, mOrdonnance.getId());
            i.putExtra(MedicamentInfoFragment.EXTRA_MEDICAMENT_EDITABLE,true);
            startActivity(i);
            }
        });

        return v;
    }
}
