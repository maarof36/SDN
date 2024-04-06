package com.example.sdn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sdn.data.Expense;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder>{
    Context context;
    ArrayList<Expense> xList;
    private OnItemClickListener itemClickListener;

    public ExpenseAdapter(Context context, ArrayList<Expense> xList) {
        this.context = context;
        this.xList = xList;
    }

    @NonNull
    @Override
    public  MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        Expense expense= xList.get(position);


        holder.Price.setText(expense.getPrice()+" ₪");
        holder.Type.setText(expense.getType());
        holder.Time.setText(expense.getTime());

        holder.Price.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount(){
        return xList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Price,Type,Time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Price=itemView.findViewById(R.id.P);
            Type=itemView.findViewById(R.id.T);
            Time=itemView.findViewById(R.id.T2);

        }
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
