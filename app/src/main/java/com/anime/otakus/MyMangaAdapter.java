package com.anime.otakus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyMangaAdapter extends RecyclerView.Adapter<MyMangaAdapter.ViewHolder>{
    ArrayList<MyMangaData> myMangaDataList;
    Context context;

    public MyMangaAdapter(ArrayList<MyMangaData> myMangaDataList, Activity mainActivity){
        this.myMangaDataList = myMangaDataList;
        this.context=mainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.manga_item_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MyMangaData myMangaData = this.myMangaDataList.get(position);
        holder.textViewName.setText(myMangaData.getName());
        holder.textViewDate.setText(myMangaData.getRating());
        Picasso.get().load(myMangaData.getImage_url()).into(holder.movieImage);
        //holder.movieImage.setImageResource(myMangaDataList.getImage_url());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,MangaView.class);
                i.putExtra("book_name",myMangaDataList.get(position).getName());
                i.putExtra("img_url",myMangaDataList.get(position).getImage_url());
                i.putExtra("book_url",myMangaDataList.get(position).getBook_url());
                i.putExtra("description",myMangaDataList.get(position).getDescription());
                context.startActivity(i);
                //Toast.makeText(context, "Work in Progress",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void update(MyMangaData b1){
        myMangaDataList.add(new MyMangaData(b1.getName(),b1.getDescription(),b1.getImage_url(),b1.getBook_url(),b1.getRating()));
        notifyDataSetChanged();
    }

    public void clear(){
        myMangaDataList.clear();
    }

    @Override
    public int getItemCount() {
        return myMangaDataList.toArray().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;
        TextView textViewName;
        TextView textViewDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage=itemView.findViewById(R.id.imageview);
            textViewName=itemView.findViewById(R.id.textName);
            textViewDate=itemView.findViewById(R.id.textdate);
        }
    }
}
