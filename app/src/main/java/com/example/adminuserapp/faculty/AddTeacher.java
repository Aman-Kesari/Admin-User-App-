package com.example.adminuserapp.faculty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.adminuserapp.databinding.ActivityAddTeacherBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class AddTeacher extends AppCompatActivity {
    ActivityAddTeacherBinding binding;
    private final int REQ=1;
    private Bitmap bitmap;
    private String selectedCategory;
    private StorageReference storageReference;
    private DatabaseReference databaseReference,dbReb;
    private ProgressDialog progressDialog;
    private Calendar calDate,calTime;
    private String name,email,post,downloadUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddTeacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //
        storageReference= FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("teacher");
        //
        progressDialog=new ProgressDialog(this);
        binding.addTeacherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        String items[]=new String[]{"Select Category","Computer Science","Electronics and Communincation","Electrical","Mechanical","Civil"};
        binding.teacherCategory.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items));
        binding.teacherCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              selectedCategory=binding.teacherCategory.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.uploadNoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });
    }

    private void checkValidation() {
        name = binding.addTeacherName.getText().toString();
        email = binding.addTeacherEmail.getText().toString();
        post = binding.addTeacherPost.getText().toString();
        if (name.isEmpty()) {
            binding.addTeacherName.setError("Empty");
            binding.addTeacherName.requestFocus();
        } else if (email.isEmpty()) {
            binding.addTeacherEmail.setError("Empty");
            binding.addTeacherEmail.requestFocus();
        } else if (post.isEmpty()) {
            binding.addTeacherPost.setError("Empty");
            binding.addTeacherPost.requestFocus();
        }else if(selectedCategory.equals("Select Category")){
            Toast.makeText(this, "Please select the category", Toast.LENGTH_SHORT).show();
        }
        else if (bitmap == null) {
            uploadData();
        } else {
            uploadImage();
        }
    }



    private void uploadImage() {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalImg = baos.toByteArray();
        final StorageReference filePath;
        //Inside Teachers Group
        filePath=storageReference.child("Teacher's").child(finalImg+"jpg");
        final UploadTask uploadTask=filePath.putBytes(finalImg);
        uploadTask.addOnCompleteListener(AddTeacher.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl=String.valueOf(uri);
                                    uploadData();
                                }
                            });
                        }
                    });
                }
                else{
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void uploadData() {
        progressDialog.setMessage("Uploading......");
        progressDialog.show();
        dbReb=databaseReference.child(selectedCategory);
        final String uniqueKey=dbReb.push().getKey();
        //Object Creation   ......
        TeacherData teacherData=new TeacherData(name,email,post,downloadUrl,uniqueKey);
        dbReb.child(uniqueKey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(AddTeacher.this, "Teacher Added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void openGallery() {
        Intent pickImage=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage,REQ);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ && resultCode==RESULT_OK){
            Uri uri=data.getData();
            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.addTeacherImage.setImageBitmap(bitmap);
        }
    }
}
