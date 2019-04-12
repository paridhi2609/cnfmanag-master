package com.btp_iitj.cnfmanag.Conference;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.btp_iitj.cnfmanag.R;
import com.btp_iitj.cnfmanag.Registration.RegistrationFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.btp_iitj.cnfmanag.Core.MainActivityTwo.conf;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConferenceDetailsFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Suppport";
    public static TextView n,d,v,desc;
    private static Button bL,bR;
    public static FragmentManager fragmentManager;
    public ConferenceDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_conference_details, container, false);
        bL=view.findViewById(R.id.withdr);
        bR=view.findViewById(R.id.apply);
        String value = getArguments().getString("documentId");
        DocumentReference docRef = db.collection("CONFERENCE").document(value);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {


                        conf.setName(document.getString("name"));
                        conf.setDate(document.getString("date"));
                        conf.setDescription(document.getString("description"));
                        conf.setVenue(document.getString("Venue"));
                        n=getView().findViewById(R.id.descName);
                        d=getView().findViewById(R.id.descDate);
                        v=getView().findViewById(R.id.descVenue);
                        desc=getView().findViewById(R.id.descDescription);
                        n.setText(conf.getName());
                        d.setText(conf.getDate());
                        v.setText(conf.getVenue());
                        desc.setText(conf.getDescription());
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        bL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///check if not applied then cannot withdraw
            }
        });

        bR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container,new RegistrationFragment()).addToBackStack("registrationFragment").commit();
                Toast.makeText(getActivity(), "First fill the Details!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
