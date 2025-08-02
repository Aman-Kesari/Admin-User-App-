package com.example.collegeapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.collegeapp.databinding.ActivityMainBinding;
import com.example.collegeapp.ebook.ebookActivity;
import com.example.collegeapp.theme.themeActivity;
import com.example.collegeapp.ui.about.aboutFragment;
import com.example.collegeapp.ui.faculty.facultyFragment;
import com.example.collegeapp.ui.gallery.galleryFragment;
import com.example.collegeapp.ui.home.homeFragment;
import com.example.collegeapp.ui.notice.noticeFragment;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
  ActivityMainBinding binding;
  private ActionBarDrawerToggle toogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toogle=new ActionBarDrawerToggle(this,binding.drawableLayout,R.string.star,R.string.close);
        binding.drawableLayout.addDrawerListener(toogle);
        toogle.syncState();
        //Horizontal NavigationBar
        binding.navigationMain.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.homeMenu:{
                        FragmentManager fm=getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.cont,new homeFragment());
                        ft.commit();
                        break;
                    }
                    case R.id.noticeMenu:{
                        FragmentManager fm=getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.cont,new noticeFragment());
                        ft.commit();
                        break;
                    }
                    case R.id.facultyMenu: {
                        FragmentManager fm=getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.cont,new facultyFragment());
                        ft.commit();
                        break;
                    }
                    case R.id.galleryMenu:{
                        FragmentManager fm=getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.cont,new galleryFragment());
                        ft.commit();
                        break;
                    }
                    case R.id.aboutMenu:{
                        FragmentManager fm=getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.cont,new aboutFragment());
                        ft.commit();
                        break;
                    }
                }
                return true;
            }
        });
        binding.navigationMain.setSelectedItemId(R.id.homeMenu);
       //Drawable NavigationBar
       Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
       binding.drawableNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()) {
                   case R.id.videoMenu: {
                       Toast.makeText(MainActivity.this, "Video Lecture Selected", Toast.LENGTH_SHORT).show();
                       break;
                   }
                   case R.id.ebookMenu: {
                       startActivity(new Intent(MainActivity.this, ebookActivity.class));
                       break;
                   }   case R.id.themeMenu: {
                       startActivity(new Intent(MainActivity.this, themeActivity.class));
                       break;
                   }   case R.id.websiteMenu: {
                       startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nitp.ac.in/")));
                       break;
                   }
                   case R.id.shareMenu: {
                       shareOn();
                       break;
                   }
                   case R.id.rateMenu: {
                       try {
                           startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName())));
                       } catch (Exception e) {
                           Toast.makeText(MainActivity.this, "Unable to open", Toast.LENGTH_SHORT).show();
                       }

                       break;
                   }
                   case R.id.developMenu: {
                       Toast.makeText(MainActivity.this, "Developer Selected", Toast.LENGTH_SHORT).show();
                       break;
                   }
               }
               return false;
           }
       });
    }

    private void shareOn() {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"pass the application url here");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Explore this awesome application");
        startActivity(Intent.createChooser(intent,"Share via"));
    }

    @Override
    public void onBackPressed() {
        if(binding.drawableLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawableLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


}