package com.reni.myapplication.users;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ornach.nobobutton.NoboButton;
import com.reni.myapplication.R;
import com.reni.myapplication.server.BaseURL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegistrasiActivity extends AppCompatActivity {

    Button btnPindah;
    NoboButton btnRegistrasi;
    EditText edtUsername, edtNamaLengkap, edtEmail, edtPassword;
    ProgressDialog pDialog;

    private RequestQueue mRequestQueue;
    private Object NoboButton;
    private Object userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        edtUsername =(EditText) findViewById(R.id.edtUsername);
        edtNamaLengkap =(EditText) findViewById(R.id.edtNamalengkap);
        edtEmail =(EditText) findViewById(R.id.edtEmail);
        edtPassword =(EditText) findViewById(R.id.edtPassword);

        btnPindah= (Button) findViewById(R.id.btnPindah);
        btnRegistrasi = (NoboButton) findViewById(R.id.btnRegistrasi);


        btnPindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrasiActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String struserName = edtUsername.getText().toString();
                String strnamaLengkap = edtNamaLengkap.getText().toString();
                String stremail = edtEmail.getText().toString();
                String strpassword = edtPassword.getText().toString();

                if (struserName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (strnamaLengkap.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nama  Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (stremail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (strpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password  Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else {
                    registrasi(struserName,strnamaLengkap,stremail,strpassword);
                }
            }
        });
            }

            public void registrasi(String userName, String namaLengkap, String email, String password) {


// Post params to be sent to the server
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("userName", userName);
                params.put("namaLengkap", namaLengkap);
                params.put("email", email);
                params.put("role", "2");
                params.put("password", password);



                JsonObjectRequest req = new JsonObjectRequest(BaseURL.register, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    String strMsg = response.getString("msg");
                                    boolean status = response.getBoolean("error");

                                    if (status == false) {
                                        Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(RegistrasiActivity.this, LoginActivity.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                    }
                                    Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());

                    }
                });

// add the request object to the queue to be executed
                mRequestQueue.add(req);


            }

            @Override
            public void onBackPressed() {
                Intent i = new Intent(RegistrasiActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }