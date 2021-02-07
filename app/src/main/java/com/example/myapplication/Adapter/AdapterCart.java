package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.PesananModel;
import com.example.myapplication.R;

import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.CartViewHolder>{

    private List<PesananModel> pesananModelList;

    public AdapterCart(List<PesananModel> pesananModelList) {
        this.pesananModelList = pesananModelList;
    }

    @NonNull
    @Override
    public AdapterCart.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cart, parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(view);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCart.CartViewHolder holder, int position) {
        int sub = pesananModelList.get(position).getJumlah() * pesananModelList.get(position).getHarga_menu();
        holder.jumlah.setText(String.valueOf(pesananModelList.get(position).getJumlah()) + "X");
        holder.namamenu.setText(pesananModelList.get(position).getNama_menu());
        holder.hargamenu.setText("Rp. "+String.valueOf(pesananModelList.get(position).getHarga_menu())+",-");
        holder.keterangan.setText(pesananModelList.get(position).getKeterangan());
        holder.subtotal.setText("Rp. "+String.valueOf(sub)+",-");
    }

    @Override
    public int getItemCount() {
        return pesananModelList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        TextView jumlah, namamenu, hargamenu, keterangan, subtotal;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            jumlah = itemView.findViewById(R.id.tvQty);
            namamenu = itemView.findViewById(R.id.tvNamamenu);
            hargamenu = itemView.findViewById(R.id.textViewHarga);
            keterangan = itemView.findViewById(R.id.tvKeterangan);
            subtotal = itemView.findViewById(R.id.textViewSubtotal);
        }
    }
}
