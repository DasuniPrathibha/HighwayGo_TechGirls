package com.example.app;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewSchedule extends AppCompatActivity {


    Button btn;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<Schedule> list;
    ArrayAdapter<Schedule> adapter;
    Schedule schedule;
    String scheduleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schedule = new Schedule();
        listView = (ListView) findViewById(R.id.listViewScheduleDetails);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Bus");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<Schedule>(this, R.layout.activity_view_schedule);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    schedule = ds.getValue(Schedule.class);
                    list.add(schedule);

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
                Schedule schedule = list.get(i);
                showUpdateDeleteDialog(schedule.getScheduleId(), schedule.getRegNo(), schedule.getRoute(), schedule.getDate(), schedule.getDeparture(), schedule.getArrival());
            }
        });


    }


    // update ticket fees details
    private void showUpdateDeleteDialog(final String scheduleId,String regNo, String route, String date, String departure, String arrival) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_window, null);

        dialogBuilder.setView(dialogView);

        final EditText editRegNo = (EditText) dialogView.findViewById(R.id.eregno);
        final EditText editRoute = (EditText) dialogView.findViewById(R.id.eroute);
        final EditText editDate = (EditText) dialogView.findViewById(R.id.edate);
        final EditText editDeparture = (EditText) findViewById(R.id.edeparture);
        final EditText editArrival = (EditText) findViewById(R.id.earrival);

        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonupdate);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttondelete);


        dialogBuilder.setTitle("Updating " + date);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String schedule_1 = editRegNo.getText().toString();
                String schedule_2 = editRoute.getText().toString();
                String schedule_3 = editDate.getText().toString();
                String schedule_4 = editDeparture.getText().toString();
                String schedule_5 = editArrival.getText().toString();


                updateScheduleDetail(scheduleId, schedule_1, schedule_2, schedule_3, schedule_4, schedule_5);
                alertDialog.dismiss();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteBus(scheduleId);
            }
        });


    }

    private void deleteBus(String scheduleId) {
        DatabaseReference drTravellingPath = FirebaseDatabase.getInstance().getReference("Schedule").child(scheduleId);

        drTravellingPath.removeValue();

        Toast.makeText(this, " Schedule Detail Deleted Successfully", Toast.LENGTH_LONG).show();

    }

    private boolean updateScheduleDetail(String regNo, String route, String date, String departure, String arrival) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Schedule").child(scheduleId);


        Schedule schedule = new Schedule(scheduleId,regNo, route, date, departure, arrival);
        databaseReference.setValue(schedule);

        Toast.makeText(this, "Schedule Detail Updated Successfully ", Toast.LENGTH_LONG).show();
        return true;
    }

}
/*
    private ListView listViewSchedule;
     DatabaseReference databaseSchedule;
     FirebaseDatabase database;
     List<Schedule> scheduleList;
String scheduleId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        getSupportActionBar().setTitle("All Schedule Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        scheduleList = new ArrayList<>();

        listViewSchedule = (ListView) findViewById(R.id.listViewScheduleDetails);
        databaseSchedule = FirebaseDatabase.getInstance().getReference();

        FirebaseDatabase.getInstance().getReference("Schedule")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        scheduleList.clear();
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Schedule schedule = snapshot.getValue(Schedule.class);
                                scheduleList.add(schedule);
                            }
                            ScheduleList adapter = new ScheduleList(ViewSchedule.this, scheduleList);
                            listViewSchedule.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        listViewSchedule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Schedule schedule =scheduleList.get(i);
                showUpdateDeleteDialog(schedule.getRegNo(),schedule.getRoute(),schedule.getDate(),schedule.getDeparture(),schedule.getArrival());
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

    }



    private void showUpdateDeleteDialog(final String regNo,String route,String date,String departure,String arrival){
        AlertDialog.Builder dialogBuilder =new AlertDialog.Builder(this);

        LayoutInflater inflater =getLayoutInflater();

        final View dialogView =inflater.inflate(R.layout.update_window,null);

        dialogBuilder.setView(dialogView);

        final EditText editRegNo  =(EditText)dialogView.findViewById(R.id.eregno);
        final EditText editRoute =(EditText)dialogView.findViewById(R.id.eroute);
        final EditText editDate       =(EditText)dialogView.findViewById(R.id.edate);
        final EditText editDeparture=(EditText) findViewById(R.id.edeparture);
        final EditText editArrival=(EditText) findViewById(R.id.earrival);

        final Button buttonUpdate   =(Button)dialogView.findViewById(R.id.buttonupdate);
        final Button buttonDelete   =(Button)dialogView.findViewById(R.id.buttondelete);



        dialogBuilder.setTitle("Updating "+date);

        final AlertDialog alertDialog =dialogBuilder.create();
        alertDialog.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String schedule_1 = editRegNo.getText().toString().trim();
                String schedule_2 = editRoute.getText().toString().trim();
                String schedule_3 = editDate.getText().toString().trim();
                String schedule_4 = editDeparture.getText().toString();
                String schedule_5 = editArrival.getText().toString();



                updateScheduleDetail(scheduleId,schedule_1,schedule_2,schedule_3,schedule_4,schedule_5);
                alertDialog.dismiss();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBus(scheduleId);
            }
        });



    }
    private void deleteBus(String busId){
        DatabaseReference drTravellingPath =FirebaseDatabase.getInstance().getReference("BusDetails").child(busId);

        drTravellingPath.removeValue();

        Toast.makeText(this, " Bus Detail Deleted Successfully", Toast.LENGTH_LONG).show();

    }
    private boolean updateScheduleDetail(String scheduleId,String regNo,String route,String date,String departure,String arrival){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Schedule").child(scheduleId);

        Schedule schedule = new Schedule(scheduleId, regNo, route, date, departure, arrival);
        databaseReference.setValue(schedule);

        Toast.makeText(this, "Schedule Detail Updated Successfully ", Toast.LENGTH_LONG).show();
        return true;
    }
*/
