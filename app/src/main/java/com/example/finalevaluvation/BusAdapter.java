package com.example.finalevaluvation;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BusAdapter extends ArrayAdapter<Bus> {
    private Activity context;
    private List<Bus> busList;

    public BusAdapter(Activity context, List<Bus> busList) {
        super(context, R.layout.single_bus, busList);
        this.context = context;
        this.busList = busList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.single_bus, null, true);


        TextView textViewTravelsName = (TextView) listViewItem.findViewById(R.id.busNameList);
        TextView textViewBusNumber = (TextView) listViewItem.findViewById(R.id.busNoList);
        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.fromList);
        TextView textViewFrom = (TextView) listViewItem.findViewById(R.id.toList);
        TextView textViewTo = (TextView) listViewItem.findViewById(R.id.telephoneList);
       // TextView textViewCondition = (TextView) listViewItem.findViewById(R.id.text_view_condition);


        Bus bus = busList.get(position);

        textViewTravelsName.setText(bus.getBusName());
        textViewBusNumber.setText("Bus Number       : "+bus.getBusNo());
        textViewDate.setText("Journey From      : "+bus.getbFrom());
        textViewFrom.setText("Bus To            : "+bus.getbTo());
        textViewTo.setText("Bus Telephone                : "+bus.getbTelephone());
       // textViewCondition.setText("Bus Condition    : "+bus.getBusCondition());

        return listViewItem;
    }
}
