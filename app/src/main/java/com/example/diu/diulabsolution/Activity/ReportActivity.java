package com.example.diu.diulabsolution.Activity;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.diu.diulabsolution.Fragment.AuthorityNotificationFragment;
import com.example.diu.diulabsolution.Model.Data;
import com.example.diu.diulabsolution.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ReportActivity extends AppCompatActivity {

    private Spinner computerIdSpinner, problemTypeSpinner;
    private EditText problemDescription;
    private String computerId, problemType,roomId;
    private AppCompatButton sendBtn;
    private FirebaseFirestore mFireStore;
    private String mUserId,problem;
    private ProgressBar progressBar;
    //private int notificationId=0;
    //private String sender_name,sender_id,sender_type,complain_id;
    private String sender_id;
    private String notificationDocId, complainDocId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        mFireStore=FirebaseFirestore.getInstance();
        mUserId=getIntent().getStringExtra("user_id");
        roomId=getIntent().getStringExtra("room_id");
        Toast.makeText(this, "id:"+mUserId+"roomId:"+roomId, Toast.LENGTH_SHORT).show();
        findView();
        setComputerIdSpinner();
        setProblemTypeSpinner();
        sendReport();
    }

    private void findView() {
        computerIdSpinner = (Spinner) findViewById(R.id.computerId);
        problemTypeSpinner = (Spinner) findViewById(R.id.problemType);
        problemDescription = (EditText) findViewById(R.id.input_problem_description);
        sendBtn = (AppCompatButton) findViewById(R.id.btn_send_report);
        progressBar=(ProgressBar)findViewById(R.id.progress_bar_report);
    }

    private void setComputerIdSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.computer_id, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        computerIdSpinner.setAdapter(adapter1);

        computerIdSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        computerId = computerIdSpinner.getItemAtPosition(position).toString();
                        computerId = (String) parent.getItemAtPosition(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }

    private void setProblemTypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.problem_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        problemTypeSpinner.setAdapter(adapter1);

        problemTypeSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        problemType = problemTypeSpinner.getItemAtPosition(position).toString();
                        problemType = (String) parent.getItemAtPosition(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

    }

    private void sendReport() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                 problem=problemDescription.getText().toString();
                if(!TextUtils.isEmpty(problem)) {
                    storeComplain();
                    Toast.makeText(ReportActivity.this, "Send report successfully", Toast.LENGTH_SHORT).show();
                    /*Intent intent=new Intent(ReportActivity.this,AuthorityActivity.class);
                    intent.putExtra("complain_doc_id",complainDocId);
                    intent.putExtra("notify_doc_id",notificationDocId);
                    startActivity(intent);*/
                }else{
                    Toast.makeText(ReportActivity.this, "Give problem description", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    private void storeComplain(){
            Map<String,Object> complains=new HashMap<>();
            complains.put("complain_type",problemType);
            complains.put("room_id",roomId);
            complains.put("computer_id",computerId);
            complains.put("complain_description",problem);
            complains.put("from_user_id",mUserId);




            mFireStore.collection("complains").add(complains).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    //Toast.makeText(ReportActivity.this, "Store complains in firestore", Toast.LENGTH_SHORT).show();
                    Log.i("Report for complains: ","Store complains in firestore");
                     complainDocId=documentReference.getId();
                    Data.shared().setComplainDocId(complainDocId);
                    storeNotification(complainDocId);
                    /*Fragment argumentFragment = new Fragment();//Get Fragment Instance
                    Bundle data = new Bundle();//Use bundle to pass data
                    data.putString("document_id", docId);//put string, int, etc in bundle with a key value
                    argumentFragment.setArguments(data);*/
                    progressBar.setVisibility(View.INVISIBLE);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //Toast.makeText(ReportActivity.this, "error Send Problem Report", Toast.LENGTH_SHORT).show();
                    Log.i("Report for complains: ","Error Store complains in firestore");
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });


    }

    private void storeNotification(final String docId){
        final String notificationTitle="Diu Lab Complain";
        //final String sender_name,sender_id,sender_type,complain_id;
        // String sender_id;
        mFireStore.collection("users").document(mUserId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //user_name=documentSnapshot.getString("name");
                String user_id=documentSnapshot.getString("user_id");
                //Log.i("Sender versity id:",sender_id);
                //user_type=documentSnapshot.getString("user_type");
                Map<String,Object> notifications=new HashMap<>();
                notifications.put("notification_title",notificationTitle);
                notifications.put("notification_sender_id",user_id);
                notifications.put("complain_id",docId);
                notifications.put("complain_type",problemType);

                mFireStore.collection("authorityNotifications").add(notifications).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        notificationDocId=documentReference.getId();
                        Data.shared().setNotifyDocId(notificationDocId);
                        //Toast.makeText(ReportActivity.this, "Notification Store in FireStore", Toast.LENGTH_SHORT).show();
                        Log.i("Report for notify: ","Notification Store in FireStore");
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Toast.makeText(ReportActivity.this, "error Notification Store in FireStore", Toast.LENGTH_SHORT).show();
                        Log.i("Report for notify: ","Error Notification Store in FireStore");
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });


    }

}

