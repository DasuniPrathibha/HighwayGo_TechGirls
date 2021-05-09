package com.example.finalevaluvation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBus extends AppCompatActivity {

    EditText txtBusName, txtBusNo, txtFrom, txtTo, txtTelephone;
    Button Addbtn;
    DatabaseReference dbref;
    Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        txtBusName = (EditText)findViewById(R.id.etvBusNameView);
        txtBusNo = (EditText)findViewById(R.id.etvBusNoView);
        txtFrom = (EditText)findViewById(R.id.etvFromView);
        txtTo = (EditText)findViewById(R.id.etvToView);
        txtTelephone = (EditText)findViewById(R.id.etvTelephoneView);

        Addbtn = (Button)findViewById(R.id.fAdd);


        bus = new Bus();


        Addbtn.setOnClickListener((view) -> {
            dbref = FirebaseDatabase.getInstance().getReference().child("Bus");

            try{
            if (TextUtils.isEmpty(txtBusName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Empty Bus Name", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtBusNo.getText().toString()))
                Toast.makeText(getApplicationContext(), "Empty Bus Number", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtFrom.getText().toString()))
                Toast.makeText(getApplicationContext(), "Empty From field", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtTo.getText().toString()))
                Toast.makeText(getApplicationContext(), "Empty To Field", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtTelephone.getText().toString()))
                Toast.makeText(getApplicationContext(), "Empty Bus Number", Toast.LENGTH_SHORT).show();
            else {
                bus.setBusName(txtBusName.getText().toString());
                bus.setBusNo(txtBusNo.getText().toString());
                bus.setbFrom(txtFrom.getText().toString());
                bus.setbTo(txtTo.getText().toString());
                bus.setbTelephone((txtTelephone.getText().toString()));
                Toast.makeText(getApplicationContext(), "Data inserted Successfully", Toast.LENGTH_SHORT).show();
                clearControls();
            }
        }
            catch(NumberFormatException ex){
                Toast.makeText(getApplicationContext(),"Invalid contact no", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearControls() {
        txtBusName.setText("");
        txtTelephone.setText("");
        txtBusNo.setText("");
        txtFrom.setText("");
        txtTo.setText("");
    }
}