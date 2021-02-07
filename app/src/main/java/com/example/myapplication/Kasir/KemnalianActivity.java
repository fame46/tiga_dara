package com.example.myapplication.Kasir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dantsu.escposprinter.connection.DeviceConnection;
import com.dantsu.escposprinter.textparser.PrinterTextParserImg;
import com.example.myapplication.Adapter.AdapterCart;
import com.example.myapplication.Model.PesananModel;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Preferences;
import com.example.myapplication.Utils.URLs;
import com.example.myapplication.async.AsyncBluetoothEscPosPrint;
import com.example.myapplication.async.AsyncEscPosPrinter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KemnalianActivity extends AppCompatActivity {

    TextView tvuangKembalian, tvAtasnama;

    Button btnreprint, btnOke;

    List<PesananModel> pesananModelList;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private DividerItemDecoration did;
    private RecyclerView.Adapter adapter;

    List<String> where = new ArrayList<String>();

    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kemnalian);

        preferences = new Preferences(this);

        tvuangKembalian = findViewById(R.id.tvUangKembalian);
        tvAtasnama = findViewById(R.id.tvAtasNamaKembalian);
        recyclerView = findViewById(R.id.recyclerViewKembalian);
        btnreprint = findViewById(R.id.btnReprint);
        btnOke = findViewById(R.id.btnOke);


        tvuangKembalian.setText("Rp. "+getIntent().getStringExtra("kembalian")+",-");
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

        btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),HomeKasir.class));
            }
        });

        btnreprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printBluetooth();
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
                                pesananModel.setSubTotal(object.getInt("subTotal"));
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

    //printer bluethoot
    public static final int PERMISSION_BLUETOOTH = 1;

    public void printBluetooth() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, PembayaranActivity.PERMISSION_BLUETOOTH);
        } else {
            // this.printIt(BluetoothPrintersConnections.selectFirstPaired());
            new AsyncBluetoothEscPosPrint(this).execute(this.getAsyncEscPosPrinter(null));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case PembayaranActivity.PERMISSION_BLUETOOTH:
                    this.printBluetooth();
                    break;
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    public AsyncEscPosPrinter getAsyncEscPosPrinter(DeviceConnection printerConnection) {
        SimpleDateFormat format = new SimpleDateFormat("'on' yyyy-MM-dd 'at' HH:mm:ss");
        AsyncEscPosPrinter printer = new AsyncEscPosPrinter(printerConnection, 203, 48f, 32);
        for(int i=0;i<pesananModelList.size();i++){
            PesananModel pesananModel = pesananModelList.get(i);
            String asw = pesananModel.getNama_menu()+"("+pesananModel.getKeterangan()+")"+"\n"+""+String.valueOf(pesananModel.getJumlah())+"X @Rp. "+pesananModel.getHarga_menu()+" = Rp. "+pesananModel.getSubTotal()+"\n";
            where.add(asw);
        }
        String replace1 = where.toString();
        String replace2 = replace1.replace("[","- ");
        String replace3 = replace2.replace(",","- ");
        String replace31 = replace3.replace("]","\n");
//        String replace4 = replace31.replace(",","");
//        String replace5 = replace4.replace("\\s\\s","");
        Log.d("TAG", "where : "+replace31);

        return printer.setTextToPrint(
                "[C]<img>" + PrinterTextParserImg.bitmapToHexadecimalString(printer, this.getApplicationContext().getResources().getDrawableForDensity(R.drawable.logo, DisplayMetrics.DENSITY_MEDIUM)) + "</img>\n" +
                        "[L]\n" +
                        "[C]<u><font >Coffe & Kitchen</font></u>\n" +
                        "[C]<u><font size='big'>Tiga Dara</font></u>\n" +
                        "[L]\n" +
                        "[C]<u type='double'>" + format.format(new Date()) + "</u>\n" +
//                        "[C]\n" +
                        "[C]================================\n" +
                        "[L]<b>Atas Nama :</b>[R]"+getIntent().getStringExtra("atasnama")+'\n' +
                        "[L]<b>Nama Kasir :</b>[R]"+preferences.getSPNama()+"\n" +
                        "[L]<b>Nomor Transaksi :</b>[R]"+getIntent().getStringExtra("notrans")+"\n" +
                        "[C]================================\n" +
                        replace31 +
//                        "[L]\n" +
                        "[C]--------------------------------\n" +
                        "[R]TOTAL PRICE :[R]Rp. "+getIntent().getStringExtra("result1")+",-\n" +
                        "[R]BAYAR :[R]RP. "+getIntent().getStringExtra("bayar")+",-\n" +
                        "[R]KEMBALIAN :[R]Rp. "+getIntent().getStringExtra("kembalian")+",-\n" +
                        "[L]\n" +
                        "[C]================================\n" +
                        "[L]\n" +
                        "[C]<u><font size='big'>Terima Kasih</font></u>\n" +
                        "[C]<u><font >Atas Kunjungan Anda</font></u>\n"
//                        "[L]<u><font color='bg-black' size='tall'>Customer :</font></u>\n" +
//                        "[L]Raymond DUPONT\n" +
//                        "[L]5 rue des girafes\n" +
//                        "[L]31547 PERPETES\n" +
//                        "[L]Tel : +33801201456\n" +
//                        "\n" +
//                        "[C]<barcode type='ean13' height='10'>831254784551</barcode>\n" +
//                        "[L]\n" +
//                        "[C]<qrcode size='20'>http://www.developpeur-web.dantsu.com/</qrcode>\n"
        );
    }
}