package com.btp_iitj.cnfmanag;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import static com.btp_iitj.cnfmanag.MainActivity.conf;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConferenceDetailsFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Suppport";
    public static TextView n,d,v,desc;

    public ConferenceDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_conference_details, container, false);
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


        return view;
    }

}
