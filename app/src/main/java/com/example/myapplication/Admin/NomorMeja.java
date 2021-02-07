package com.example.myapplication.Admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.myapplication.Adapter.AdapterMeja;
import com.example.myapplication.Adapter.AdapterUser;
import com.example.myapplication.Model.MejaModel;
import com.example.myapplication.Model.UserModel;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Preferences;
import com.example.myapplication.Utils.URLs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NomorMeja extends Fragment implements AdapterMeja.HapusListener{

    FloatingActionButton fab;

    Preferences preferences;

    List<MejaModel> mejaModelList;

    RecyclerView recyclerView;
    LinearLayoutManager llm;
    DividerItemDecoration did;
    RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nomor_meja, container, false);

        fab = v.findViewById(R.id.fab);
        recyclerView = v.findViewById(R.id.recyclermeja);
        preferences = new Preferences(getContext());

        mejaModelList = new ArrayList<>();

        adapter = new AdapterMeja(getContext(), mejaModelList, this);
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        did = new DividerItemDecoration(recyclerView.getContext(), llm.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(did);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.custom_dialog,null);
                final EditText txt_inputText = (EditText)mView.findViewById(R.id.etNomormeja);
                Button btn = (Button)mView.findViewById(R.id.btnSave);
                alert.setView(mView);
                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        simpandata(txt_inputText.getText().toString());
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        loadPengadaanData();

        return v;
    }

    private void simpandata(final String a){

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, URLs.CREATEMEJA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response", response);
                        try {
                            JSONObject jsonObject2 = new JSONObject(response);
                            String success = jsonObject2.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(getContext(), "Create Meja Success !", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(getApplicationContext(),HomeAdmin.class));
                                loadPengadaanData();

                            }else if (success.equals("-1")) {
                                Toast.makeText(getContext(), "meja is already exist !", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(getApplicationContext(),HomeAdmin.class));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Failed to Upload data " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Failed to Upload data " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("meja", a);
                params.put("created_by", preferences.getSPNama());

                return params;
            }
        };
        RequestQueue requestQueue2 = Volley.newRequestQueue(getContext());
        requestQueue2.add(stringRequest2);
    }

    @Override
    public void hapusId(String id) {
        hapus(id);
    }

    private void hapus(final String id){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext());
        builder.setTitle("Hapus Akun")
                .setMessage("Apakah anda yakin ?")
                .setCancelable(false)
                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();
                        mejaModelList.clear();
                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URLs.DELETEMEJA,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.e("response", response);
                                        try {
                                            JSONObject jsonObject1 = new JSONObject(response);
                                            String success1 = jsonObject1.getString("value");

                                            if (success1.equals("1")) {
                                                Toast.makeText(getContext(), "Hapus data berhasil", Toast.LENGTH_SHORT).show();
                                                loadPengadaanData();

                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(getContext(), "Hapus data Gagal " + e.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getContext(), "Hapus data Gagal " + error.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("id", id);
                                return params;
                            }
                        };
                        RequestQueue requestQueue1 = Volley.newRequestQueue(getContext());
                        requestQueue1.add(stringRequest1);

                    }
                })
                .setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadPengadaanData(){

        mejaModelList.clear();
        StringRequest jsonObjectRequest = new StringRequest(com.android.volley.Request.Method.GET, URLs.READMEJA, new Response.Listener<String>() {
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
                                MejaModel mejaModel = new MejaModel();
                                mejaModel.setId(object.getInt("id"));
                                mejaModel.setMeja(object.getInt("meja"));
                                mejaModelList.add(mejaModel);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }
}