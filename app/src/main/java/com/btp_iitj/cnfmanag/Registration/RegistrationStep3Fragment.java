package com.btp_iitj.cnfmanag.Registration;


import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.btp_iitj.cnfmanag.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.btp_iitj.cnfmanag.Core.MainActivityTwo.registration;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationStep3Fragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public static Button finalsave;
    public Calendar c;
    private FirebaseFirestore db;
    public static ImageView datePIcker;
    public static FragmentManager fragmentManager;
    public DatePickerDialog dpd;

    public RegistrationStep3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_page3, container, false);
        FirebaseAuth kAuth;
        kAuth=FirebaseAuth.getInstance();
        final String userId=kAuth.getCurrentUser().getUid();
        Toast.makeText(getActivity(), userId, Toast.LENGTH_SHORT).show();
        Spinner spinner = (Spinner) view.findViewById(R.id.transportSpinner);
        spinner.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.transport_adapter, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        datePIcker=view.findViewById(R.id.datePickr);
        finalsave=view.findViewById(R.id.finalSubmit);
        datePIcker.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                c=Calendar.getInstance();
                int date=c.get(Calendar.DAY_OF_MONTH);
                int month=c.get(Calendar.MONTH);
                int year=c.get(Calendar.YEAR);
                dpd=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        registration.setArrDate(dayOfMonth+"/"+month+"/"+year);

                    }
                },date,month,year);
                dpd.show();
            }
        });
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        finalsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=FirebaseFirestore.getInstance();

                Map<String,Object> myuser = new HashMap<>();
                myuser.put("modeOFtransport",registration.getModeOfTrans());
                myuser.put("accomodation",registration.getAccomodation());
               // myuser.put("dob",registration.getDob());
               // myuser.put("transactinDate",registration.getTransDate());
              //  myuser.put("conferenceId",registration.getConferenceId());
               //myuser.put("conferenceRegisteresId", conf.getName());
                String value=getArguments().getString("username");
                db.collection("RegisteredUser").document(userId)
                        .update(myuser);
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
