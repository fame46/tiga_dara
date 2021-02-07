package com.example.myapplication.Pelayan;

import android.app.ProgressDialog;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplication.Adapter.AdapterMenu;
import com.example.myapplication.Model.MenuModel;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Preferences;
import com.example.myapplication.Utils.RecyclerItemClickListener;
import com.example.myapplication.Utils.URLs;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaketFragment extends Fragment {

    ImageView imageView, btnLeft, btnRight;
    TextView tvnama_menu, tvhargaMenu, tvjumlah, tvtotalHarga, tvKeterangan, btnMin, btnPlus;
    Button btnPesan;

    List<MenuModel> menuModelList;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private DividerItemDecoration did;
    private RecyclerView.Adapter adapter;

    private View BottomSheet;
    private BottomSheetBehavior SheetBehavior;

    Preferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_paket, container, false);

        preferences = new Preferences(getContext());

        recyclerView = v.findViewById(R.id.recyclerViewPelayanPaket);
        imageView = v.findViewById(R.id.imageViewBottomPaket);
        btnLeft = v.findViewById(R.id.arrowLeftPaket);
        btnRight = v.findViewById(R.id.arrorRightPaket);
        tvnama_menu = v.findViewById(R.id.tvNamaMenuBottomPaket);
        tvhargaMenu = v.findViewById(R.id.tvHargaMenuBottomPaket);
        tvjumlah = v.findViewById(R.id.tvJumlahPaket);
        tvtotalHarga = v.findViewById(R.id.tvJumlahhargaPaket);
        tvKeterangan = v.findViewById(R.id.tvKeteranganPaket);
        btnMin = v.findViewById(R.id.btnMinPaket);
        btnPlus = v.findViewById(R.id.btnPlusPaket);
        btnPesan = v.findViewById(R.id.btnPesanPaket);

        BottomSheet = v.findViewById(R.id.bottomSheetPaket);
        SheetBehavior = BottomSheetBehavior.from(BottomSheet);
        SheetBehavior.setHideable(true);

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
                final MenuModel menuModel = menuModelList.get(position);
                if(SheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                    SheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    Glide.with(getActivity()).load(menuModel.getGambar()).into(imageView);
                    tvnama_menu.setText(menuModel.getNama_menu());
                    tvhargaMenu.setText("Rp. "+String.valueOf(menuModel.getHarga())+" ,-");
                    tvtotalHarga.setText(String.valueOf(menuModel.getHarga()*Integer.parseInt(tvjumlah.getText().toString())));
                    btnMin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(Integer.parseInt(tvjumlah.getText().toString()) == 0){

                            }else {
                                int jumlah = Integer.parseInt(tvjumlah.getText().toString()) - 1;
                                int jumlahHarga = menuModel.getHarga() * jumlah;
                                tvjumlah.setText(String.valueOf(jumlah));
                                tvtotalHarga.setText(String.valueOf(jumlahHarga));
                            }
                        }
                    });

                    btnPlus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int jumlah = Integer.parseInt(tvjumlah.getText().toString()) + 1;
                            int jumlahHarga = menuModel.getHarga() * jumlah;
                            tvjumlah.setText(String.valueOf(jumlah));
                            tvtotalHarga.setText(String.valueOf(jumlahHarga));
                        }
                    });

                    btnRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            btnLeft.setVisibility(View.VISIBLE);
                            tvKeterangan.setText("di bungkus");
                            btnRight.setVisibility(View.GONE);
                        }
                    });

                    btnLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            btnRight.setVisibility(View.VISIBLE);
                            tvKeterangan.setText("makan di tempat");
                            btnLeft.setVisibility(View.GONE);
                        }
                    });

                    btnPesan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cart(menuModel.getHarga());
//                            Toast.makeText(getContext(), ""+String.valueOf(menuModel.getHarga()), Toast.LENGTH_SHORT).show();
                            SheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }
                    });
                }else {
                    SheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    Glide.with(getActivity()).load(menuModel.getGambar()).into(imageView);
                    tvnama_menu.setText(menuModel.getNama_menu());
                    tvhargaMenu.setText("Rp. "+String.valueOf(menuModel.getHarga())+" ,-");
                    tvtotalHarga.setText(String.valueOf(menuModel.getHarga()*Integer.parseInt(tvjumlah.getText().toString())));
                    btnMin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(Integer.parseInt(tvjumlah.getText().toString()) == 0){

                            }else {
                                int jumlah = Integer.parseInt(tvjumlah.getText().toString()) - 1;
                                tvjumlah.setText(jumlah);
                            }
                        }
                    });

                    btnPlus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int jumlah = Integer.parseInt(tvjumlah.getText().toString()) + 1;
                            tvjumlah.setText(jumlah);
                        }
                    });

                    btnRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            btnLeft.setVisibility(View.VISIBLE);
                            tvKeterangan.setText("di bungkus");
                            btnRight.setVisibility(View.GONE);
                        }
                    });

                    btnLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            btnRight.setVisibility(View.VISIBLE);
                            tvKeterangan.setText("makan di tempat");
                            btnLeft.setVisibility(View.GONE);
                        }
                    });

                    btnPesan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cart(menuModel.getHarga());
//                            Toast.makeText(getContext(), ""+String.valueOf(menuModel.getHarga()), Toast.LENGTH_SHORT).show();
                            SheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }
                    });
                }
            }
        }));

        return v;
    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        menuModelList.clear();
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, URLs.GETPAKET, new Response.Listener<String>() {
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

    private void cart(final int a){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.CREATEPESANAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(getActivity(), "Sending to Cart", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Failed to Sending order "+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Failed to Sending order "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("nama_menu", tvnama_menu.getText().toString());
                params.put("harga_menu", String.valueOf(a));
                params.put("jumlah",tvjumlah.getText().toString());
                params.put("keterangan", tvKeterangan.getText().toString());
                params.put("pelayan",preferences.getSPNama());
                params.put("subTotal",tvtotalHarga.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}