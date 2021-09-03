package com.example.bracesteeth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ThreeColumn_ListAdapter extends ArrayAdapter<Braces> {

    private LayoutInflater mInflater;
    private ArrayList<Braces> braces;
    private int mViewResourceId;

    public ThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<Braces> baraces) {
        super(context, textViewResourceId, baraces);
        this.braces = baraces;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Braces brace = braces.get(position);

        if (brace != null) {
            TextView _ID = (TextView) convertView.findViewById(R.id.textId);
            TextView _DATE = (TextView) convertView.findViewById(R.id.textDate);
            TextView _PRIX = (TextView) convertView.findViewById(R.id.textPrix);
            if (_ID != null) {
                _ID.setText(brace.getId());
            }
            if (_DATE != null) {
                _DATE.setText((brace.getDate()));
            }
            if (_PRIX != null) {
                _PRIX.setText((brace.getPrix()));
            }
        }

        return convertView;
    }
}