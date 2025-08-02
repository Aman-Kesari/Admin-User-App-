package com.example.adminuserapp.notice;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminuserapp.R;
import com.example.adminuserapp.faculty.teacherAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class noticeAdapter extends RecyclerView.Adapter<noticeAdapter.ViewHolder>{
    private Context context;
    private ArrayList<NoticeData> arrayList;

    public noticeAdapter(Context context, ArrayList<NoticeData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_feed_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NoticeData currentItem=arrayList.get(position);
        holder.deleteNoticeTitle.setText(currentItem.getTitile());
        try {
            if(currentItem.getImg()!=null) {
               Glide.with(context).load(currentItem.getImg()).into(holder.deleteNoticeImage);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Now for delete button
        holder.deleteNoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Do you want to delete the news ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Notice");
                        databaseReference.child(currentItem.getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        });
                        notifyItemRemoved(position);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                         dialogInterface.cancel();
                    }
                });
                AlertDialog dialog=null;
                try {
                    dialog=builder.create();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       private TextView deleteNoticeTitle;
        private Button deleteNoticeBtn;
        private ImageView deleteNoticeImage;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           deleteNoticeImage=itemView.findViewById(R.id.deleteNoticeImage);
           deleteNoticeTitle=itemView.findViewById(R.id. deleteNoticeTitle);
           deleteNoticeBtn=itemView.findViewById(R.id.deleteNoticeBtn);

       }
   }
}
