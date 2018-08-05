package com.example.diu.diulabsolution.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diu.diulabsolution.Adapter.UserAdapter;
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

public class AuthorityAllUsersFragment extends Fragment {

    private RecyclerView recyclerViewList;
    private FirebaseFirestore mFirestore;
    private View rootView;
    private List<Users> usersList;
    private UserAdapter userAdapter;

    public AuthorityAllUsersFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();
        usersList.clear();
        mFirestore.collection("users").addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        Users users = doc.getDocument().toObject(Users.class);
                        usersList.add(users);
                        userAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_all_users, container, false);
        mFirestore = FirebaseFirestore.getInstance();
        //addUsersInArrayList();
        recyclerViewList = (RecyclerView) rootView.findViewById(R.id.recyclerListView);
        usersList = new ArrayList<>();
        userAdapter = new UserAdapter(container.getContext(), usersList);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerViewList.setAdapter(userAdapter);
        return rootView;
    }

   /* private void addUsersInArrayList(){
        recyclerViewList=(RecyclerView)rootView.findViewById(R.id.recyclerListView);
        usersList=new ArrayList<>();
        userAdapter=new UserAdapter(usersList,getContext());
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewList.setAdapter(userAdapter);
    }*/

}
