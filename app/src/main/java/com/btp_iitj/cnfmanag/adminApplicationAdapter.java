package com.btp_iitj.cnfmanag;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.btp_iitj.cnfmanag.Conference.Conference_Adapter;
import com.btp_iitj.cnfmanag.Domain_Classes.Conference;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class adminApplicationAdapter extends FirestoreRecyclerAdapter<AdminApplications,adminApplicationAdapter.adminApplication_holder> {



    public adminApplicationAdapter(@NonNull FirestoreRecyclerOptions<AdminApplications> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull adminApplication_holder holder, int position, @NonNull AdminApplications model) {
        holder.cname.setText(model.getUserId());
        Log.d("suthar", "Model: " + model.toString());
    }

    @NonNull
    @Override
    public adminApplication_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.admin_dashboard_cardview,viewGroup,false);
        return new adminApplication_holder(v);
    }

    class adminApplication_holder extends RecyclerView.ViewHolder{
        TextView cname;
        Button acept,rejct;
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        TextView text;

        public adminApplication_holder(@NonNull View itemView) {
            super(itemView);
            cname=itemView.findViewById(R.id.adminapplicationname);
            acept=(Button)itemView.findViewById(R.id.adminConfirm);
            rejct=(Button)itemView.findViewById(R.id.adminReject);
            text=itemView.findViewById(R.id.adminapplicationname);
            acept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str=text.getText().toString();
                    Map<String,Object> myuser = new HashMap<>();
                    myuser.put("RequestStatus","Y");
                    db.collection("RegisteredUser").document(str).update(myuser);

                }
            });
            rejct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str=text.getText().toString();
                    Map<String,Object> myuser = new HashMap<>();
                    myuser.put("RequestStatus","N");
                    db.collection("RegisteredUser").document(str).update(myuser);
                    db.collection("RegisteredUser").document(str)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                   // Toast.makeText(this, "Application Rejected!", Toast.LENGTH_LONG).show();
                                    //Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //Log.w(TAG, "Error deleting document", e);
                                }
                            });
                }
            });

        }

    }


}
