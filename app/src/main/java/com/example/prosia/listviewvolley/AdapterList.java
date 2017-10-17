package com.example.prosia.listviewvolley;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PROSIA on 14/10/2017.
 */

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder>{

    Context context;
    ArrayList<HashMap<String, String>> list_data;
    public AdapterList(MainActivity mainActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = mainActivity;
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String judul = list_data.get(position).get("nama");
        Glide.with(context)
                .load("http://fahevizha72.000webhostapp.com/img/"+list_data.get(position).get("gambar"))
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imghape);
        holder.txthape.setText(list_data.get(position).get("nama"));

        // Set onclicklistener pada view tvTitle (TextView)
        holder.txthape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("judulResep",judul);
                Intent intent = new Intent(context,DetailResep.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
             //   context.finish();
//                Toast.makeText(v.getContext().getApplicationContext(), judul, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txthape;
        ImageView imghape;

        public ViewHolder(View itemView) {
            super(itemView);

            txthape = (TextView) itemView.findViewById(R.id.txthape);
            imghape = (ImageView) itemView.findViewById(R.id.imghp);
        }
    }
}
