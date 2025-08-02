package com.example.collegeapp.zoom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.collegeapp.R;
import com.example.collegeapp.databinding.ActivityFullImageViewBinding;

public class fullImageView extends AppCompatActivity {
private ActivityFullImageViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFullImageViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String noticeImageUrl=getIntent().getStringExtra("noticeImageUrl");
        Glide.with(fullImageView.this).load(noticeImageUrl).into(binding.photoView);

    }
}