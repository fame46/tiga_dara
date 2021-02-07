package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Admin.HomeAdmin;
import com.example.myapplication.Dapur.HomeDapur;
import com.example.myapplication.Kasir.HomeKasir;
import com.example.myapplication.Pelayan.HomePelayan;
import com.example.myapplication.Utils.Preferences;
import com.example.myapplication.Utils.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;

    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = new Preferences(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekLogin(etUsername.getText().toString(),etPassword.getText().toString());
            }
        });
    }

    private void cekLogin(final String usernameS, final  String passwordS){
        if (TextUtils.isEmpty(usernameS)) {
            etUsername.setError("Masukkan Username");
            etUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(passwordS)) {
            etPassword.setError("Masukkan Password");
            etPassword.requestFocus();
            return;
        }

        StringRequest arrayRequest = new StringRequest(Request.Method.POST, URLs.LOGIN,new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("volley", "response : " + response.toString());
                try {
                    JSONObject object = new JSONObject(response);

                    if (object.getInt("success")==1) {
//                        Toast.makeText(getApplicationContext(),"Anda Masuk Sebagai "+ object.getString("user_id"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        //storing the user in shared preferences
                        preferences.saveSPString(Preferences.SP_NAMA,  object.getString("username"));
                        // Shared Pref ini berfungsi untuk menjadi trigger session login
                        preferences.saveSPBoolean(Preferences.SP_SUDAH_LOGIN, true);
                        if(object.getInt("level")==0){
                            startActivity(new Intent(getApplicationContext(), HomeAdmin.class));
                        }else if(object.getInt("level")==1){
                            startActivity(new Intent(getApplicationContext(), HomeKasir.class));
                        }else if(object.getInt("level")==2){
                            startActivity(new Intent(getApplicationContext(), HomePelayan.class));
                        }else {
                            startActivity(new Intent(getApplicationContext(), HomeDapur.class));
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "username atau password Salah !", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "login error : "+e, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("volley", "error : " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", usernameS);
                params.put("password", passwordS);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(arrayRequest);
    }

    @Override
    public void onBackPressed() {

    }
}