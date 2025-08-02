package com.example.adminuserapp.faculty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.adminuserapp.R;
import com.example.adminuserapp.databinding.ActivityUpdateBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {
    ActivityUpdateBinding binding;
    private int REQ=1;
    private Bitmap bitmap=null;
    private String name,email,post,image;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private String downloadUrl;
    private String category,uniqueKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        storageReference= FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("teacher");
        binding.updateTeacherName.setText(getIntent().getStringExtra("name"));
        binding.updateTeacherEmail.setText(getIntent().getStringExtra("email"));
        binding.updateTeacherPost.setText(getIntent().getStringExtra("post"));
        try {
            Picasso.get().load(getIntent().getStringExtra("image")).into(binding.updateTeacherImage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        uniqueKey=getIntent().getStringExtra("uniqueKey");
        category=getIntent().getStringExtra("category");
        binding.updateTeacherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        binding.updateTeacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=binding.updateTeacherName.getText().toString();
                email=binding.updateTeacherEmail.getText().toString();
                post=binding.updateTeacherPost.getText().toString();
                checkValidation();

            }
        });
        binding.deleteTeacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
    }

    private void checkValidation() {
        if(name.isEmpty()){
            binding.updateTeacherName.setError("Empty");
            binding.updateTeacherName.requestFocus();
        }else if(post.isEmpty()){
            binding.updateTeacherPost.setError("Empty");
            binding.updateTeacherPost.requestFocus();
        }else if(email.isEmpty()){
            binding.updateTeacherEmail.setError("Empty");
            binding.updateTeacherEmail.requestFocus();
        }else if(bitmap==null){
            updateData(getIntent().getStringExtra("image"));
        }else{
            updateImage();
        }
    }

    private void deleteData() {
        databaseReference.child(category).child(uniqueKey).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(UpdateActivity.this, "Teacher Deleted", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UpdateActivity.this,UploadFaculty.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateData(String s) {
        HashMap hp=new HashMap();
        hp.put("name",name);
        hp.put("post",post);
        hp.put("email",email);
        hp.put("image",s);
        databaseReference.child(category).child(uniqueKey).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(UpdateActivity.this, "Teacher Updated Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UpdateActivity.this,UploadFaculty.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateImage() {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalImg = baos.toByteArray();
        final StorageReference filePath;
        //Inside Teachers Group
        filePath=storageReference.child("Teacher's").child(finalImg+"jpg");
        final UploadTask uploadTask=filePath.putBytes(finalImg);
        uploadTask.addOnCompleteListener(UpdateActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
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
                                    //Dekhta Hu
                                    updateData(downloadUrl);
                                }
                            });
                        }
                    });
                }
                else{
                    Toast.makeText(UpdateActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
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
            binding.updateTeacherImage.setImageBitmap(bitmap);
        }
    }
}
