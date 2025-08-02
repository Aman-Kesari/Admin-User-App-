package com.example.collegeapp.ui.gallery;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.collegeapp.R;
import com.example.collegeapp.zoom.fullImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
public class galleryAdapter extends RecyclerView.Adapter<galleryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> images;
    public galleryAdapter(Context c, ArrayList<String> images) {
        context =c;
        this.images = images;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.gallery_image,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(images.get(position)).into(holder.imageGallery);
        holder.imageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, fullImageView.class);
                intent.putExtra("noticeImageUrl",images.get(position));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return images.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
       private ImageView imageGallery;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageGallery=itemView.findViewById(R.id.imageGallery);
        }
    }
}
