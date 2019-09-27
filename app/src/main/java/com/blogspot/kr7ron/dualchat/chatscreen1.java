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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class chatscreen1 extends Activity {

    public FirebaseAuth fa;
    public DatabaseReference dr,dr1;
    public EditText e;
    public TextView t;
    public Button b;
    public Button Send;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatscreen1);
        fa=FirebaseAuth.getInstance();
        dr= FirebaseDatabase.getInstance().getReference().child("Admin").child("txt");
        dr1= FirebaseDatabase.getInstance().getReference().child("Guest");
        t=(TextView)findViewById(R.id.utext1);
        e=(EditText)findViewById(R.id.usend);
        Send=(Button)findViewById(R.id.usendbutton);
        b=(Button)findViewById(R.id.button5);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Msg m=new Msg(e.getText().toString());
                dr1.setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(chatscreen1.this,"Sent",Toast.LENGTH_SHORT).show();
                        e.setText("");
                    }
                });
            }
        });
        dr.addValueEventListener(new ValueEventListener() {
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
                startActivity(new Intent(chatscreen1.this,Login.class));
            }
        });
    }
}
