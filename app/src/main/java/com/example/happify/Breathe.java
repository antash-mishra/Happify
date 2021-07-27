package com.example.happify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//import com.example.happify.Timer;
//import com.example.happify.R;

public class Breathe extends AppCompatActivity {
    TextView m,n;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);
        m=findViewById(R.id.string);
         n=findViewById(R.id.file);

    }

    public void breathe(View v){
        Intent i = new Intent(this, Timer.class);
        startActivity(i);
    }
}