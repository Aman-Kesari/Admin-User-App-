package com.example.collegeapp.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.collegeapp.R;
import com.example.collegeapp.databinding.ActivityPdfViewerBinding;

public class pdfViewer extends AppCompatActivity {
   ActivityPdfViewerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPdfViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}