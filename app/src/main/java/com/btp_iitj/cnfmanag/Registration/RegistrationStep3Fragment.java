package com.btp_iitj.cnfmanag.Registration;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.btp_iitj.cnfmanag.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.btp_iitj.cnfmanag.Core.MainActivity.registration;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationStep3Fragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public static Button finalsave;
    private FirebaseFirestore db;

    public RegistrationStep3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_page3, container, false);
        Spinner spinner = (Spinner) view.findViewById(R.id.transportSpinner);
        spinner.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.transport_adapter, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        finalsave=view.findViewById(R.id.finalSubmit);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        finalsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=FirebaseFirestore.getInstance();
                Map<String,Object> myuser = new HashMap<>();
                myuser.put("name",registration.getName());
                myuser.put("phone",registration.getPhone());
                myuser.put("email",registration.getEmail());
                myuser.put("secEmail",registration.getSecemail());
                myuser.put("secMob",registration.getSecmob());
                myuser.put("salutation",registration.getSalutation());
                myuser.put("registrationPackage",registration.getRegPackage());
                myuser.put("paymentMode",registration.getPaymentMode());
                myuser.put("TransId",registration.getTransId());
                myuser.put("BankName",registration.getBankName());
                myuser.put("IfscCode",registration.getIfscCode());
                myuser.put("modeOFtransport",registration.getModeOfTrans());
                myuser.put("accomodation",registration.getAccomodation());
                myuser.put("dob",registration.getDob());
                db.collection("RegisteredUser").document()
                        .set(myuser)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Log.w(TAG, "Error writing document", e);
                            }
                        });
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.yes:
                        // switch to fragment 1
                        registration.setAccomodation("Y");
                        Toast.makeText(getActivity(), "Yes selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.no:
                        // Fragment 2
                        registration.setAccomodation("N");
                        Toast.makeText(getActivity(), "NO selected", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String str= parent.getItemAtPosition(position).toString();
        registration.setModeOfTrans(str);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
