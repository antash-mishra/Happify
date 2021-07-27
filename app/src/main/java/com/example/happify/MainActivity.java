package com.example.happify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private final static int RC_SIGN_IN = 100;
    SignInButton signInButton;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setColorScheme(0);

        //Initialize sign in options
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken ("713564509257-3k6704a4e74qhcahd9ulmhvqnv6arvpo.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize sign in intent
                signIn();
            }
        });

        // Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUserUser = firebaseAuth.getCurrentUser();
        if(currentUserUser!=null) {
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 100);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Result returned from mGoogleSignInClient.getSignInIntent();
        if (requestCode == 100) {
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn
                    .getSignedInAccountFromIntent(data);
            //check condition
            if (signInAccountTask.isSuccessful()) {
                //when google sign in is successful
                //Initialize string
                String s = "Google sign in successful";
                displayToast(s);
            }


            try {
                GoogleSignInAccount account = signInAccountTask.getResult(ApiException.class);
//                // Log.d(TAG, "firebaseAuthWithGoogle:", account.getId());
                //check condition
                if (account != null) {
                    AuthCredential authCredential = GoogleAuthProvider
                            .getCredential(account.getIdToken(), null);

                    //check credential
                    firebaseAuth.signInWithCredential(authCredential)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        // Log.d(TAG, "signInWithCredential:success");

                                        //If successful redirect to profile activity
                                        startActivity(new Intent(getApplicationContext()
                                                , DashboardActivity.class)
                                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                        //Firebase authenticated successfully

                                    }

                                    else {
                                        // If sign in fails, display a message to the user.
                                        displayToast("Authentication failed" +
                                                task.getException().getMessage());
                                    }
                                }

                            });
                }
            }

            catch (ApiException e) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                //Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                // ...
                e.printStackTrace();
            }

        }
    }

    private void displayToast(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

}