package com.example.collegeapp.ui.notice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.example.collegeapp.databinding.FragmentNoticeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class noticeFragment extends Fragment {
    private FragmentNoticeBinding binding;
    private DatabaseReference databaseReference;
    private ArrayList<NoticeData> arrayList;
    private noticeAdapter arrayAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNoticeBinding.inflate(getLayoutInflater());
        //Newly added
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notice");
        binding.deleteRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.deleteRecyclerView.setHasFixedSize(true);
        getNotice();
        return binding.getRoot();
    }
        private void getNotice() {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    arrayList=new ArrayList<>();
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        NoticeData data=snapshot.getValue(NoticeData.class);
                        arrayList.add(0,data);
                    }
                    arrayAdapter=new noticeAdapter(getContext(),arrayList);
                    arrayAdapter.notifyDataSetChanged();
                    binding.progressBar.setVisibility(View.GONE);
                    binding.deleteRecyclerView.setAdapter(arrayAdapter);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
    }

}