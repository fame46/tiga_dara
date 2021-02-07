package com.example.myapplication.Pelayan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.example.myapplication.Adapter.AdapterDDMeja;
import com.example.myapplication.Adapter.AdapterMenu;
import com.example.myapplication.Admin.HomeAdmin;
import com.example.myapplication.Model.MejaModel;
import com.example.myapplication.Model.MenuModel;
import com.example.myapplication.Model.PesananModel;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Preferences;
import com.example.myapplication.Utils.RecyclerItemClickListener;
import com.example.myapplication.Utils.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    Spinner spinner;
    EditText etAtasnama;
    TextView tvTotalBayar, tvNotrans;
    Button btnKonfirmasi;

    List<PesananModel> pesananModelList;
    List<MejaModel> itemMeja;
    private ArrayList<String> arrayList = new ArrayList<>();

    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private DividerItemDecoration did;
    private RecyclerView.Adapter adapter;

    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        preferences = new Preferences(this);

        recyclerView = findViewById(R.id.recyclerViewCart);
        spinner = findViewById(R.id.spinner);
        etAtasnama = findViewById(R.id.etAtasNama);
        tvTotalBayar = findViewById(R.id.tvTotalBayar);
        btnKonfirmasi = findViewById(R.id.btnKonfirmasi);
//        tvNotrans = findViewById(R.id.notrans);

        pesananModelList = new ArrayList<>();
        adapter = new AdapterCart(pesananModelList);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        did = new DividerItemDecoration(recyclerView.getContext(), llm.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(did);
        recyclerView.setAdapter(adapter);

        itemMeja = new ArrayList<>();

//        getNotrans();
        loadData();
        getData();
        sum();

        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etAtasnama.length() == 0){
                    etAtasnama.setError("isi atasnama !");
                }else {
//                    Toast.makeText(CartActivity.this, "yuhu", Toast.LENGTH_SHORT).show();
                    getNotrans();
                }
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(CartActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final PesananModel pesananModel = pesananModelList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Hapus Pesanan")
                        .setMessage("Apakah anda yakin menghapus "+pesananModel.getNama_menu()+" dari keranjang ?")
                        .setCancelable(false)
                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(getApplicationContext(), ""+String.valueOf(pesananModel.getId()), Toast.LENGTH_SHORT).show();
//                                userModelList.clear();
                                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URLs.DELETECART,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                Log.e("response", response);
                                                try {
                                                    JSONObject jsonObject1 = new JSONObject(response);
                                                    String success1 = jsonObject1.getString("value");

                                                    if (success1.equals("1")) {
                                                        Toast.makeText(getApplicationContext(), "Hapus data berhasil", Toast.LENGTH_SHORT).show();
                                                        loadData();

                                                    }

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                    Toast.makeText(getApplicationContext(), "Hapus data Gagal " + e.toString(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(getApplicationContext(), "Hapus data Gagal " + error.toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        }) {
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> params = new HashMap<>();
                                        params.put("id", String.valueOf(pesananModel.getId()));
                                        return params;
                                    }
                                };
                                RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
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
        }));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomePelayan.class));
    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        pesananModelList.clear();
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, URLs.GETCART+"?pelayan="+preferences.getSPNama(), new Response.Listener<String>() {
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

    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.READMEJA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response",response);
                        try {
                            //Parsing the fetched Json String to JSON Object
                            JSONObject j = new JSONObject(response);

                            int success = j.getInt("success");
                            if (success == 1) {
                                JSONArray jsonArray = j.getJSONArray("result");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    try {
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        MejaModel mejaModel = new MejaModel();
                                        mejaModel.setId(object.getInt("id"));
                                        mejaModel.setMeja(object.getInt("meja"));
                                        itemMeja.add(mejaModel);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                for (int lol = 0; lol<itemMeja.size(); lol++){
                                    arrayList.add(String.valueOf(itemMeja.get(lol).getMeja()));
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        spinner.setAdapter(new ArrayAdapter<String>(CartActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayList));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    public void sum(){
        StringRequest jsonObjectRequest = new StringRequest(com.android.volley.Request.Method.GET,URLs.TOTALBAYAR+"?pelayan="+preferences.getSPNama(),
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        Log.e("Aaaa",response);
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            int success = jsonObject.getInt("success");
                            String result = jsonObject.getString("result");

                            tvTotalBayar.setText("Rp. "+result+",-");

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }

    public void getNotrans(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        final String dateTime3 = simpleDateFormat2.format(calendar.getTime());
        final String dateTime = simpleDateFormat.format(calendar.getTime());

        StringRequest jsonObjectRequest = new StringRequest(com.android.volley.Request.Method.GET,URLs.GETNOTRANS+"?create_on="+dateTime3,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        Log.e("Aaaa",response);
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("result");
                            if(array.length() == 0){
//                                Toast.makeText(CartActivity.this, ""+dateTime+"0001", Toast.LENGTH_SHORT).show();
                                String no = dateTime+"0001";
                                int kd_meja = spinner.getSelectedItemPosition();
                                konfirmasi(String.valueOf(itemMeja.get(kd_meja).getMeja()),no);
                                startActivity(new Intent(getApplicationContext(),HomePelayan.class));
                            }else {
                                JSONObject data = array.getJSONObject(0);
                                int result = data.getInt("notrans");
//                            int kontol = Integer.parseInt(result);
                                int notrans100 = result  + 1;
//                            Toast.makeText(CartActivity.this, ""+String.valueOf(notrans100), Toast.LENGTH_SHORT).show();
//                            tvNotrans.setText(String.valueOf(notrans100));
                                int kd_meja = spinner.getSelectedItemPosition();
//                    Toast.makeText(CartActivity.this, ""+itemMeja.get(kd_meja).getMeja(), Toast.LENGTH_SHORT).show();
                                konfirmasi(String.valueOf(itemMeja.get(kd_meja).getMeja()),String.valueOf(notrans100));
                                startActivity(new Intent(getApplicationContext(),HomePelayan.class));

                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }

    private void konfirmasi(final String a, final String b){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.KONFIRMASI,
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
                params.put("pelayan", preferences.getSPNama());
                params.put("atasnama", etAtasnama.getText().toString());
                params.put("notrans", b);
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