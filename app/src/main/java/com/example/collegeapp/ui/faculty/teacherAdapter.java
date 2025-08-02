package com.example.collegeapp.ui.faculty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.collegeapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class teacherAdapter extends RecyclerView.Adapter<teacherAdapter.ViewHolder>{
     private ArrayList<TeacherData> arrayList;
     Context context;
     private String category;
     public teacherAdapter(Context c, ArrayList<TeacherData> a, String ca){
         arrayList=a;
         context=c;
         category=ca;
     }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(context).inflate(R.layout.faculty_items_layout,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         TeacherData data=arrayList.get(position);
         holder.name.setText(data.getName());
         holder.post.setText(data.getPost());
         holder.email.setText(data.getEmail());
        holder.image.setImageResource(R.drawable.avatar);
        try {
            Picasso.get().load(data.getImage()).into(holder.image);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(holder.image.getDrawable()==null){
            holder.image.setImageResource(R.drawable.avatar);
        }
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
         private TextView name,email,post;
         private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.teacherName);
            post=itemView.findViewById(R.id.teacherPost);
            email=itemView.findViewById(R.id.teacherEmail);
            image=itemView.findViewById(R.id.teacherImage);
        }
    }
}
