package com.example.collegeapp.ui.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.example.collegeapp.databinding.FragmentFacultyBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class facultyFragment extends Fragment {
    private FragmentFacultyBinding binding;
    private DatabaseReference databaseReference,dbRef;
    private ArrayList<TeacherData> csList,eceList,eList,mechList,civilList;
    private teacherAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentFacultyBinding.inflate(getLayoutInflater());


                ////  Database ke andar jake kiske andar kya likhna........
                databaseReference= FirebaseDatabase.getInstance().getReference().child("teacher");
                csDepartment();
                eceDepartment();
                eeDepartment();
                meDepartment();
                civilDepartment();
        return binding.getRoot();
    }
    private void csDepartment() {
        dbRef=databaseReference.child("Computer Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                csList=new ArrayList<>();
                if(!dataSnapshot.exists()){
                    //No data wala show karna chahiye
                    binding.csNoData.setVisibility(View.VISIBLE);
                    binding.csDepartment.setVisibility(View.GONE);
                }else{
                    binding.csNoData.setVisibility(View.GONE);
                    binding.csDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        csList.add(data);
                    }
                    //department terms are basically recyclerView;;;
                    binding.csDepartment.setHasFixedSize(true);
                    binding.csDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new teacherAdapter(getContext(),csList,"Computer Science");
                    binding.csDepartment.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void eceDepartment() {
        dbRef=databaseReference.child("Electronics and Communincation");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eceList=new ArrayList<>();
                if(!dataSnapshot.exists()){
                    binding.eceNoData.setVisibility(View.VISIBLE);
                    binding.eceDepartment.setVisibility(View.GONE);
                }else{
                    binding.eceNoData.setVisibility(View.GONE);
                    binding.eceDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        eceList.add(data);
                    }
                    binding.eceDepartment.setHasFixedSize(true);
                    binding.eceDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new teacherAdapter(getContext(),eceList,"Electronics and Communincation");
                    binding.eceDepartment.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void eeDepartment() {
        dbRef=databaseReference.child("Electrical");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eList=new ArrayList<>();
                if(!dataSnapshot.exists()){
                    binding.eeNoData.setVisibility(View.VISIBLE);
                    binding.eeDepartment.setVisibility(View.GONE);
                }else{
                    binding.eeNoData.setVisibility(View.GONE);
                    binding.eeDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        eList.add(data);
                    }
                    binding.eeDepartment.setHasFixedSize(true);
                    binding.eeDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new teacherAdapter(getContext(),eList,"Electrical");
                    binding.eeDepartment.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void meDepartment() {
        dbRef=databaseReference.child("Mechanical");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mechList=new ArrayList<>();
                if(!dataSnapshot.exists()){
                    binding.meNoData.setVisibility(View.VISIBLE);
                    binding.meDepartment.setVisibility(View.GONE);
                }else{
                    binding.meNoData.setVisibility(View.GONE);
                    binding.meDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        mechList.add(data);
                    }
                    binding.meDepartment.setHasFixedSize(true);
                    binding.meDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new teacherAdapter(getContext(),mechList,"Mechanical");
                    binding.meDepartment.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void civilDepartment() {
        dbRef=databaseReference.child("Civil");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                civilList=new ArrayList<>();
                if(!dataSnapshot.exists()){
                    binding.civilNoData.setVisibility(View.VISIBLE);
                    binding.civilDepartment.setVisibility(View.GONE);
                }else{
                    binding.civilNoData.setVisibility(View.GONE);
                    binding.civilDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        civilList.add(data);
                    }
                    binding.civilDepartment.setHasFixedSize(true);
                    binding.civilDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new teacherAdapter(getContext(),civilList,"Civil");
                    binding.civilDepartment.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//        @Override
//        public void onCreateOptionsMenu(Menu menu, @NonNull MenuInflater inflater) {
//            inflater.inflate(R.menu.faculty_menu, menu);
//            super.onCreateOptionsMenu(menu, inflater);
//        }
}
