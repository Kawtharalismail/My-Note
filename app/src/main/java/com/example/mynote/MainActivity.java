package com.example.mynote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static List<NoteModel> noteModelList;
    FloatingActionButton addButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteModelList=new ArrayList<>();
        addButton=findViewById(R.id.addButton);
        recyclerView=findViewById(R.id.noteRecyclerView);
        setUpNoteView();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            setUpNoteView();
        }
    }

    public void addNote(View view){
        Intent addNoteIntent=new Intent(MainActivity.this,AddActivity.class);
        startActivityForResult(addNoteIntent,10);
    }


    public void setUpNoteView(){
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new NoteAdapter(MainActivity.this,noteModelList));

    }

    @Override
    public void onRestart() {
        super.onRestart();
        setUpNoteView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
