package com.btp_iitj.cnfmanag;


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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 */
public class profilePage2 extends Fragment implements AdapterView.OnItemSelectedListener {

    public static EditText bankNam, ifsccod, transid;
    public static Button nxt,pre;
    private FirebaseFirestore db;
    public static ImageView datePIcker;
    public static FragmentManager fragmentManager;
    public profilePage2() {
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
        Spinner spinner = (Spinner) view.findViewById(R.id.payOptSpinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.paymentInforItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new profilePage3()).commit();
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new profilePage1()).commit();

            }
        });

        return view;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String str= parent.getItemAtPosition(position).toString();
        Toast.makeText(getActivity(), "Item selected for payment Option", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
