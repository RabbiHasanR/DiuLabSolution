package com.example.diu.diulabsolution.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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

import com.example.diu.diulabsolution.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {

    private static final int PICK_IMAGE=1;
    private EditText inputId,inputName,inputEmail,inputPassword;
    private Spinner userTypeSpinner;
    private String user_id,user_name,user_email,user_password,user_type;
    private StorageReference mStorageRef;
    private FirebaseFirestore mFiresStore;
    private FirebaseAuth mAuth;
    private Uri imageUri;
    private ProgressBar progressBar;
    private CircleImageView imageBtn;
    private AppCompatButton signUp_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        imageUri=null;
        mStorageRef=FirebaseStorage.getInstance().getReference().child("images");
        mAuth=FirebaseAuth.getInstance();
        mFiresStore=FirebaseFirestore.getInstance();
        findview();
        setUserTypeSpinner();
        takeImage();
        signUp();

    }

    public void findview(){
        inputId=(EditText)findViewById(R.id.input_id);
        inputName=(EditText)findViewById(R.id.input_name);
        inputEmail=(EditText)findViewById(R.id.input_email);
        inputPassword=(EditText)findViewById(R.id.input_password);
        userTypeSpinner=(Spinner)findViewById(R.id.signUp_userType);
        progressBar=(ProgressBar)findViewById(R.id.progress_bar_signup);
        imageBtn=(CircleImageView)findViewById(R.id.signUp_image_btn);
        signUp_btn=(AppCompatButton)findViewById(R.id.btn_signup);
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
                        user_type=userTypeSpinner.getItemAtPosition(position).toString();
                        user_type=(String)parent.getItemAtPosition(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }

    private void storeData(){
        if(imageUri!=null){
            progressBar.setVisibility(View.VISIBLE);
            user_id=inputId.getText().toString();
            user_name=inputName.getText().toString();
            user_email=inputEmail.getText().toString();
            user_password=inputPassword.getText().toString();

            if(!TextUtils.isEmpty(user_id)&&!TextUtils.isEmpty(user_name)&&!TextUtils.isEmpty(user_email)&&!TextUtils.isEmpty(user_password) ){
                mAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            final String userId=mAuth.getCurrentUser().getUid();
                            StorageReference user_profile=mStorageRef.child(userId+".jpg");
                            user_profile.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> uploadTask) {
                                    if(uploadTask.isSuccessful()){
                                        String download_url=uploadTask.getResult().getUploadSessionUri().toString();
                                        Map<String,Object> userMap=new HashMap<>();
                                        userMap.put("user_id",user_id);
                                        userMap.put("email",user_email);
                                        userMap.put("password",user_password);
                                        userMap.put("name",user_name);
                                        userMap.put("user_type",user_type);
                                        userMap.put("images",download_url);
                                        mFiresStore.collection("users").document(userId).set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                sendToSignIn();
                                            }
                                        });
                                    }
                                    else{
                                        Toast.makeText(SignUpActivity.this, "Error to SignUp"+uploadTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Log.i("Error for cloud store:",uploadTask.getException().getMessage());
                                    }

                                }
                            });

                        }else{
                            Toast.makeText(SignUpActivity.this, "Error to SignUp"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            Log.i("Error for auth:",task.getException().getMessage());
                        }
                    }
                });
            }
        }
    }

    private void sendToSignIn(){
        Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
        startActivity(intent);
        finish();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private void uploadImage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select picture"),PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE){
            imageUri=data.getData();
            imageBtn.setImageURI(imageUri);
        }

    }

    private void signUp(){
        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeData();
            }
        });
    }

    private void takeImage(){
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }
}
