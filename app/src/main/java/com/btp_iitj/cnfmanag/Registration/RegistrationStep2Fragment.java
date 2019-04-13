package com.btp_iitj.cnfmanag.Registration;


import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.btp_iitj.cnfmanag.Core.MainActivityTwo;
import com.btp_iitj.cnfmanag.MainActivity;
import com.btp_iitj.cnfmanag.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.btp_iitj.cnfmanag.Core.MainActivityTwo.registration;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationStep2Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public static EditText bankNam, ifsccod, transid,modofpayment;
    public static Button nxt,pre;
    public Calendar c;
    private FirebaseFirestore db;
    public static ImageView datePIcker;
    public static FragmentManager fragmentManager;
    public DatePickerDialog dpd;
    public RegistrationStep2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_page2, container, false);
        bankNam=view.findViewById(R.id.bankname);
        transid=view.findViewById(R.id.transacIdEDit);
        ifsccod=view.findViewById(R.id.ifscCode);
        nxt =view.findViewById(R.id.nextpage2);
        pre=view.findViewById(R.id.prevpage2);
       datePIcker=view.findViewById(R.id.datePicker);

        registration.setIfscCode(ifsccod.getText().toString());
        registration.setBankName(bankNam.getText().toString());
        Spinner spinner = (Spinner) view.findViewById(R.id.payOptSpinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.paymentInforItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=FirebaseFirestore.getInstance();
                registration.setTransId(transid.getText().toString());
                Toast.makeText(getActivity(), registration.getTransId(), Toast.LENGTH_SHORT).show();
                //registration.setTransDate(registration.getTransDate());
                registration.setBankName(bankNam.getText().toString());
                registration.setIfscCode(ifsccod.getText().toString());
                Map<String,Object> myuser = new HashMap<>();
                myuser.put("paymentMode",registration.getPaymentMode());
                myuser.put("TransId",registration.getTransId());
                myuser.put("BankName",registration.getBankName());
                myuser.put("IfscCode",registration.getIfscCode());
                myuser.put("modeOFtransport",registration.getModeOfTrans());
                myuser.put("accomodation",registration.getAccomodation());
                myuser.put("dob",registration.getDob());
                myuser.put("transactinDate",registration.getTransDate());
                myuser.put("conferenceId",registration.getConferenceId());
                //myuser.put("conferenceRegisteresId", conf.getName());
                String value=getArguments().getString("username");
                Bundle args= new Bundle();
                RegistrationStep3Fragment ldf=new RegistrationStep3Fragment();
                //String id=value;
                args.putString("username",value);
                ldf.setArguments(args);
                db.collection("RegisteredUser").document(value)
                        .update(myuser);
                fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, ldf).addToBackStack("registrationStep2Fragment").commit();

            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new RegistrationStep1Fragment()).addToBackStack("registrationStep1Fragment").commit();

            }
        });
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
                        registration.setTransDate(dayOfMonth+"/"+month+"/"+year);

                    }
                },date,month,year);
                dpd.show();
            }
        });

        return view;

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String str= parent.getItemAtPosition(position).toString();
        registration.setPaymentMode(str);
        Toast.makeText(getActivity(), "Item selected for payment Option", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
