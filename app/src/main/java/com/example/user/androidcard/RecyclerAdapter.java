package com.example.user.androidcard;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by USER on 16-Jan-18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    List<Menu>menu;
    private LayoutInflater inflater;
    private Context context;

    public RecyclerAdapter(Context context, List<Menu> itemmenu){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.menu = itemmenu;
    }
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position) {
        Menu current=menu.get(position);
        holder.setdata(current,position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,kategori,price;
        ImageView thumbnail;
        int position;
        Menu current;
        View itemView;
        public MyViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            name=(TextView)itemView.findViewById(R.id.tvName);
            kategori=(TextView)itemView.findViewById(R.id.tvdescription);
            price=(TextView)itemView.findViewById(R.id.tvprice);
        }
        public void setdata(Menu current, int position) {
            String imageurl = "http://560057.youcanlearnit.net/service/images/"+current.getImage();
            Glide.with(context).load(imageurl).into(thumbnail);
            this.name.setText(current.getItemName());
            this.kategori.setText(current.getCategory());
            this.price.setText(current.getPrice());
            this.position = position;
            this.current=current;

        }

        public void setListeners() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentCommunicator fragmentCommunicator = (FragmentCommunicator)context;
                    fragmentCommunicator.displayDetail(current);
                }
            });
        }
    }
}
