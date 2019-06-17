package com.example.laboratorioars2019;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterItem extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Frase> items;

    public AdapterItem (Activity activity, ArrayList<Frase> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Frase> frases) {
        for (int i = 0; i < frases.size(); i++) {
            items.add(frases.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Log.d("Adapter","Se llamo: " + position);
        //Log.d("Adapter", items.get(position).toString());
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_frase, null);
        }

        Frase dir = items.get(position);

        TextView title = (TextView) v.findViewById(R.id.category);
        title.setText(dir.getFrase());

        TextView description = (TextView) v.findViewById(R.id.texto);
        description.setText(dir.getAutor());

        RatingBar rating = (RatingBar) v.findViewById(R.id.ratingBar);
        if(dir.getCantVotos() != 0){
            rating.setRating((float) (dir.getSumaActual()/dir.getCantVotos()));
        }else{
            rating.setRating(0);
        };


        return v;
    }

}
