package com.example.diu.diulabsolution.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diu.diulabsolution.Model.Notification;
import com.example.diu.diulabsolution.R;

import java.util.List;

public class AuthorityNotificationAdapter extends RecyclerView.Adapter<AuthorityNotificationAdapter.ViewHolder> {

    private List<Notification> notificationsList;
    private Context context;

    public AuthorityNotificationAdapter(Context context, List<Notification> notificationList){
        this.notificationsList=notificationList;
        this.context=context;
    }

    @NonNull
    @Override
    public AuthorityNotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.authority_notification_list,parent,false);
        return new AuthorityNotificationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.notificationTitleView.setText(notificationsList.get(position).getNotificationTitle());
        holder.complainTypeView.setText(notificationsList.get(position).getComplainType());
        holder.senderIdView.setText(notificationsList.get(position).getNotificationSenderId());
    }

    @Override
    public int getItemCount() {
        return notificationsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View mView;
        private TextView notificationTitleView,complainTypeView,senderIdView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            notificationTitleView=(TextView)mView.findViewById(R.id.notificationTitle);
            complainTypeView=(TextView)mView.findViewById(R.id.complainType);
            senderIdView=(TextView)mView.findViewById(R.id.senderId);
        }
    }


}
