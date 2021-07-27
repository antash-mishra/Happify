package com.example.happify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOError;

public class Depression extends AppCompatActivity {
    EditText ans1, ans2, ans3, ans4, ans5, ans6;
    AppCompatButton sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression);
        ans1 = findViewById(R.id.dans1);
        ans2 = findViewById(R.id.dans2);
        ans3 = findViewById(R.id.dans3);
        ans4 = findViewById(R.id.dans4);
        ans5 = findViewById(R.id.dans5);
        ans6 = findViewById(R.id.dans6);
        sub = findViewById(R.id.dsubmit);
        sub.setOnClickListener((View v) ->{
            try {
                int n1,n2,n3,n4,n5,n6;
                n1 = Integer.parseInt(ans1.getText().toString());
                n2 = Integer.parseInt(ans2.getText().toString());
                n3 = Integer.parseInt(ans3.getText().toString());
                n4 = Integer.parseInt(ans4.getText().toString());
                n5 = Integer.parseInt(ans5.getText().toString());
                n6 = Integer.parseInt(ans6.getText().toString());
                if (n1 <= 5 && n1 >= 1 && n2 <= 5 && n2 >= 1 && n3 <= 5 && n3 >= 1 && n4 <= 5 && n4 >= 1 && n5 <= 5 && n5 >= 1 && n6 <= 5 && n6 >= 1) {
                    int sum = n1+n2+n3+n4+n5+n6;
                    String diagnosis;
                    if(sum < 12)
                        diagnosis = "No depression";
                    else if(sum < 18)
                        diagnosis = "Acute Depression";
                    else if(sum <24)
                        diagnosis="Mild Depression";
                    else
                        diagnosis="Severe Depression";

                    Toast.makeText(this, diagnosis, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Values only between 1 and 5", Toast.LENGTH_LONG).show();
                }
            }
            catch(IOError e){
                e.printStackTrace();
            }
        } );
    }
}