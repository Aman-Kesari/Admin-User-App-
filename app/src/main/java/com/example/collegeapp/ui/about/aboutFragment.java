package com.example.collegeapp.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.collegeapp.R;
import com.example.collegeapp.databinding.FragmentAboutBinding;

import java.util.ArrayList;

public class aboutFragment extends Fragment {
    private FragmentAboutBinding binding;
    private ArrayList<branchModel> arrayList;
    private branchAdapter branchAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentAboutBinding.inflate(getLayoutInflater());
        arrayList=new ArrayList<>();
       arrayList.add(new branchModel(R.drawable.computer_icon,"Computer Science","Accha Hai"));
        arrayList.add(new branchModel(R.drawable.mech_icon,"Mechanical","Accha Hai"));
        branchAdapter=new branchAdapter(arrayList,getContext());
        //Use picasso or Glide for net import
        binding.aboutCollageImage.setImageResource(R.drawable.col_img);
        binding.mapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("geo:0,0?q=National Institute of Technology Patna");
                Intent intent =new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });

        binding.viewPager.setAdapter(branchAdapter);
        return binding.getRoot();
    }
}