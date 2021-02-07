package com.example.myapplication.Admin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

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
import com.example.myapplication.Adapter.AdapterMenu;
import com.example.myapplication.Model.MenuModel;
import com.example.myapplication.R;
import com.example.myapplication.Utils.RecyclerItemClickListener;
import com.example.myapplication.Utils.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LihatMinumanFragment extends Fragment {

    List<MenuModel> menuModelList;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private DividerItemDecoration did;
    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lihat_minuman, container, false);

        recyclerView = v.findViewById(R.id.recyclerViewminuman);
        menuModelList = new ArrayList<>();
        adapter = new AdapterMenu(getContext(),menuModelList);
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        did = new DividerItemDecoration(recyclerView.getContext(), llm.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(did);
        recyclerView.setAdapter(adapter);

        loadData();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                final MenuModel menuModel =menuModelList.get(position);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Hapus Menu "+menuModel.getNama_menu()+ " ?");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.DELETEMENU, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.e("Aaaa",response);
                                        try{
                                            JSONObject jsonObject = new JSONObject(response);
                                            String value = jsonObject.getString("value");
                                            if(value.equals("1")){
                                                Toast.makeText(getContext(), "Berhasil Menghapus !", Toast.LENGTH_SHORT).show();
                                                loadData();
                                            }
                                        }catch (JSONException e){
                                            e.printStackTrace();
                                            Toast.makeText(getActivity(),"Failed to Delete !"+e.toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener(){
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.e("Volley", error.toString());
                                    }
                                })
                                {
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String,String> params = new HashMap<>();
                                        params.put("id", String.valueOf(menuModel.getId()));
                                        return params;
                                    }
                                };
                                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                                requestQueue.add(stringRequest);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }));

        return v;
    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        menuModelList.clear();
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, URLs.GETMINUMAN, new Response.Listener<String>() {
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
                                MenuModel menuModel = new MenuModel();
                                menuModel.setId(object.getInt("id"));
                                menuModel.setNama_menu(object.getString("nama_menu"));
                                menuModel.setHarga(object.getInt("harga"));
                                menuModel.setJenis(object.getString("jenis"));
                                menuModel.setCreate_on(object.getString("create_on"));
                                menuModel.setCreate_by(object.getString("create_by"));
                                menuModel.setModified_on(object.getString("modified_on"));
                                menuModel.setModified_by(object.getString("modified_by"));
                                menuModel.setGambar(object.getString("gambar"));
                                menuModelList.add(menuModel);
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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }
}