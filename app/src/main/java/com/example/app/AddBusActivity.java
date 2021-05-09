package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.database.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AddBusActivity extends AppCompatActivity {

   private EditText addRegNo, addDate, addRoute, addDeparture, addArrival;
   private Button add;
    Schedule schedule;
    DatabaseReference dbRef;

    int scheduleId = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        addRegNo = findViewById(R.id.etRegNo);
        addRoute = findViewById(R.id.etRoute);
        addDate = findViewById(R.id.etDate);
        addDeparture = findViewById(R.id.etDeparture);
        addArrival = findViewById(R.id.etArrival);

        add = findViewById(R.id.btnAdd);


        dbRef = FirebaseDatabase.getInstance().getReference().child("Schedule");

        schedule = new Schedule();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    scheduleId = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String regNo = addRegNo.getText().toString().trim();
                String route = addRoute.getText().toString().trim();
                String date = addDate.getText().toString().trim();
                String departure = addDeparture.getText().toString().trim();
                String arrival = addArrival.getText().toString().trim();

                try {
                    if (TextUtils.isEmpty(addRegNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Registration No", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(addRoute.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Route", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(addDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(addDeparture.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Departure", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(addArrival.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Arrival", Toast.LENGTH_SHORT).show();
                    else {
                        //  schedule.setRegNo(_enterName.getText().toString().trim()); // getting this to access db
                        schedule.setRegNo(regNo);
                        schedule.setRoute(route);
                        schedule.setDate(date);
                        schedule.setDeparture(departure);
                        schedule.setArrival(arrival);


                        dbRef.child(String.valueOf(scheduleId + 1)).setValue(schedule);

                        Toast.makeText(AddBusActivity.this, "Schedule added successfully!", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (Exception e) {
                    e.getCause();
                    e.printStackTrace();
                }

            }
        });

    }
/*
        add.setOnClickListener(new View.OnClickListener(){
           // DatabaseReference dbRef;
          //  dbRef = FirebaseDatabase.getInstance.getReference().child("Schedule");
            rootnode = FirebaseDatabase.getInstance();
            reference = rootnode.getReference("");

            try{
                if(TextUtils.isEmpty(addRegNo.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Please Enter Registration No",Toast.LENGTH_SHORT).show();
                 else if(TextUtils.isEmpty(addRoute.getText().toString()))
                          Toast.makeText(getApplicationContext(),"Please Enter Route",Toast.LENGTH_SHORT).show();
                  else if(TextUtils.isEmpty(addDate.getText().toString()))
                            Toast.makeText(getApplicationContext(),"Please Enter Date",Toast.LENGTH_SHORT).show();
                   else if(TextUtils.isEmpty(addDeparture.getText().toString()))
                              Toast.makeText(getApplicationContext(),"Please Enter Departure",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(addArrival.getText().toString()))
                               Toast.makeText(getApplicationContext(),"Please Enter Arrival",Toast.LENGTH_SHORT).show();
                     else{
                           schedule.setRegNo(addRegNo.getText().toString().trim());
                           schedule.setRoute(addRegNo.getText().toString().trim());
                           schedule.setDate(addRegNo.getText().toString().trim());
                           schedule.setDeparture(addRegNo.getText().toString().trim());
                           schedule.setArrival(addRegNo.getText().toString().trim());

                          // dbRef.push().setValue(schedule);
                    dbRef.child("sch1").setValue(schedule);

                           Toast.makeText(getApplicationContext(), "Schedule Added Successfully!", Toast.LENGTH_SHORT).show();
                           clearControls();
                }

            }
            catch(Exception exception){
                Toast.makeText(getApplicationContext(), "Invalid Inputs,Please Check!", Toast.LENGTH_SHORT).show();
            }


        });
*/
    private void clearControls(){
    addRegNo.setText("");
    addRoute.setText("");
    addDate.setText("");
    addDeparture.setText("");
    addArrival.setText("");
}
}