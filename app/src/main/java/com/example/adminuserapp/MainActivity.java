package com.example.adminuserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.adminuserapp.databinding.ActivityMainBinding;
import com.example.adminuserapp.faculty.UploadFaculty;
import com.example.adminuserapp.notice.DeleteNoticeActivity;
import com.example.adminuserapp.notice.UploadNotice;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.addNotice.setOnClickListener(this);
        binding.addImage.setOnClickListener(this);
        binding.addEbook.setOnClickListener(this);
        binding.addFaculty.setOnClickListener(this);
        binding.deleteNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){
            case R.id.addNotice:{
                 intent=new Intent(MainActivity.this, UploadNotice.class);
                startActivity(intent);
                break;
            }
            case R.id.addImage:{
                intent=new Intent(MainActivity.this,UploadImage.class);
                startActivity(intent);
                break;
            }
            case R.id.addEbook:{
                intent=new Intent(MainActivity.this,UploadPdf.class);
                startActivity(intent);
                break;
            }
            case R.id.addFaculty:{
                intent=new Intent(MainActivity.this,UploadFaculty.class);
                startActivity(intent);
                break;
            }
            case R.id.deleteNotice:{
                intent=new Intent(MainActivity.this, DeleteNoticeActivity.class);
                startActivity(intent);
                break;
            }

        }

    }
}