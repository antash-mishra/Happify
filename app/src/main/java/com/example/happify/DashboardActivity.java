package com.example.happify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.happify.MainActivity;
public class DashboardActivity extends AppCompatActivity {

    Button btLogout;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        //Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        //Initialize firebase user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        btLogout = findViewById(R.id.bt_logout);

//        if(firebaseUser != null) {
//            //set image view
//            Glide.with(ProfileActivity.this)
//                    .load(firebaseUser.getPhotoUrl())
//                    .into(ivImage);
//            tvName.setText(firebaseUser.getDisplayName());
//        }


        //Initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN);

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sign out from google
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // check condition
                        if(task.isSuccessful()) {
                            //when task is successful
                            // sign out from google
                            firebaseAuth.signOut();

                            //Display text
                            Toast.makeText(getApplicationContext(), "Logout successful",
                                    Toast.LENGTH_SHORT).show();
                        }
                        //Finish activity
                        finish();
                    }
                });
            }
        });

        //final Button close = findViewById(R.id.close);
        //close.setActivated(true);
    }

    public void test(View v){
        Intent i = new Intent(this, Test.class);
        startActivity(i);
    }
    public void breathe(View v){
        Intent i = new Intent(this,Breathe.class);
        startActivity(i);
    }
    public void read(View v){
        Intent i = new Intent(this, Read.class);
        startActivity(i);
    }

    public void profile_view(View v){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    //public void close(View v){
    //    MainActivity.this.finish();
    //    System.exit(1);
    //}
}
