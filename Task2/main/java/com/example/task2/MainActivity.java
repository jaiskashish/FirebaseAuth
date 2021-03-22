package com.example.task2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
EditText fname,lname,school,subject,Email,pass;
Spinner age;
    String[] ag = { "5", "6", "7", "8", "9","10","11","12","13","14","15","Other"};
    String age1;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        school=(EditText)findViewById(R.id.school);
        subject=(EditText)findViewById(R.id.subject);
        Email=(EditText)findViewById(R.id.Email);
        pass=(EditText)findViewById(R.id.password);
        age=(Spinner)findViewById(R.id.age);
        age.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ag);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(aa);

    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

        age1=ag[position];
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void signup(View v){
        String fn=fname.getText().toString();
        String ln=lname.getText().toString();
        String sc=school.getText().toString();
        String sub=subject.getText().toString();
        String mail=Email.getText().toString();
        String password=pass.getText().toString();
        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users"); //parent
                            String userId = mDatabase.push().getKey();
                            User user1 = new User(fn, ln,age1,sc,sub,mail);
                            mDatabase.child(userId).setValue(user1);
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(fn+"\n"+ln).build();

                            user.updateProfile(profileUpdates);
                            Toast.makeText(MainActivity.this,"Your account is successfully created",Toast.LENGTH_SHORT).show();
                            Intent i1=new Intent(MainActivity.this,Login.class);
                            startActivity(i1);

                        } else {
                            // If sign in fails, display a message to the user.

                        }
                    }
                });
    }
    public void Log(View v){
        Intent i5=new Intent(MainActivity.this,Login.class);
        startActivity(i5);
    }
}