package com.example.collegeapp.theme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;

import com.example.collegeapp.R;
import com.example.collegeapp.databinding.ActivityThemeBinding;

public class themeActivity extends AppCompatActivity {
//   private ActivityThemeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding=ActivityThemeBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_theme);

        findViewById(R.id.darkBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
               //AppCompatDelegate.getM
            }
        });
        findViewById(R.id.lightBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                //AppCompatDelegate.getM
            }
        });


    }
}