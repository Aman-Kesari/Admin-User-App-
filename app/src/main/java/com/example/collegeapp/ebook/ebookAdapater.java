package com.example.collegeapp.ebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapp.R;


import java.util.ArrayList;

public class ebookAdapater extends RecyclerView.Adapter<ebookAdapater.ViewHolder>{
    private Context context;
    private ArrayList<ebookData> arrayList;

    public ebookAdapater(Context context, ArrayList<ebookData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ebookAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pdf_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ebookAdapater.ViewHolder holder, int position) {
        ebookData data=arrayList.get(position);
        holder.ebookTitle.setText(data.getPdfTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(context,pdfViewer.class);
               intent.putExtra("url",data.getPdfUrl());
               context.startActivity(intent);
            }
        });
        holder.ebookDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse(data.getPdfUrl()));
               context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ebookTitle;
        private ImageView ebookDownloadBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ebookTitle=itemView.findViewById(R.id.ebookTitle);
            ebookDownloadBtn=itemView.findViewById(R.id.ebookDownloadbtn);
        }
    }
}
