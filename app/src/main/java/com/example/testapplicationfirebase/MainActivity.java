package com.example.testapplicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText txtId, txtName, txtAddress, txtContact;
    Button btnsave, btnshow, btnupdate, btndelete;

    DatabaseReference dbRef;
    Student std;

    private void clearControls(){
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId = findViewById(R.id.editId);
        txtName = findViewById(R.id.editName);
        txtAddress = findViewById(R.id.editAddress);
        txtContact = findViewById(R.id.editContactNo);

        btnsave = findViewById(R.id.btnSave);
        btnshow = findViewById(R.id.btnShow);
        btnupdate = findViewById(R.id.btnUpdate);
        btndelete = findViewById(R.id.btnDelete);

        std = new Student();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Student");

                try{
                    if(TextUtils.isEmpty(txtId.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please Enter Your ID.",Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(txtName.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Please Enter Your Name.", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(txtAddress.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Please Enter Your Address.", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(txtContact.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Please Enter Your Contact Number.", Toast.LENGTH_SHORT).show();
                    }else{
                        std.setId(txtId.getText().toString().trim());
                        std.setName(txtName.getText().toString().trim());
                        std.setAddress(txtAddress.getText().toString().trim());
                        std.setContactNo(Integer.parseInt(txtContact.getText().toString().trim()));

                        dbRef.push().setValue(std);
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Invalide Contact Number.Please Try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Student").child("-Lnw9UVF0g-MTf3xnf0r");
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            txtId.setText(dataSnapshot.child("id").getValue().toString());
                            txtName.setText(dataSnapshot.child("name").getValue().toString());
                            txtAddress.setText(dataSnapshot.child("address").getValue().toString());
                            txtContact.setText(dataSnapshot.child("contactNo").getValue().toString());
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"No Source to Display.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updateref = FirebaseDatabase.getInstance().getReference().child("Student");
                updateref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("-Lnw9UVF0g-MTf3xnf0r")){
                            try{
                                std.setId(txtId.getText().toString().trim());
                                std.setName(txtName.getText().toString().trim());
                                std.setAddress(txtAddress.getText().toString().trim());
                                std.setContactNo(Integer.parseInt(txtContact.getText().toString().trim()));

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("-Lnw9UVF0g-MTf3xnf0r");
                                dbRef.setValue(std);
                                clearControls();

                                Toast.makeText(getApplicationContext(),"Data Updated Successfully.",Toast.LENGTH_SHORT).show();
                            }catch(NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number.", Toast.LENGTH_SHORT).show();

                            }
                        }else
                            Toast.makeText(getApplicationContext(), "No source to Update.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference().child("Student");
                deleteRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("-Lnw9UVF0g-MTf3xnf0r")){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("-Lnw9UVF0g-MTf3xnf0r");
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(),"Data Deleted Successfully.",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"No such a source to Delete.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
