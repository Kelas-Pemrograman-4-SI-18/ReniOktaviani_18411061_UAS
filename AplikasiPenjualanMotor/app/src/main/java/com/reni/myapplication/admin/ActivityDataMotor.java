package com.reni.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.reni.myapplication.R;
import com.reni.myapplication.adapter.AdapterMotor;
import com.reni.myapplication.model.ModelMotor;
import com.reni.myapplication.server.BaseURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityDataMotor extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterMotor adapter;
    ListView list;

    ArrayList<ModelMotor> newsList = new ArrayList<ModelMotor>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_motor);

        getSupportActionBar().setTitle("Data Motor");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterMotor(ActivityDataMotor.this, newsList);
        list.setAdapter(adapter);
        getAllMotor();

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ActivityDataMotor.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }

    private void getAllMotor() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataMotor, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data motor = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelMotor motor = new ModelMotor();
                                    final String _id = jsonObject.getString("_id");
                                    final String namamerk = jsonObject.getString("namamerk");
                                    final String kodemotor = jsonObject.getString("kodemotor");
                                    final String jenismotor = jsonObject.getString("jenismotor");
                                    final String warna = jsonObject.getString("warna");
                                    final String tahunmotor = jsonObject.getString("tahunmotor");
                                    final String hargamotor = jsonObject.getString("hargamotor");
                                    final String gambar = jsonObject.getString("gambar");
                                    motor.setKodemotor(kodemotor);
                                    motor.setNamamerk(namamerk);
                                    motor.setJenismotor(jenismotor);
                                    motor.setWarna(warna);
                                    motor.setTahunmotor(tahunmotor);
                                    motor.setHargamotor(hargamotor);
                                    motor.setGambar(gambar);
                                    motor.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(ActivityDataMotor.this, EditMotorDanHapusActivity.class);
                                            a.putExtra("kodemotor", newsList.get(position).getKodemotor());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("namamerk", newsList.get(position).getNamamerk());
                                            a.putExtra("jenismotor", newsList.get(position).getJenismotor());
                                            a.putExtra("warna", newsList.get(position).getWarna());
                                            a.putExtra("tahunmotor", newsList.get(position).getTahunmotor());
                                            a.putExtra("hargamotor", newsList.get(position).getHargamotor());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(motor);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}