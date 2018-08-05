package com.example.diu.diulabsolution.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.diu.diulabsolution.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NotificationDetailsActivity extends AppCompatActivity {

    private TextView senderIdView,complainTypeView,roomIdView,computerIdView,complainDescriptionView;
    private FirebaseFirestore mFirestore;
    private String complainDocId,mUserId;
    private AppCompatButton seeBtn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
        mAuth=FirebaseAuth.getInstance();
        mFirestore=FirebaseFirestore.getInstance();
        complainDocId=getIntent().getExtras().getString("complain_doc_id");
        mUserId=getIntent().getExtras().getString("current_user_id");
        Toast.makeText(this, "complain Doc id: "+complainDocId+"current user Id:"+mUserId, Toast.LENGTH_SHORT).show();

        findView();
        getDataFromFireStoreAndSetInProfile();
        seeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserNotification();
                Toast.makeText(NotificationDetailsActivity.this, "Send notification to user", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void findView(){
        senderIdView=(TextView)findViewById(R.id.sender_id);
        complainTypeView=(TextView)findViewById(R.id.complain_type);
        roomIdView=(TextView)findViewById(R.id.room_id);
        computerIdView=(TextView)findViewById(R.id.computer_id);
        complainDescriptionView=(TextView)findViewById(R.id.complain_description);
        seeBtn=(AppCompatButton)findViewById(R.id.btn_see);

    }

    private void getDataFromFireStoreAndSetInProfile(){
        mFirestore.collection("complains").document(complainDocId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //String sender_id=documentSnapshot.getString("name");
                String complain_type=documentSnapshot.getString("complain_type");
                String room_id=documentSnapshot.getString("room_id");
                String computer_id=documentSnapshot.getString("computer_id");
                String complain_description=documentSnapshot.getString("complain_description");


                //set data
                //senderIdView.setText(sender_id);
                complainTypeView.setText(complain_type);
                roomIdView.setText(room_id);
                computerIdView.setText(computer_id);
                complainDescriptionView.setText(complain_description);
            }
        });
    }

    private void storeUserNotification(){
        final String authorityId=mAuth.getCurrentUser().getUid();
        final String userNotificationTitle="Solve Complain";
        mFirestore.collection("users").document(mUserId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String seeNotifyUserId=documentSnapshot.getString("user_id");
                String seeNotifyUserName=documentSnapshot.getString("name");
                String seeNotifyUserType=documentSnapshot.getString("user_type");

                Map<String,Object> notifications=new HashMap<>();
                notifications.put("notification_title",userNotificationTitle);
                notifications.put("see_notify_user_id",seeNotifyUserId);
                notifications.put("see_notify_user_name",seeNotifyUserName);
                notifications.put("see_notify_user_type",seeNotifyUserType);
                notifications.put("see_notify_authority_id",authorityId);
                mFirestore.collection("userNotifications").add(notifications).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(NotificationDetailsActivity.this, "User notification store successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NotificationDetailsActivity.this, "Error user notification store", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
