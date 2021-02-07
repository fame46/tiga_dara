package com.example.myapplication.Pelayan;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.AdapterKasir;
import com.example.myapplication.Kasir.HomeKasir;
import com.example.myapplication.Model.PesananModel;
import com.example.myapplication.R;
import com.example.myapplication.Utils.RecyclerItemClickListener;
import com.example.myapplication.Utils.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelesaiFragment extends Fragment {

    Button btnSelesai;

    List<PesananModel> pesananModelList;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private DividerItemDecoration did;
    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_selesai, container, false);

        recyclerView = v.findViewById(R.id.recyclerViewSelesai);
        btnSelesai = v.findViewById(R.id.btnRefreshPesananSelesai);

        pesananModelList = new ArrayList<>();
        adapter = new AdapterKasir(pesananModelList);
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        did = new DividerItemDecoration(recyclerView.getContext(), llm.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(did);
        recyclerView.setAdapter(adapter);

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        loadData();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PesananModel pesananModel = pesananModelList.get(position);
                Intent intent = new Intent(getContext(), SelesaiDetailActivity.class);
                intent.putExtra("atasnama", pesananModel.getAtasnama());
                intent.putExtra("nomeja", String.valueOf(pesananModel.getNomeja()));
                startActivity(intent);
            }
        }));

        return v;
    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        pesananModelList.clear();
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, URLs.GETANTAR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Aaaa",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    int success = jsonObject.getInt("success");
                    if (success == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("result");
                        Toast.makeText(getContext(), "List telah diperbarui", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject object = jsonArray.getJSONObject(i);
                                PesananModel pesananModel = new PesananModel();
                                pesananModel.setAtasnama(object.getString("atasnama"));
                                pesananModel.setNomeja(object.getInt("nomeja"));
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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }
}