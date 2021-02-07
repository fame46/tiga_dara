package com.example.myapplication.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.AdapterUser;
import com.example.myapplication.Model.UserModel;
import com.example.myapplication.R;
import com.example.myapplication.Utils.URLs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends Fragment implements AdapterUser.HapusListener{

    FloatingActionButton fab;

    List<UserModel> userModelList;

    RecyclerView recyclerView;
    LinearLayoutManager llm;
    DividerItemDecoration did;
    RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        fab = v.findViewById(R.id.fab);
        recyclerView = v.findViewById(R.id.recycler);

        userModelList = new ArrayList<>();

        adapter = new AdapterUser(getContext(), userModelList, this);
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
                startActivity(new Intent(getContext(),AddUserActivity.class));
            }
        });

        loadPengadaanData();

        return v;
    }

    @Override
    public void hapusId(String id) {
        hapus(id);
    }

    private void hapus(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Hapus Akun")
                .setMessage("Apakah anda yakin ?")
                .setCancelable(false)
                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();
                        userModelList.clear();
                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URLs.DELETEUSER,
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
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadPengadaanData(){

        userModelList.clear();
        StringRequest jsonObjectRequest = new StringRequest(com.android.volley.Request.Method.GET, URLs.READUSER, new Response.Listener<String>() {
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
                                UserModel userModel = new UserModel();
                                userModel.setId(object.getInt("id"));
                                userModel.setUsername(object.getString("username"));
                                userModelList.add(userModel);
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