package com.example.collegeapp.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.collegeapp.R;
import com.example.collegeapp.databinding.FragmentGalleryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class galleryFragment extends Fragment {
    private FragmentGalleryBinding binding;
    private galleryAdapter galleryAdapter;
    DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentGalleryBinding.inflate(getLayoutInflater());
        databaseReference= FirebaseDatabase.getInstance().getReference().child("gallery");
        getConvocation();
        getIndependence();
        getOtherImages();


       return binding.getRoot();
    }

    private void getOtherImages() {
        databaseReference.child("Others Events").addValueEventListener(new ValueEventListener() {
            ArrayList<String> arrayList=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String data= (String) snapshot.getValue();
                    arrayList.add(data);
                }
                galleryAdapter=new galleryAdapter(getContext(),arrayList);
                binding.othersRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                binding.othersRecycler.setAdapter(galleryAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIndependence() {
        databaseReference.child("Independence Day").addValueEventListener(new ValueEventListener() {
            ArrayList<String> imageList=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String data= (String) snapshot.getValue();
                    imageList.add(data);
                }
                galleryAdapter=new galleryAdapter(getContext(),imageList);
                binding.independenceRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                binding.independenceRecycler.setAdapter(galleryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getConvocation() {
    databaseReference.child("Convocation").addValueEventListener(new ValueEventListener() {
       ArrayList<String> imageList=new ArrayList<>();
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                String data= (String) snapshot.getValue();
                imageList.add(data);
            }
            galleryAdapter=new galleryAdapter(getContext(),imageList);
            binding.convocationRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
            binding.convocationRecycler.setAdapter(galleryAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    }
}