package com.blogspot.kr7ron.dualchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileOutputStream;

public class chatscreen extends Activity {
    FirebaseAuth fa;
    DatabaseReference dr,dr1;
    EditText e;
    TextView t;
    Button b;
    Button Send;

    @Override
    public void onBackPressed() {
        Toast.makeText(chatscreen.this, "Back",Toast.LENGTH_SHORT);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatscreen);
        fa=FirebaseAuth.getInstance();
        dr= FirebaseDatabase.getInstance().getReference().child("Admin");
        dr1= FirebaseDatabase.getInstance().getReference().child("Guest").child("txt");
        t=(TextView)findViewById(R.id.ktext1);
        e=(EditText)findViewById(R.id.ksend);
        Send=(Button)findViewById(R.id.sendbutton);
        b=(Button)findViewById(R.id.button4);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Msg m=new Msg(e.getText().toString());
                dr.setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(chatscreen.this,"Sent",Toast.LENGTH_SHORT).show();
                        e.setText("");
                    }
                });
            }
        });
        dr1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                t.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fa.signOut();
                startActivity(new Intent(chatscreen.this,Login.class));
            }
        });

    }
}
