package com.example.adminuserapp.notice;

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
import android.widget.Toast;

import com.example.adminuserapp.databinding.ActivityUploadNoticeBinding;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UploadNotice extends AppCompatActivity {
    ActivityUploadNoticeBinding binding;
    private final int REQ=1;
    private Bitmap bitmap;

    private DatabaseReference reference,dReb;
    private StorageReference storageReference;
    String downloadUrl="";
    private Calendar calDate,calTime;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUploadNoticeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog=new ProgressDialog(this);
       reference=FirebaseDatabase.getInstance().getReference();
       storageReference= FirebaseStorage.getInstance().getReference();
        binding.addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        //For Uploading
        binding.uploadNoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.noticeTitle.getText().toString().isEmpty()){
                    binding.noticeTitle.setError("Empty");
                    binding.noticeTitle.requestFocus();
                }else if(bitmap==null){
                    uploadData();
                }else{
                    uploadImage();
                }
            }
        });
    }






    private void uploadImage() {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalImg = baos.toByteArray();
        final StorageReference filePath;
        filePath=storageReference.child("Notice").child(finalImg+"jpg");
        final UploadTask uploadTask=filePath.putBytes(finalImg);
        uploadTask.addOnCompleteListener(UploadNotice.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
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
                 Toast.makeText(UploadNotice.this, "Something went wrong", Toast.LENGTH_SHORT).show();
             }
            }
        });
    }
    private void uploadData() {
        progressDialog.setMessage("Uploading......");
        progressDialog.show();
///////////////////////
        dReb=reference.child("Notice");
        final String uniqueKey=dReb.push().getKey();
        String title=binding.noticeTitle.getText().toString();
        ////////////
       calDate=Calendar.getInstance();
        SimpleDateFormat d=new SimpleDateFormat("dd-MM-yy");
        String date=d.format(calDate.getTime());
        //Now for time
        calTime=Calendar.getInstance();
        SimpleDateFormat t=new SimpleDateFormat("hh:mm a");
        String time=t.format(calTime.getTime());
        ////////////////////upto this now................
        NoticeData noticeData=new NoticeData(title,downloadUrl,date,time,uniqueKey);
        dReb.child(uniqueKey).setValue(noticeData).addOnSuccessListener(new OnSuccessListener<Void>() {
             @Override
             public void onSuccess(Void unused) {
                 progressDialog.dismiss();
                 Toast.makeText(UploadNotice.this, "Notice Uploaded", Toast.LENGTH_SHORT).show();
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 progressDialog.dismiss();
                 Toast.makeText(UploadNotice.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
            binding.noticeImageView.setImageBitmap(bitmap);
        }
    }
}