package com.reni.myapplication.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.reni.myapplication.R;
import com.reni.myapplication.server.BaseURL;
import com.squareup.picasso.Picasso;

public class DetailMotor extends AppCompatActivity {

    EditText edtkodemotor, edtmerkmotor, edtjenismotor, edtwarna, edttahunmotor, edthargamotor;
    ImageView imgGambarMotor;

    String strkodemotor, strnamamerk, strjenismotor, strwarna, strtahunmotor, strhargamotor, strGambar, _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_motor);

        edtkodemotor = (EditText) findViewById(R.id.edtkodemotor);
        edtmerkmotor = (EditText) findViewById(R.id.edtmerkmotor);
        edtjenismotor = (EditText) findViewById(R.id.edtjenismotor);
        edtwarna = (EditText) findViewById(R.id.edtwarna);
        edttahunmotor = (EditText) findViewById(R.id.edttahunmotor);
        edthargamotor = (EditText) findViewById(R.id.edthargamotor);

        imgGambarMotor = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strkodemotor = i.getStringExtra("kodemotor");
        strnamamerk = i.getStringExtra("namamerk");
        strjenismotor = i.getStringExtra("jenismotor");
        strwarna = i.getStringExtra("warna");
        strtahunmotor = i.getStringExtra("tahunmoto");
        strhargamotor = i.getStringExtra("hargamotor");
        strGambar = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        edtkodemotor.setText(strkodemotor);
        edtmerkmotor.setText(strnamamerk);
        edtjenismotor.setText(strjenismotor);
        edtwarna.setText(strwarna);
        edttahunmotor.setText(strtahunmotor);
        edthargamotor.setText(strhargamotor);
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + strGambar)
                .into(imgGambarMotor);
    }
}