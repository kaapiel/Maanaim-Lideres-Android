package br.com.igreja.cellapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.igreja.cellapp.R;

/**
 * Created by Inmetrics on 18/08/2016.
 */
public class SpinnerAdapter extends BaseAdapter {

    ArrayList<Integer> colors;
    ArrayList<String> regioes;
    Context context;

    public SpinnerAdapter(Context context) {
        this.context = context;

        colors = new ArrayList<Integer>();
        regioes = new ArrayList<String>();
        int retrieve[] = context.getResources().getIntArray(R.array.androidcolors);
        String retrieve2[] = context.getResources().getStringArray(R.array.array_regioes);

        for (int re : retrieve) {
            colors.add(re);
        }

        for (String re : retrieve2) {
            regioes.add(re);
        }
    }

    @Override
    public int getCount() {
        return colors.size();
    }

    @Override
    public Object getItem(int arg0) {
        return colors.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        TextView txv = (TextView) view.findViewById(android.R.id.text1);
        txv.setBackgroundColor(colors.get(pos));
        txv.setTextSize(25f);
        txv.setText(regioes.get(pos));
        return view;
    }
}
