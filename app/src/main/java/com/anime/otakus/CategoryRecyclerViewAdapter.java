package com.anime.otakus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//import com.bumptech.glide.Glide;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "CategoryRecyclerViewAdapter";

    private ArrayList<String> mNames= new ArrayList<>();
    private ArrayList<Integer > thumbnail = new ArrayList<>();

    private List<MyMangaData> mbook;
    private Context mContext ;

    public CategoryRecyclerViewAdapter(Context mContext, ArrayList<String> mNames, ArrayList<Integer> thumbnail) {
        this.mNames = mNames;
        this.thumbnail = thumbnail;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(mNames.get(position));
        holder.name.setBackgroundResource(thumbnail.get(position));



        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (mNames.get(position)){

                    case "ACTION":{
                        Intent intent5 = new Intent(mContext,Action.class);
                        mContext.startActivity(intent5);
                        break;
                    }
                    case "ADVENTURE":{
                        Intent intent4 = new Intent(mContext,Adventure.class);
                        mContext.startActivity(intent4);
                        break;
                    }
                    case "THRILLER":{
                        Intent intent2 = new Intent(mContext,Thriller.class);
                        mContext.startActivity(intent2);
                        break;
                    }
                   case "HORROR":{
                        Intent intent3 = new Intent(mContext,Horror.class);
                        mContext.startActivity(intent3);
                        break;
                    }
                    case "DRAMA":{
                        Intent intent4 = new Intent(mContext,Drama.class);
                        mContext.startActivity(intent4);
                        break;
                    }
                    case "COMEDY":{
                        Intent intent4 = new Intent(mContext,Comedy.class);
                        mContext.startActivity(intent4);
                        break;
                    }

                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return thumbnail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       // ImageView image ;
        TextView name;
       // Layout

        public ViewHolder(View itemView) {
            super(itemView);
          //  image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.cat_name);
        }
    }


}
