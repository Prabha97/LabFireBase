package com.example.testapplicationfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                        std.setId(txtId.getText().toString());
                        std.setName(txtName.getText().toString());
                        std.setAddress(txtAddress.getText().toString());
                        std.setContactNo(Integer.parseInt(txtContact.getText().toString()));

                        dbRef.push().setValue(std);
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Invalide Contact Number.Please Try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
