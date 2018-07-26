package com.example.diu.diulabsolution.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diu.diulabsolution.Model.Room;
import com.example.diu.diulabsolution.R;

import java.util.ArrayList;

public class RoomAdapter extends ArrayAdapter<Room> {
    public RoomAdapter(Context context,int textViewResourceId, ArrayList<Room> rooms){
        super(context, textViewResourceId, rooms);
    }
    @Override
    public int getCount() {
        return super.getCount();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View gridItemView = convertView;
        if(gridItemView == null) {
           // gridItemView = LayoutInflater.from(getContext()).inflate(
                   // R.layout.grid_room_item, parent, false);
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridItemView = inflater.inflate(R.layout.grid_room_item, null);
        }
        Room currentRoom= (Room) getItem(position);
        TextView roomNameTextView=(TextView)gridItemView.findViewById(R.id.roomName_text_view);
        roomNameTextView.setText(currentRoom.getRoomName());
        TextView roomIdTextView=(TextView)gridItemView.findViewById(R.id.roomId_text_view);
        roomIdTextView.setText(currentRoom.getRoomId());
        ImageView imageView = (ImageView) gridItemView.findViewById(R.id.imageId);
        if(currentRoom.hasImag()) {
            // Get the image from the current WordAdapter object and
            imageView.setImageResource(currentRoom.getRoomImageId());
        }
        else {
            //make sure the view is visible
            imageView.setVisibility(View.GONE);
        }

        return gridItemView;

    }
}
