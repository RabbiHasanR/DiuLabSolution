package com.example.diu.diulabsolution.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.diu.diulabsolution.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class NotificationDetailsActivity extends AppCompatActivity {

    private TextView senderIdView,complainTypeView,roomIdView,computerIdView,complainDescriptionView;
    private FirebaseFirestore mFirestore;
    private String documentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
        mFirestore=FirebaseFirestore.getInstance();
        findView();
    }

    public void findView(){
        senderIdView=(TextView)findViewById(R.id.sender_id);
        complainTypeView=(TextView)findViewById(R.id.complain_type);
        roomIdView=(TextView)findViewById(R.id.room_id);
        computerIdView=(TextView)findViewById(R.id.computer_id);
        complainDescriptionView=(TextView)findViewById(R.id.complain_description);

    }

    private void getDataFromFireStoreAndSetInProfile(){
        mFirestore.collection("complains").document(documentId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
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
}
