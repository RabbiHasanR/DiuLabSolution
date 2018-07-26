package com.example.diu.diulabsolution.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.diu.diulabsolution.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
      private View rootView;
    private Spinner userTypeSpinner;
    private LinearLayout detailsLayout,editLayout;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private TextView profileName,profileId,profileEmail,profileType,profilePassword;
    private CircleImageView profileImage;
    private String mUserId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_profile, container, false);
        mAuth=FirebaseAuth.getInstance();
        mFirestore=FirebaseFirestore.getInstance();
        findView();
        //profileDetailsVisibility();
        //profileEditVisibility();
        setUserTypeSpinner();
        getDataFromFireStoreAndSetInProfile();
        return rootView;
    }

    public void findView(){
        userTypeSpinner=(Spinner)rootView.findViewById(R.id.signUp_userType_edit);
        detailsLayout=(LinearLayout)rootView.findViewById(R.id.display_info_layout);
        editLayout=(LinearLayout)rootView.findViewById(R.id.edit_info_layout);
        profileName=(TextView)rootView.findViewById(R.id.profile_user_name);
        profileId=(TextView)rootView.findViewById(R.id.profile_user_id);
        profileEmail=(TextView)rootView.findViewById(R.id.profile_user_email);
        profileType=(TextView)rootView.findViewById(R.id.profile_user_type);
        profilePassword=(TextView)rootView.findViewById(R.id.profile_user_password);
        profileImage=(CircleImageView)rootView.findViewById(R.id.profile_image);
    }

    public void profileDetailsVisibility(){
        detailsLayout.setVisibility(View.VISIBLE);
        editLayout.setVisibility(View.GONE);
    }
    public void profileEditVisibility(){
        detailsLayout.setVisibility(View.GONE);
        editLayout.setVisibility(View.VISIBLE);
        setUserTypeSpinner();
    }

    private void setUserTypeSpinner(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.user_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        userTypeSpinner.setAdapter(adapter1);

        userTypeSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //Intent intent=new Intent(getApplicationContext(),LabActivity.class);
                        //startActivity(intent);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }

    private void getDataFromFireStoreAndSetInProfile(){
        mUserId=mAuth.getCurrentUser().getUid();
        mFirestore.collection("users").document(mUserId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String user_name=documentSnapshot.getString("name");
                String user_id=documentSnapshot.getString("user_id");
                String user_email=documentSnapshot.getString("email");
                String user_password=documentSnapshot.getString("password");
                String user_type=documentSnapshot.getString("user_type");
                String user_images=documentSnapshot.getString("images");

                //set data
                profileName.setText(user_name);
                profileId.setText(user_id);
                profileEmail.setText(user_email);
                profilePassword.setText(user_password);
                profileType.setText(user_type);
                RequestOptions requestOptions=new RequestOptions();
                requestOptions.placeholder(R.drawable.dummy_profile_image);
                Glide.with(getContext()).setDefaultRequestOptions(requestOptions).load(user_images).into(profileImage);
            }
        });
    }

}
