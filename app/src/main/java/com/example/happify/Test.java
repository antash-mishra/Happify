package com.example.happify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Test extends AppCompatActivity {
    AppCompatButton dep, ptsd, anxiety, addiction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        dep = findViewById(R.id.depTest);
        ptsd = findViewById(R.id.ptsdTest);
        anxiety = findViewById(R.id.anxTest);
        addiction = findViewById(R.id.addTest);

        dep.setOnClickListener((View v) -> startActivity(new Intent(Test.this, Depression.class)));
        ptsd.setOnClickListener((View v) -> startActivity(new Intent(Test.this, PTSD.class)));
        anxiety.setOnClickListener((View v)-> startActivity(new Intent(Test.this, Anxiety.class)));
        addiction.setOnClickListener((View v)->startActivity(new Intent(Test.this, Addiction.class)));
    }
}