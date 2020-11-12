package com.reni.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.reni.myapplication.R;
import com.reni.myapplication.model.ModelMotor;
import com.reni.myapplication.server.BaseURL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMotor extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelMotor> item;

    public AdapterMotor(Activity activity, List<ModelMotor> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_motor, null);


        TextView namamerk = (TextView) convertView.findViewById(R.id.txtnamamerk);
        TextView jenismotor     = (TextView) convertView.findViewById(R.id.txtjenismotor);
        TextView warna          = (TextView) convertView.findViewById(R.id.txtwarna);
        TextView tahunmotor         = (TextView) convertView.findViewById(R.id.txttahunmotor);
        TextView hargamotor         = (TextView) convertView.findViewById(R.id.txthargamotor);
        ImageView gambarMotor         = (ImageView) convertView.findViewById(R.id.gambarMotor);

        namamerk.setText(item.get(position).getNamamerk());
        jenismotor.setText(item.get(position).getJenismotor());
        warna.setText(item.get(position).getWarna());
        tahunmotor.setText("Rp." + item.get(position).getHargamotor());
        hargamotor.setText(item.get(position).getTahunmotor());

        Picasso.get().load(BaseURL.baseUrl + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(gambarMotor);
        return convertView;
    }
}
