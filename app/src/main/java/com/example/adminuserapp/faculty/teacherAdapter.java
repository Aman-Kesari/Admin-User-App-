package com.example.adminuserapp.faculty;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminuserapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class teacherAdapter extends RecyclerView.Adapter<teacherAdapter.ViewHolder>{
     private ArrayList<TeacherData> arrayList;

     Context context;
     private String category;
     public teacherAdapter(Context c,ArrayList<TeacherData> a,String ca){
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
        holder.button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
              Intent intent=new Intent(context,UpdateActivity.class);
                 intent.putExtra("image",data.getImage());
                 intent.putExtra("name",data.getName());
                 intent.putExtra("email",data.getEmail());
                 intent.putExtra("post",data.getPost());
                 intent.putExtra("uniqueKey",data.getKey());
                 intent.putExtra("category",category);
                 context.startActivity(intent);
             }
         });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
         private TextView name,email,post;
         private Button button;
         private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.teacherName);
            post=itemView.findViewById(R.id.teacherPost);
            email=itemView.findViewById(R.id.teacherEmail);
            button=itemView.findViewById(R.id.teacherUpdate);
            image=itemView.findViewById(R.id.teacherImage);

        }
    }
}
