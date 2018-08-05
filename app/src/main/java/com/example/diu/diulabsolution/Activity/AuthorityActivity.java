package com.example.diu.diulabsolution.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.diu.diulabsolution.Adapter.AuthorityPagerAdapter;
import com.example.diu.diulabsolution.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthorityActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ViewPager viewPager;
    private String complainDocId,notifyDocId;
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser==null){
            sendToLogin();
        }
    }

    private void sendToLogin(){
        Intent intent=new Intent(this,SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority);
        mAuth=FirebaseAuth.getInstance();
        setAuthorityViewPager();
        if(getIntent().getExtras()!=null){
             //complainDocId=getIntent().getExtras().getString("complain_doc_id");
             //notifyDocId=getIntent().getExtras().getString("notify_doc_id");
            gotoFragment(complainDocId);
        }

    }

    private void gotoFragment(String complainDocId) {
        if(viewPager != null){
            this.viewPager.setCurrentItem(2,true);

        }
    }

    public void setAuthorityViewPager(){
        // Find the view pager that will allow the user to swipe between fragments
         viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        AuthorityPagerAdapter adapter = new AuthorityPagerAdapter(this,getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Find the tab layout that shows the tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
