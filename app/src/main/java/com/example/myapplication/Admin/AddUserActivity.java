package com.example.myapplication.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Preferences;
import com.example.myapplication.Utils.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddUserActivity extends AppCompatActivity {

    Spinner spinner;
    EditText etusername, etpassword;
    Button save;

    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        preferences = new Preferences(this);

        spinner = findViewById(R.id.spinner);
        etusername = findViewById(R.id.etUsername);
        etpassword = findViewById(R.id.etPassword);
        save = findViewById(R.id.btnSave);

        final String[] choice = new String[]{
                "Kasir", "Pelayan", "Dapur"
        };
        final ArrayAdapter<String> spinnerArray2 = new ArrayAdapter<String>(
                this,R.layout.list_textview,choice
        );
        spinnerArray2.setDropDownViewResource(R.layout.list_textview);
        spinner.setAdapter(spinnerArray2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int choiceI = spinner.getSelectedItemPosition();
                simpandata(choiceI+1);
            }
        });

    }

    private void simpandata(final int a){

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, URLs.CREATEUSER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response", response);
                        try {
                            JSONObject jsonObject2 = new JSONObject(response);
                            String success = jsonObject2.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Create User Success !", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),HomeAdmin.class));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Failed to Upload data " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Failed to Upload data " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", etusername.getText().toString());
                params.put("password", etpassword.getText().toString());
                params.put("level", String.valueOf(a));
                params.put("created_by", preferences.getSPNama());

                return params;
            }
        };
        RequestQueue requestQueue2 = Volley.newRequestQueue(getApplicationContext());
        requestQueue2.add(stringRequest2);
    }
}