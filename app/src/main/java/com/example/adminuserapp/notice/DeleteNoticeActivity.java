package com.example.adminuserapp.notice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.adminuserapp.R;
import com.example.adminuserapp.databinding.ActivityDeleteNoticeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DeleteNoticeActivity extends AppCompatActivity {
    ActivityDeleteNoticeBinding binding;
    private DatabaseReference databaseReference;
    private ArrayList<NoticeData> arrayList;
    private noticeAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDeleteNoticeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Notice");
        binding.deleteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.deleteRecyclerView.setHasFixedSize(true);
        getNotice();

    }

    private void getNotice() {
      databaseReference.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              arrayList=new ArrayList<>();
              for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                  NoticeData data=snapshot.getValue(NoticeData.class);
                  arrayList.add(data);
              }
              arrayAdapter=new noticeAdapter(DeleteNoticeActivity.this,arrayList);
              arrayAdapter.notifyDataSetChanged();
              binding.progressBar.setVisibility(View.GONE);
              binding.deleteRecyclerView.setAdapter(arrayAdapter);

          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {
              binding.progressBar.setVisibility(View.GONE);
              Toast.makeText(DeleteNoticeActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

          }
      });
    }
}