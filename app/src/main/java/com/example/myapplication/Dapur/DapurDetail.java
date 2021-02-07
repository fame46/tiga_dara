package com.example.myapplication.Dapur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.AdapterCart;
import com.example.myapplication.Model.PesananModel;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Preferences;
import com.example.myapplication.Utils.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DapurDetail extends AppCompatActivity {

    TextView tvAtasnama;
    Button btnSelesai;

    List<PesananModel> pesananModelList;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private DividerItemDecoration did;
    private RecyclerView.Adapter adapter;

    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dapur_detail);

        preferences = new Preferences(this);

        tvAtasnama = findViewById(R.id.tvAtasNamaBayarDapur);
        recyclerView = findViewById(R.id.recyclerViewDapur);
        btnSelesai = findViewById(R.id.btnSelesai);

        tvAtasnama.setText(getIntent().getStringExtra("atasnama"));

        pesananModelList = new ArrayList<>();
        adapter = new AdapterCart(pesananModelList);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        did = new DividerItemDecoration(recyclerView.getContext(), llm.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(did);
        recyclerView.setAdapter(adapter);

        loadData();

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selesai(getIntent().getStringExtra("nomeja"));
                startActivity(new Intent(getApplicationContext(), HomeDapur.class));
            }
        });
    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        pesananModelList.clear();
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, URLs.GETKEMBALIAN+"?atasnama="+getIntent().getStringExtra("atasnama")+
                "&nomeja="+getIntent().getStringExtra("nomeja"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Aaaa",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    int success = jsonObject.getInt("success");
                    if (success == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("result");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject object = jsonArray.getJSONObject(i);
                                PesananModel pesananModel = new PesananModel();
                                pesananModel.setId(object.getInt("id"));
                                pesananModel.setNama_menu(object.getString("nama_menu"));
                                pesananModel.setHarga_menu(object.getInt("harga_menu"));
                                pesananModel.setJumlah(object.getInt("jumlah"));
                                pesananModel.setKeterangan(object.getString("keterangan"));
                                pesananModelList.add(pesananModel);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    private void selesai(final String a){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.SELESAI,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Log.e("response",response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Gagal melakukan pesanan "+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                String id = "";
                for(int i=0;i<pesananModelList.size();i++){
                    id += String.valueOf(pesananModelList.get(i).getId())+",";
                }
                params.put("dapur", preferences.getSPNama());
                params.put("atasnama", tvAtasnama.getText().toString());
                params.put("nomeja", a);
                params.put("count",String.valueOf(pesananModelList.size()));
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}