package com.btp_iitj.cnfmanag.Registration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.btp_iitj.cnfmanag.R;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.btp_iitj.cnfmanag.Core.MainActivityTwo.registration;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationStep1Fragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private String str;
    public String money;
    public TextView moneyam;
    public static EditText secEmail,secMob;
    public static Button nex, prev;
    private static final String TAG = "Suppport";
    private FirebaseFirestore db;
    public static FragmentManager fragmentManager;

    public RegistrationStep1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_page1, container, false);
        nex=view.findViewById(R.id.save_page1);
        moneyam=view.findViewById(R.id.display);
        secMob=view.findViewById(R.id.sec_contact);
        prev=view.findViewById(R.id.prev);
        secEmail=view.findViewById(R.id.sec_email);
        Spinner spinner = (Spinner) view.findViewById(R.id.salutation_spinner);
        spinner.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.salutation_adapter, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) view.findViewById(R.id.package_selection_spinner);
        spinner2.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.package_adapter, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        registration.setSecemail(secEmail.getText().toString());
        registration.setSecmob(secMob.getText().toString());



        ///handle next and previous
        nex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new RegistrationStep2Fragment()).addToBackStack("registrationStep2Fragment").commit();

            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new RegistrationFragment()).addToBackStack("registrationFragment").commit();
            }
        });


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        str=parent.getItemAtPosition(position).toString();
        //textView.setText(str);
        Spinner spin = (Spinner)parent;
        Spinner spin2 = (Spinner)parent;
        if(spin.getId() == R.id.salutation_spinner)

        {
            registration.setSalutation(str);
            Toast.makeText(getActivity(), "Your choose :1",Toast.LENGTH_SHORT).show();
        }
        String money="";

        if(spin2.getId() == R.id.package_selection_spinner) {
            registration.setRegPackage(str);
            str=parent.getItemAtPosition(position).toString();
            if(str=="Full Registration")
                money="10000";
            else if(str=="Student")
                money="1000";
            else if(str=="Industry Delegate") {
                money="15000";
            }
           // money="your cost for registration"+money;

            String temp="your amount for registrattion: ";
            moneyam.setText(temp);

            //Toast.makeText(getActivity(), "Your choose : 2", Toast.LENGTH_SHORT).show();
        }

        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
