package com.btp_iitj.cnfmanag.Registration;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.btp_iitj.cnfmanag.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.btp_iitj.cnfmanag.Core.MainActivity.registration;

public class RegistrationFragment extends Fragment {
    public static EditText name, dob, mobile, email;
    public static Button save;
    private static final String TAG = "Suppport";
    public DocumentReference docref;
    public static FragmentManager fragmentManager;
    private FirebaseFirestore db;
    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit_profile, container, false);
        name=view.findViewById(R.id.uname);
        dob=view.findViewById(R.id.udob);
        mobile= view.findViewById(R.id.uphone);
        email=view.findViewById(R.id.uemail);
        save=view.findViewById(R.id.save_user);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                registration.setName(name.getText().toString());
                registration.setDob(dob.getText().toString());
                registration.setEmail(email.getText().toString());
                registration.setPhone(mobile.getText().toString());

                fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new RegistrationStep1Fragment()).commit();
            }
        });


        return view;
    }

}
