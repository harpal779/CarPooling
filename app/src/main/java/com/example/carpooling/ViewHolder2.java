package com.example.carpooling;

import android.content.Context;
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

    public void setData(Context context,String CarName,String Phone,String Model,String From,String To,String Date,String Time,String AvailableSeats,String Price){
        TextView textView = itemView.findViewById(R.id.textview_row);

        textView.setText("CarName: " + CarName + "\n"  + "Phone: "  + Phone +"\n"+ "Model: " + Model + "\n" + "From: " + From + "TO: " + To + "\n"  + "Date: "  + Date +"\n"+ "Time : " + Time + "\n" + "AvailableSeats : " + AvailableSeats + "\n" + "Price: " + Price);
    }

    private ViewHolder.Clicklistener mClicklistener;
    public interface Clicklistener{
        void onItemlongClick(View view , int position);
    }

    public void setOnClickListener(ViewHolder.Clicklistener clickListener){
        mClicklistener = clickListener;
    }

}