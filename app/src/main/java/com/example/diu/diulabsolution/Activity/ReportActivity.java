package com.example.diu.diulabsolution.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.diu.diulabsolution.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ReportActivity extends AppCompatActivity {

    private Spinner computerIdSpinner, problemTypeSpinner;
    private EditText problemDescription;
    private String computerId, problemType,roomId;
    private AppCompatButton sendBtn;
    private FirebaseFirestore mFireStore;
    private String mUserId;
    private ProgressBar progressBar;
    //private int notificationId=0;

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
                String problem=problemDescription.getText().toString();
                if(!TextUtils.isEmpty(problem)){
                    Map<String,Object> notificationMessage=new HashMap<>();
                    notificationMessage.put("problem_type",problemType);
                    notificationMessage.put("room_id",roomId);
                    notificationMessage.put("computer_id",computerId);
                    notificationMessage.put("message",problem);
                    notificationMessage.put("from",mUserId);
                    mFireStore.collection("notifications").add(notificationMessage).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(ReportActivity.this, "Send Problem Report", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ReportActivity.this, "don't Send Problem Report", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }else{
                    Toast.makeText(ReportActivity.this, "Give problem description", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        });
    }
}

