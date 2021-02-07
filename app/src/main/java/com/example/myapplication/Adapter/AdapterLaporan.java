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

public class AdapterLaporan extends RecyclerView.Adapter<AdapterLaporan.LaporanVierwHolder>{

    private List<PesananModel> pesananModelList;

    public AdapterLaporan(List<PesananModel> pesananModelList) {
        this.pesananModelList = pesananModelList;
    }

    @NonNull
    @Override
    public AdapterLaporan.LaporanVierwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kasir, parent, false);
        AdapterLaporan.LaporanVierwHolder laporanVierwHolder = new AdapterLaporan.LaporanVierwHolder(view);
        return laporanVierwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLaporan.LaporanVierwHolder holder, int position) {
        holder.atasnama.setText(pesananModelList.get(position).getAtasnama());
        holder.tanggal.setText(pesananModelList.get(position).getCreate_on());
    }

    @Override
    public int getItemCount() {
        return pesananModelList.size();
    }

    public class LaporanVierwHolder extends RecyclerView.ViewHolder {
        TextView atasnama, tanggal;
        public LaporanVierwHolder(@NonNull View itemView) {
            super(itemView);

            atasnama = itemView.findViewById(R.id.tv);
            tanggal = itemView.findViewById(R.id.tv2);

        }
    }
}
