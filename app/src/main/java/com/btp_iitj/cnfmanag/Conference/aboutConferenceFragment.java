package com.btp_iitj.cnfmanag.Conference;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.btp_iitj.cnfmanag.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.btp_iitj.cnfmanag.Domain_Classes.Conference;

import static com.btp_iitj.cnfmanag.Core.MainActivity.conf;
import static com.btp_iitj.cnfmanag.R.id.Adesc;

public class aboutConferenceFragment extends Fragment {

    private static final String TAG = "Suppport";
    public static TextView a,b,c,d;


    public aboutConferenceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about_conference, container, false);
        a=view.findViewById(R.id.Aname);
        b=view.findViewById(R.id.Adate);
        c=view.findViewById(R.id.Avenue);
       // d=view.findViewsWithText(Adesc);
        a.setText(conf.getName());
        b.setText(conf.getDate());
        c.setText(conf.getVenue());
        d.setText(conf.getDescription());
        return view;
    }

}
