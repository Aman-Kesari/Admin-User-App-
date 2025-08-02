package com.example.collegeapp.ui.notice;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.bumptech.glide.Glide;
import com.example.collegeapp.R;
import com.example.collegeapp.zoom.fullImageView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class noticeAdapter extends RecyclerView.Adapter<noticeAdapter.holder>{
    private Context context;
    private ArrayList<NoticeData> arrayList;
   private DatabaseReference databaseReference;
   private StorageReference storageReference;
    public noticeAdapter(Context context, ArrayList<NoticeData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_feed_layout,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        NoticeData currentItem=arrayList.get(position);
        holder.deleteNoticeTitle.setText(currentItem.getTitile());
        try {
            if(currentItem.getImg()!=null) {
                Glide.with(context).load(currentItem.getImg()).into(holder.deleteNoticeImage);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        holder.deleteNoticeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent =new Intent(context, fullImageView.class);
            intent.putExtra("noticeImageUrl",currentItem.getImg());
            context.startActivity(intent);
            }
        });
        holder.postDate.setText(currentItem.getDate());
        holder.postTime.setText(currentItem.getTime());
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }


   public class holder extends RecyclerView.ViewHolder {
       private TextView deleteNoticeTitle;

       private ImageView deleteNoticeImage;
       private TextView postDate,postTime;
       public holder(@NonNull View itemView) {
           super(itemView);
           deleteNoticeImage=itemView.findViewById(R.id.deleteNoticeImage);
           deleteNoticeTitle=itemView.findViewById(R.id. deleteNoticeTitle);
           postDate=itemView.findViewById(R.id.postDate);
           postTime=itemView.findViewById(R.id.postTime);
       }
   }
}
