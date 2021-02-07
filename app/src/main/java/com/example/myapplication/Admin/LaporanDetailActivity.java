package com.example.myapplication.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.AdapterCart;
import com.example.myapplication.Model.PesananModel;
import com.example.myapplication.R;
import com.example.myapplication.Utils.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LaporanDetailActivity extends AppCompatActivity {

    TextView tvAtasnam, tvKasir, tvTanggal;

    List<PesananModel> pesananModelList;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private DividerItemDecoration did;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_detail);

        tvAtasnam = findViewById(R.id.tvAtasNamaLaporanDetail);
        tvKasir = findViewById(R.id.tv13);
        tvTanggal = findViewById(R.id.tv11);
        recyclerView = findViewById(R.id.recyclerViewLaporanDetail);

        tvAtasnam.setText(getIntent().getStringExtra("atasnama"));
        tvKasir.setText(getIntent().getStringExtra("kasir"));
        tvTanggal.setText(getIntent().getStringExtra("create_on"));

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
    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        pesananModelList.clear();
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, URLs.GETLAPORANDETAIL+"?atasnama="+getIntent().getStringExtra("atasnama")+
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
}