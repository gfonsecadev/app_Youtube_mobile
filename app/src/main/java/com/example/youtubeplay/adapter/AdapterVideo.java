package com.example.youtubeplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeplay.R;
import com.example.youtubeplay.activity.YoutubeActivety;
import com.example.youtubeplay.model.Items;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.HolderVideo> {
    List<Items> videosList;
    Context context;

    public AdapterVideo(List<Items> videosList, Context context) {
        this.videosList = videosList;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderVideo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycler_video, parent, false);

        return new AdapterVideo.HolderVideo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderVideo holder, int position) {
        Items videos = videosList.get(position);
        holder.titulo.setText(videos.snippet.title);
        Picasso.get().load(videos.snippet.thumbnails.high.url).into(holder.capaVideo);
        holder.capaVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, YoutubeActivety.class);
                intent.putExtra("idVideo", videos.id.videoId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }

    public class HolderVideo extends RecyclerView.ViewHolder {
        TextView titulo;
        ImageView capaVideo;

        public HolderVideo(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textTitulo);
            capaVideo = itemView.findViewById(R.id.imageCapa);


        }
    }


}
