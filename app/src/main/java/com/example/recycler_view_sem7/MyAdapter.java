package com.example.recycler_view_sem7;


import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by USER on 01-Aug-19.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    Context ct;
    ArrayList<Person> al;
    MyDataBase dataBase;


    MyAdapter(Context ct, ArrayList<Person> al, MyDataBase dataBase)
    {
        this.ct = ct;
        this.al= al;
        this.dataBase = dataBase;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li =  LayoutInflater.from(ct);

        View v1 = li.inflate(R.layout.mylayout,parent,false);

        return new MyViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Person p = al.get(position);

        holder.tvName.setText(p.getName());
        holder.tvReg.setText(""+p.getReg());
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName, tvReg;
        ImageView ivremove;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvname);
            tvReg = itemView.findViewById(R.id.tvreg);

            ivremove = itemView.findViewById(R.id.ivremove);

            linearLayout = itemView.findViewById(R.id.ll);

            ivremove.setVisibility(View.INVISIBLE);

            linearLayout.setOnLongClickListener(new OnLongClickListener() {

                @Override
                public boolean onLongClick(View view) {

                    if(ivremove.getVisibility() == View.VISIBLE)
                        ivremove.setVisibility(View.INVISIBLE);
                    else
                        ivremove.setVisibility(View.VISIBLE);

                    ivremove.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            delete(getAdapterPosition());
                            ivremove.setVisibility(View.INVISIBLE);
                        }
                    });
                    return true;
                }
            });
        }

        public void delete(int position)
        {
            Toast.makeText(ct,"Delete position is: "+position, Toast.LENGTH_SHORT).show();

            dataBase.removeValues(position);
            notifyDataSetChanged();
        }
    }

}
