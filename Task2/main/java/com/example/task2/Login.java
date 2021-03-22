package com.example.task2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
EditText mail1,pass2;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String TAG="mynam";
private FirebaseDatabase database;
private DatabaseReference reference;
    String currentuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mail1=(EditText)findViewById(R.id.mail1);
        pass2=(EditText)findViewById(R.id.pass1);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");
        FirebaseUser user = mAuth.getCurrentUser();


    }
    public void Login(View v){
        String email1=mail1.getText().toString();
        String pass1=pass2.getText().toString();
        mAuth.signInWithEmailAndPassword(email1, pass1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this,"Success",Toast.LENGTH_SHORT).show();

                            Intent i2=new Intent(Login.this,Profile.class);
                            startActivity(i2);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
    public void Account(View v){
        Intent i4=new Intent(Login.this,MainActivity.class);
        startActivity(i4);
    }
}