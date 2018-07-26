package com.example.diu.diulabsolution.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diu.diulabsolution.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserNotificationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserNotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserNotificationFragment extends Fragment {
   private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_user_notification, container, false);

        return rootView;
    }


}
