package com.example.happify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOError;

public class PTSD extends AppCompatActivity {
    EditText ans1, ans2, ans3, ans4 ,ans5;
    AppCompatButton sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptsd);
        ans1 = findViewById(R.id.ptans1);
        ans2 = findViewById(R.id.ptans2);
        ans3 = findViewById(R.id.ptans3);
        ans4 = findViewById(R.id.ptans4);
        ans5 = findViewById(R.id.ptans5);
        sub = findViewById(R.id.ptSubmit);
        sub.setOnClickListener((View v) ->{
            try{
                int n1,n2,n3,n4,n5;
                n1 = Integer.parseInt(ans1.getText().toString());
                n2 = Integer.parseInt(ans2.getText().toString());
                n3 = Integer.parseInt(ans3.getText().toString());
                n4 = Integer.parseInt(ans4.getText().toString());
                n5 = Integer.parseInt(ans5.getText().toString());
                if( n1 >= 0 && n1 <= 1 && n2 >=0 && n2 <= 1 && n3 >=0 && n3 <= 1 && n4 >=0 && n4 <= 1){
                    int sum = n1+n2+n3+n4+n5;
                    if(sum > 2)
                        Toast.makeText(this, "You have PTSD", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(this, "You don't have PTSD", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(this, "Enter 0 or 1", Toast.LENGTH_LONG).show();
            }
            catch (IOError e){
                e.printStackTrace();
            }
        });
    }
}