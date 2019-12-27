package com.winitsolutions.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class userAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<User> userList;
    Context context;
    onItemClick itemClick;


    public userAdapter(List<User> userList, Context context, onItemClick itemClick) {
        this.userList = userList;
        this.context = context;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user, parent, false);
        return new userHolder(view, itemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        User user = userList.get(position);

        if (holder instanceof userHolder) {
            ((userHolder) holder).text_firstname.setText(user.getFirstname());
            ((userHolder) holder).text_lastname.setText(user.getLastname());
            ((userHolder) holder).text_college.setText(user.getCollege());
            ((userHolder) holder).text_phonenumber.setText(user.getPhone_numbe());


        }




    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class userHolder extends RecyclerView.ViewHolder {
        TextView text_firstname, text_lastname, text_college, text_phonenumber;
        ImageView img_delete;
        onItemClick itemClick;

        public userHolder(@NonNull View itemView, final onItemClick itemClick) {
            super(itemView);

            text_firstname = itemView.findViewById(R.id.text_firstname);
            text_lastname = itemView.findViewById(R.id.text_lasttname);
            text_college = itemView.findViewById(R.id.text_college);
            text_phonenumber = itemView.findViewById(R.id.text_phonenumber);
            img_delete = itemView.findViewById(R.id.img_delete);
            this.itemClick = itemClick;


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = userList.get(getAdapterPosition());
                    itemClick.itemUpdateListenr(user, getAdapterPosition());
                }
            });


            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    //         User user=userList.get(getAdapterPosition());
  //                  itemClick.itemUpdateListenr(getAdapterPosition());
                    User user = userList.get(getAdapterPosition());
                    itemClick.itemDeleteClickListener(user, getAdapterPosition());

                }
            });

        }
    }
}
