package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.MejaModel;
import com.example.myapplication.Model.PesananModel;
import com.example.myapplication.R;

import java.util.List;

public class AdapterKasir extends RecyclerView.Adapter<AdapterKasir.KasirVierwHolder>{

    private List<PesananModel> pesananModelList;

    public AdapterKasir(List<PesananModel> pesananModelList) {
        this.pesananModelList = pesananModelList;
    }

    @NonNull
    @Override
    public AdapterKasir.KasirVierwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kasir, parent, false);
        AdapterKasir.KasirVierwHolder kasirVierwHolder = new AdapterKasir.KasirVierwHolder(view);
        return kasirVierwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKasir.KasirVierwHolder holder, int position) {
        holder.atasnama.setText(pesananModelList.get(position).getAtasnama());
        holder.nomeja.setText("NO. "+String.valueOf(pesananModelList.get(position).getNomeja()));
    }

    @Override
    public int getItemCount() {
        return pesananModelList.size();
    }

    public class KasirVierwHolder extends RecyclerView.ViewHolder {
        TextView atasnama, nomeja;
        public KasirVierwHolder(@NonNull View itemView) {
            super(itemView);

            atasnama = itemView.findViewById(R.id.tv);
            nomeja = itemView.findViewById(R.id.tv2);
        }
    }
}
