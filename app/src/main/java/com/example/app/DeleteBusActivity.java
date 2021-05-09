package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DeleteBusActivity extends ArrayAdapter<Schedule> {

    private Activity context;
    private List<Schedule> scheList;
    Schedule sche1;

    public DeleteBusActivity(Activity context, List<Schedule> scheList) {
        super(context, R.layout.activity_view, scheList);
        this.context = context;
        this.scheList = scheList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_view, null, true);


        TextView textViewRegNo = (TextView) listViewItem.findViewById(R.id.etregno2);
        TextView textViewRoute = (TextView) listViewItem.findViewById(R.id.etroute2);
        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.etdate2);
        TextView textViewDeparture = (TextView) listViewItem.findViewById(R.id.etdeparture2);
        TextView textViewArrival = (TextView) listViewItem.findViewById(R.id.etarrival2);


        Schedule sche = scheList.get(position);

        textViewRegNo.setText((sche.getRegNo()));
        textViewRoute.setText(sche.getRoute());
        textViewDate.setText(sche.getDate());
        textViewDeparture.setText(sche.getDeparture());
        textViewArrival.setText(sche.getArrival());



        return listViewItem;

    }


}