package com.example.carpooling;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder2 extends RecyclerView.ViewHolder {


    public ViewHolder2(@NonNull View itemView) {
        super(itemView);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClicklistener.onItemlongClick(view,getAdapterPosition());
                return false;
            }
        });
    }

    public void setData(Context context, String carname, String phone, String model, String From, String to,
                        String date, String time, String AvailableSeats,String Price){
        TextView textView = itemView.findViewById(R.id.textview_row);

        textView.setText("CarName: " + carname + "\n"  + "Phone: "  + phone +"\n"+ "Model: " + model + "\n" +
                "From: " + From + "TO: " + to + "\n"  + "Date: "  + date +"\n"+ "Time : " + time  + "\n" +
                "AvailableSeats : " + AvailableSeats + "\n" + "Price: " + Price);
    }

    private ViewHolder.Clicklistener mClicklistener;
    public interface Clicklistener{
        void onItemlongClick(View view , int position);




    }

    public void setOnClickListener(ViewHolder.Clicklistener clickListener){
        mClicklistener = clickListener;
    }

}
