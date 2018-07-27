package com.example.diu.diulabsolution.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diu.diulabsolution.Adapter.AuthorityNotificationAdapter;
import com.example.diu.diulabsolution.Adapter.UserAdapter;
import com.example.diu.diulabsolution.Model.Notification;
import com.example.diu.diulabsolution.Model.Users;
import com.example.diu.diulabsolution.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


public class AuthorityNotificationFragment extends Fragment {
    private View rootView;
    private RecyclerView recyclerViewList;
    private FirebaseFirestore mFirestore;
    private List<Notification> notificationList;
    private AuthorityNotificationAdapter authorityNotificationAdapter;

    @Override
    public void onStart() {
        super.onStart();
        notificationList.clear();
        mFirestore.collection("notifications").addSnapshotListener(getActivity(),new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for(DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){
                    if(doc.getType()==DocumentChange.Type.ADDED){
                        Notification notifications=doc.getDocument().toObject(Notification.class);
                        notificationList.add(notifications);

                        authorityNotificationAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_authority_notification, container, false);
        mFirestore=FirebaseFirestore.getInstance();
        //addUsersInArrayList();
        recyclerViewList=(RecyclerView)rootView.findViewById(R.id.recyclerListViewAuthorityNotification);
        notificationList=new ArrayList<>();
        authorityNotificationAdapter=new AuthorityNotificationAdapter(container.getContext(),notificationList);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerViewList.setAdapter(authorityNotificationAdapter);


        return rootView;
    }


}
