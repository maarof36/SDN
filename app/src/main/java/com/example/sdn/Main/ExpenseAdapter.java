package com.example.sdn.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sdn.R;
import com.example.sdn.data.Expense2;
import com.example.sdn.data.FirebaseServices;
import com.example.sdn.data.User1;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder>{
    Context context;
    ArrayList<Expense2> xList;
    private OnItemClickListener itemClickListener;
    private FirebaseServices fbs;
    private LinearLayout color;

    public ExpenseAdapter(Context context, ArrayList<Expense2> xList) {
        this.context = context;
        this.xList = xList;
        fbs = FirebaseServices.getInstance();
    }

    @NonNull
    @Override
    public  MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        Expense2 expense= xList.get(position);
        User1 u = fbs.getCurrentUser();
        holder.Price.setText(expense.getPrice()+"₪");
        holder.Type.setText(expense.getType());
        holder.Time.setText(expense.getTime());

        if(expense.getType().equals("House")) holder.color.setBackgroundResource(R.color.blue);
        if(expense.getType().equals("Car")) holder.color.setBackgroundResource(R.color.red);
        if(expense.getType().equals("Communiations")) holder.color.setBackgroundResource(R.color.green);
        if(expense.getType().equals("Clothes")) holder.color.setBackgroundResource(R.color.cyan);
        if(expense.getType().equals("Fixing")) holder.color.setBackgroundResource(R.color.white);
        if(expense.getType().equals("Phone")) holder.color.setBackgroundResource(R.color.yellow);





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
        LinearLayout color;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Price=itemView.findViewById(R.id.P);
            Type=itemView.findViewById(R.id.T);
            Time=itemView.findViewById(R.id.T2);
            color=itemView.findViewById(R.id.Itemid);

        }
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
