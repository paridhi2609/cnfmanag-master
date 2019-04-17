package com.btp_iitj.cnfmanag;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.btp_iitj.cnfmanag.Core.MainActivityTwo.conf;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayPRofileFragment extends Fragment {
    private FirebaseFirestore db;
    public static TextView mname,mdob,memail,mmobile,msecemail,msecmobile,msalutation,mpackage,mpaymentmode, mtransid,mtransdate, mbankname,mifsccodem,mmodeoftrans,marrivaldate,maccomodation;
    public String str;


    public DisplayPRofileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_display_profile, container, false);
        mname=view.findViewById(R.id.dname);
        mdob=view.findViewById(R.id.ddob);
        memail=view.findViewById(R.id.demail);
        mmobile=view.findViewById(R.id.dmobile);
        msecemail=view.findViewById(R.id.dsecemail);
        msecmobile=view.findViewById(R.id.dsecmob);
        msalutation=view.findViewById(R.id.dsalutation);
        mpackage=view.findViewById(R.id.dpackage);
        mpaymentmode=view.findViewById(R.id.dpaymentmode);
        mtransid=view.findViewById(R.id.dtransid);
        mtransdate=view.findViewById(R.id.dtransdate);
        mbankname=view.findViewById(R.id.dbnkname);
        mifsccodem=view.findViewById(R.id.difsccode);
        mmodeoftrans=view.findViewById(R.id.dmodeoftrans);
        marrivaldate=view.findViewById(R.id.darrival);
        maccomodation=view.findViewById(R.id.daccomreq);

        db=FirebaseFirestore.getInstance();
        FirebaseAuth kAuth;
        kAuth=FirebaseAuth.getInstance();
        final String userId=kAuth.getCurrentUser().getUid();
        DocumentReference docRef = db.collection("RegisteredUser").document(userId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        str=document.getString("name");
                        mname.setText(str);
                        mdob.setText(document.getString("dob"));
                        memail.setText(document.getString("email"));
                        mmobile.setText(document.getString("phone"));
                        msecemail.setText(document.getString("secEmail"));
                        msecmobile.setText(document.getString("secMob"));
                        msalutation.setText(document.getString("salutation"));
                        mpaymentmode.setText(document.getString("paymentMode"));
                        mtransid.setText(document.getString("TransId"));
                        mtransdate.setText(document.getString("transDate"));
                        mbankname.setText(document.getString("BankName"));
                        mifsccodem.setText(document.getString("IfscCode"));
                        mmodeoftrans.setText(document.getString("modeOFtransport"));
                        marrivaldate.setText(document.getString("ArrivalDate"));
                        maccomodation.setText(document.getString("accomodation"));


                        Log.d("gehloti",str);


                    } else {
                       // Log.d(TAG, "No such document");
                    }
                } else {
                   // Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        return view;
    }

}
