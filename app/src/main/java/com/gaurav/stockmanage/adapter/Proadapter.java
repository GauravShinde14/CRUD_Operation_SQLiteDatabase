package com.gaurav.stockmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gaurav.stockmanage.R;
import com.gaurav.stockmanage.model.Productmodel;

import java.util.ArrayList;

public class Proadapter extends RecyclerView.Adapter<Proadapter.ViewHolder> {

private ArrayList<Productmodel> proarray;
private Context context;

    public Proadapter(ArrayList<Productmodel> proarray, Context context) {
        this.proarray = proarray;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_rv,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Productmodel productmodel =proarray.get(position);
        holder.productname.setText(String.valueOf(productmodel.getName()));
        holder.qty.setText(String.valueOf(productmodel.getQuantity()));
    }



    @Override
    public int getItemCount() {
        return proarray.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
         TextView productname;
        private TextView qty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productname = itemView.findViewById(R.id.idTVCourseName);
            qty = itemView.findViewById(R.id.idTVCourseTracks);
        }
    }
}
