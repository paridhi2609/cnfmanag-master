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

import com.btp_iitj.cnfmanag.HelloBlank;
import com.btp_iitj.cnfmanag.R;
import com.btp_iitj.cnfmanag.Registration.RegistrationFragment;
import com.btp_iitj.cnfmanag.Registration.RegistrationStep1Fragment;
import com.btp_iitj.cnfmanag.Registration.RegistrationStep2Fragment;
import com.btp_iitj.cnfmanag.Registration.RegistrationStep3Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static com.btp_iitj.cnfmanag.Core.MainActivityTwo.conf;
import static com.btp_iitj.cnfmanag.Core.MainActivityTwo.registration;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConferenceDetailsFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Suppport";
    public static TextView n,d,v,desc;
    private static Button bL,bR;
    public String checking;
    public static FragmentManager fragmentManager;
    public static TextView countView;
    public ConferenceDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_conference_details, container, false);
        countView=view.findViewById(R.id.attendents);
        bL=view.findViewById(R.id.withdr);
        bR=view.findViewById(R.id.apply);
        db.collection("RegisteredUser")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            for (DocumentSnapshot document : task.getResult()) {
                                String x=document.getString("RequestStatus");
                                if("Y".equals(x))
                                {Log.d("paridhi",document.getString("RequestStatus"));
                                    count++;}
                                 }
                            countView.setText(String.valueOf(count));
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        FirebaseAuth kAuth;
        kAuth=FirebaseAuth.getInstance();
        final String userId=kAuth.getCurrentUser().getUid();
//        String value = getArguments().getString("documentId");
        DocumentReference docRef = db.collection("CONFERENCE").document("science");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {


                        conf.setName(document.getString("name"));
                        conf.setDate(document.getString("date"));
                        conf.setDescription(document.getString("description"));
                        conf.setVenue(document.getString("venue"));
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

                FirebaseAuth kAuth;
                kAuth=FirebaseAuth.getInstance();
                final String userId=kAuth.getCurrentUser().getUid();
                Map<String,Object> myuser = new HashMap<>();
                myuser.put("RequestStatus","N");

                //ar String value=getArguments().getString("username");
                db.collection("RegisteredUser").document(userId)
                        .update(myuser);
                Toast.makeText(getActivity(), "Withdrawn!", Toast.LENGTH_SHORT).show();



            }
        });

        bR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration.setConferenceId(conf.getName());
                fragmentManager=getActivity().getSupportFragmentManager();
                FirebaseAuth kAuth;
                kAuth=FirebaseAuth.getInstance();
                final String userId=kAuth.getCurrentUser().getUid();
                db.collection("RegisteredUser").document(userId)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                checking=documentSnapshot.getString("RequestStatus");
                                Log.d("pari gehlot",checking+"0");
                                if("R".equals(checking)||"Y".equals(checking)){
                                    Log.d("pari gehlot",checking+"1");
                                    Toast.makeText(getActivity(), "You have already Registered for the Conference!", Toast.LENGTH_LONG).show();
                                    //bR.setEnabled(false);
                                    //return;
                                }
                                else if("N".equals(checking)) {
                                    Log.d("pari gehlot",checking+"2");
                                    //Toast.makeText(getActivity(), "Successfully applied!", Toast.LENGTH_LONG).show();
                                    String check = documentSnapshot.getString("salutation");
                                    String transactionId = documentSnapshot.getString("TransId");
                                    String aco=documentSnapshot.getString("accomodation");
                                    if (check == null) {
                                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new RegistrationStep1Fragment()).addToBackStack("1").commit();
                                    } else if (transactionId == null) {
                                        Toast.makeText(getActivity(), "Resuming from where You Left!", Toast.LENGTH_SHORT).show();
                                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new RegistrationStep2Fragment()).addToBackStack("2").commit();
                                    } else if(aco==null){
                                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new RegistrationStep3Fragment()).addToBackStack("3").commit();
                                    }
                                    else{
                                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new RegistrationStep1Fragment()).addToBackStack("dkf").commit();
                                    }

                                }
                                else if("rejected".equals(checking)){
                                        fragmentManager=getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new RegistrationStep1Fragment()).addToBackStack("dkf").commit();
                                }
                            }
                        });

            }
        });

        return view;
    }

}
