package com.example.adminuserapp.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.adminuserapp.R;

import com.example.adminuserapp.databinding.ActivityUploadFacultyBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class UploadFaculty extends AppCompatActivity {
    ActivityUploadFacultyBinding binding;
    private DatabaseReference databaseReference,dbRef;
    private ArrayList<TeacherData> csList,eceList,eList,mechList,civilList;
    private teacherAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUploadFacultyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UploadFaculty.this,AddTeacher.class));
                ////  Database ke andar jake kiske andar kya likhna........

            }
        });
        databaseReference= FirebaseDatabase.getInstance().getReference().child("teacher");
        csDepartment();
        eceDepartment();
        eeDepartment();
        meDepartment();
        civilDepartment();
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
                    binding.csDepartment.setLayoutManager(new LinearLayoutManager(UploadFaculty.this));
                    adapter=new teacherAdapter(UploadFaculty.this,csList,"Computer Science");
                    binding.csDepartment.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UploadFaculty.this,databaseError.getMessage() , Toast.LENGTH_SHORT).show();
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
                    binding.eceDepartment.setLayoutManager(new LinearLayoutManager(UploadFaculty.this));
                    adapter=new teacherAdapter(UploadFaculty.this,eceList,"Electronics and Communincation");
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
                    binding.eeDepartment.setLayoutManager(new LinearLayoutManager(UploadFaculty.this));
                    adapter=new teacherAdapter(UploadFaculty.this,eList,"Electrical");
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
                    binding.meDepartment.setLayoutManager(new LinearLayoutManager(UploadFaculty.this));
                    adapter=new teacherAdapter(UploadFaculty.this,mechList,"Mechanical");
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
                    binding.civilDepartment.setLayoutManager(new LinearLayoutManager(UploadFaculty.this));
                    adapter=new teacherAdapter(UploadFaculty.this,civilList,"Civil");
                    binding.civilDepartment.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}