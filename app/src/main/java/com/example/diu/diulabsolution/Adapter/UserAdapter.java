package com.example.diu.diulabsolution.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.diu.diulabsolution.Model.Users;
import com.example.diu.diulabsolution.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<Users> usersList;
    private Context context;

    public UserAdapter(Context context,List<Users> usersList){
        this.usersList=usersList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userNameView.setText(usersList.get(position).getName());
        holder.userIdView.setText(usersList.get(position).getUser_id());
        CircleImageView user_image_view=holder.imageView;
        Glide.with(context).load(usersList.get(position).getImages()).into(user_image_view);

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View mView;
        private CircleImageView imageView;
        private TextView userNameView,userIdView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            imageView=(CircleImageView)mView.findViewById(R.id.profilePic);
            userNameView=(TextView)mView.findViewById(R.id.userName);
            userIdView=(TextView)mView.findViewById(R.id.userId);
        }
    }


}
