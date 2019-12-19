package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {

    ImageView loadImage;
    EditText addedTitle,addedDescription;
    Button addButton;
    ConstraintLayout addImageLayout;
    static  int  RESULT_LOAD_IMG=1010;

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                loadImage.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(AddActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(AddActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        loadImage=findViewById(R.id.loadImage);
        addedTitle=findViewById(R.id.addedTitle);
        addedDescription=findViewById(R.id.addedDescription);
        addButton=findViewById(R.id.addButton);
        addImageLayout=findViewById(R.id.addedImage);
    }

    public void AddreturnNote(View view){

        NoteModel note2=new NoteModel();
        note2.setItemImage(loadImage.getDrawable());
        note2.setItemText(addedTitle.getText().toString());
        note2.setItemDescriptionText(addedDescription.getText().toString());
        note2.setItemSwitch(false);
        MainActivity.noteModelList.add(note2);

        Intent addNoteIntent=new Intent(AddActivity.this,MainActivity.class);
        setResult(RESULT_OK,addNoteIntent);
        finish();
    }

    public void setImage(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }
}
