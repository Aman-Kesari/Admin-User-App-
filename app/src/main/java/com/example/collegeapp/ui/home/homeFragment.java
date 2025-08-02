
package com.example.collegeapp.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.collegeapp.R;

import java.util.ArrayList;

public class homeFragment extends Fragment {
    private ImageSlider imageSlider;
    private ArrayList<SlideModel> imageList;
    private ImageView mapImage;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        mapImage=view.findViewById(R.id.mapImage);
        imageSlider=view.findViewById(R.id.imageSlider);
        imageList=new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.col_img,ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.img,ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.img_2,ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.img_3,ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.img_4,ScaleTypes.FIT));
        imageSlider.setImageList(imageList,ScaleTypes.FIT);
        imageSlider.startSliding(500);
        mapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("geo:0,0?q=National Institute of Technology Patna");
                Intent intent =new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });



        return view;
    }
}