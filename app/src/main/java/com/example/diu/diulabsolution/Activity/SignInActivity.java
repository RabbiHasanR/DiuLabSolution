package com.example.diu.diulabsolution.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diu.diulabsolution.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SignInActivity extends AppCompatActivity {

    private TextView signUP;
    private AppCompatButton signIn;
    private Spinner userTypeSpinner;
    private EditText inputEmail,inputPassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private String userType;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser!=null){
            
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth=FirebaseAuth.getInstance();

        findView();
        signUP.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(),SignUpActivity.class);
                        startActivity(intent);
                    }
                }
        );
        setUserTypeSpinner();
        login();
    }

    public void findView(){
        signIn=(AppCompatButton)findViewById(R.id.btn_login);
        signUP=(TextView)findViewById(R.id.link_signup);
        userTypeSpinner=(Spinner)findViewById(R.id.signIn_userType);
        inputEmail=(EditText)findViewById(R.id.input_signin_email);
        inputPassword=(EditText)findViewById(R.id.input_signin_password);
        progressBar=(ProgressBar)findViewById(R.id.progress_bar_signin);
    }

    private void setUserTypeSpinner(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.user_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        userTypeSpinner.setAdapter(adapter1);

        userTypeSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        userType=userTypeSpinner.getItemAtPosition(position).toString();
                        userType=(String)parent.getItemAtPosition(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }

    private void sendToAuthority(){
        Intent intent=new Intent(getApplicationContext(),AuthorityActivity.class);
        startActivity(intent);
    }
    private void sendToUser(){
        Intent intent=new Intent(getApplicationContext(),UserActivity.class);
        startActivity(intent);
    }

    private void login(){
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=inputEmail.getText().toString();
                String password=inputPassword.getText().toString();

                if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)){
                   progressBar.setVisibility(View.VISIBLE);
                   mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               if(userType.equalsIgnoreCase("authority")){
                                   sendToAuthority();
                                   progressBar.setVisibility(View.INVISIBLE);
                               }
                               else {
                                   sendToUser();
                                   progressBar.setVisibility(View.INVISIBLE);
                               }
                           }else {
                               Toast.makeText(SignInActivity.this, "Signin failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                               progressBar.setVisibility(View.INVISIBLE);
                           }
                       }
                   });
                }
                else {
                    Toast.makeText(SignInActivity.this, "Please give email and password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
