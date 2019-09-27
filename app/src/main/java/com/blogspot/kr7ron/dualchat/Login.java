package com.blogspot.kr7ron.dualchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.oob.SignUp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Activity {
    Button b;
    TextView t;
    EditText email,password;
    FirebaseAuth fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b=(Button)findViewById(R.id.button2);
        t=(TextView)findViewById(R.id.textView);
        email=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
        fa=FirebaseAuth.getInstance();
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fa.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this, "Logged in!", Toast.LENGTH_SHORT).show();
                            if(email.getText().toString().equals("barkavk@gmail.com"))
                            {
                                startActivity(new Intent(Login.this,chatscreen.class));
                            }
                            else
                            {
                                startActivity(new Intent(Login.this,chatscreen1.class));
                            }
                        }
                        else
                            Toast.makeText(Login.this,"Failed!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
