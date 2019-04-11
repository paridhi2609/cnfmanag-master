package com.btp_iitj.cnfmanag;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class profilePage1 extends Fragment implements AdapterView.OnItemSelectedListener {
    private String str;
    public String money;
    public TextView moneyam;
    public static EditText secEmail;
    public static Button nex, prev;
    private static final String TAG = "Suppport";
    private FirebaseFirestore db;
    public static FragmentManager fragmentManager;

    public profilePage1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_page1, container, false);
        nex=view.findViewById(R.id.save_page1);
        moneyam=view.findViewById(R.id.display);
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

        ///handle next and previous
        nex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String salutation1=str;
                final String secondemail=secEmail.getText().toString();

                Map<String, Object> conference_user = new HashMap<>();
                db=FirebaseFirestore.getInstance();
                conference_user.put("salutation",salutation1);
                conference_user.put("secondaryEmail",secondemail);


                Toast.makeText(getActivity(), "Data successfully Edited", Toast.LENGTH_SHORT).show();


                db.collection("CONFERENCE_USER").document()
                        .set(conference_user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
                fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new profilePage2()).commit();

            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new EditProfileFragment()).commit();
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
            Toast.makeText(getActivity(), "Your choose :1",Toast.LENGTH_SHORT).show();
        }
        String money="";

        if(spin2.getId() == R.id.package_selection_spinner) {
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
