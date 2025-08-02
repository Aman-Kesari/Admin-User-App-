package com.example.collegeapp.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.example.collegeapp.databinding.ActivityEbookBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class ebookActivity extends AppCompatActivity {
    ActivityEbookBinding binding;
    private ArrayList<ebookData> arrayList;
    private ebookAdapater ebookAdapter;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEbookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Ebook");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("pdf");
        getData();
    }
    private void getData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList=new ArrayList<>();
               for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                   ebookData data= snapshot.getValue(ebookData.class);
                   arrayList.add(data);
               }
               ebookAdapter=new ebookAdapater(ebookActivity.this,arrayList);
               binding.ebookRecycler.setLayoutManager(new LinearLayoutManager(ebookActivity.this));
               binding.ebookRecycler.setAdapter(ebookAdapter);
               binding.shimmerViewContainer.stopShimmer();
               binding.shimmerLinearLayout.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ebookActivity.this, databaseError.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        binding.shimmerViewContainer.stopShimmer();
        super.onPause();
    }

    @Override
    protected void onResume() {
        binding.shimmerViewContainer.startShimmer();
        super.onResume();
    }
}