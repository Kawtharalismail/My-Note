package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ShowNote extends AppCompatActivity {
    private EditText showTitle,showDescription;
    private FloatingActionButton okButton;
    private Intent intent;
    private ImageView showImage;
    int position;
    static  int  RESULT_LOAD_IMG=1010;


    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                showImage.setImageBitmap(selectedImage);
                showTitle.setEnabled(true);
                showDescription.setEnabled(true);
                okButton.show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(ShowNote.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(ShowNote.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        showTitle=findViewById(R.id.showTitleText);
        showDescription=findViewById(R.id.showDescriptionText);
        okButton=findViewById(R.id.okButton);
        showImage=findViewById(R.id.showImage);
        intent = getIntent();
        position=intent.getIntExtra("position",0);
        Toast.makeText(this, "now :"+position,
                Toast.LENGTH_LONG).show();
        showTitle.setText(MainActivity.noteModelList.get(position).getItemText());
        showDescription.setText( MainActivity.noteModelList.get(position).getItemDescriptionText());
        showImage.setImageDrawable(MainActivity.noteModelList.get(position).getItemImage());
    }



    public void editNote(View view){
        showTitle.setEnabled(true);
        showDescription.setEnabled(true);
        showImage.setImageAlpha(150);
        okButton.show();
    }

    public void changeNoteImage(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }


    public void endorseTheChanges(View view){
        MainActivity.noteModelList.get(position).setItemText(showTitle.getText().toString());
        MainActivity.noteModelList.get(position).setItemDescriptionText(showDescription.getText().toString());
        finish();
    }
}
