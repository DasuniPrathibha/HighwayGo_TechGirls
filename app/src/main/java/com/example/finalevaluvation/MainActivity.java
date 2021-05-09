package com.example.finalevaluvation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fb;
    Button btn;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<Bus> list;
    ArrayAdapter<Bus> adapter;
    Bus bus;
    String  busId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fb = (FloatingActionButton) findViewById(R.id.fAdd);
        fb.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,AddBus.class));
        });




        bus = new Bus();
        listView = (ListView)findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Bus");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<Bus>(this, R.layout.bus_info, R.id.busInfo);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){
                    bus = ds.getValue(Bus.class);
                    list.add(bus);

                }

                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bus bus =list.get(i);
                showUpdateDeleteDialog(busId,bus.getBusName(),bus.getBusNo(),bus.getbFrom(),bus.getbTo(),bus.getbTelephone());
            }
        });
    }
    // update bus details
    private void showUpdateDeleteDialog(String busId,String travelsName,String busNumber,String from, String to,String telephone){
        AlertDialog.Builder dialogBuilder =new AlertDialog.Builder(this);
        LayoutInflater inflater =getLayoutInflater();
        final View dialogView =inflater.inflate(R.layout.update_delete,null);
        dialogBuilder.setView(dialogView);

        final EditText editTravelsName  =(EditText)dialogView.findViewById(R.id.etvBusNameView);
        final EditText editBusNumber =(EditText)dialogView.findViewById(R.id.etvBusNoView);
        final EditText editFromBus =(EditText)dialogView.findViewById(R.id.etvFromView);
        final EditText editToBus=(EditText) findViewById(R.id.etvToView);
        final EditText editTelephone=(EditText) findViewById(R.id.etvTelephoneView);
        final Button buttonUpdate   =(Button)dialogView.findViewById(R.id.btnUpdate);
        final Button buttonDelete   =(Button)dialogView.findViewById(R.id.btnDelete);

        dialogBuilder.setTitle("Updating "+travelsName);

        final AlertDialog alertDialog =dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bus_1 = editTravelsName.getText().toString();
                String bus_2 = editBusNumber.getText().toString();
                String bus_3 = editTelephone.getText().toString();
                String bus_4 = editFromBus.getText().toString();
                String bus_5 = editToBus.getText().toString();

                updateBusDetail(bus_1,bus_2,bus_4,bus_5,bus_3);
                alertDialog.dismiss();
            }
        });



        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteBus(busId);
            }
        });



    }
    private void deleteBus(String busId){
        DatabaseReference drTravellingPath =FirebaseDatabase.getInstance().getReference("Bus").child(busId);

        drTravellingPath.removeValue();

        Toast.makeText(this, " Bus Detail Deleted Successfully", Toast.LENGTH_LONG).show();

    }

    private boolean updateBusDetail(String busNameI,String busNumberI,String from,String to,String telephoneI){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Bus").child(busId);


        Bus bus = new Bus(busNameI,busNumberI,from,to,telephoneI);
        databaseReference.setValue(bus);

        Toast.makeText(this, "Bus Detail Updated Successfully ", Toast.LENGTH_LONG).show();
        return true;
    }



}