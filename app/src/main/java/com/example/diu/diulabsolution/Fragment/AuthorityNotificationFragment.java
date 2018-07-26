package com.example.diu.diulabsolution.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diu.diulabsolution.R;


public class AuthorityNotificationFragment extends Fragment {
    private View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_authority_notification, container, false);


        return rootView;
    }


}
