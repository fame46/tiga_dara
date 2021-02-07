package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.MejaModel;
import com.example.myapplication.R;

import java.util.List;

public class AdapterDDMeja extends RecyclerView.Adapter<AdapterDDMeja.DDMejaVierwHolder>{

    private List<MejaModel> itemMeja;

    public AdapterDDMeja(List<MejaModel> itemMeja) {
        this.itemMeja = itemMeja;
    }

    @NonNull
    @Override
    public DDMejaVierwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_meja, parent, false);
        DDMejaVierwHolder ddMejaVierwHolder = new DDMejaVierwHolder(view);
        return ddMejaVierwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDDMeja.DDMejaVierwHolder holder, int position) {
        holder.meja.setText(itemMeja.get(position).getMeja());
    }

    @Override
    public int getItemCount() {
        return itemMeja.size();
    }

    public class DDMejaVierwHolder extends RecyclerView.ViewHolder {
        TextView meja;
        public DDMejaVierwHolder(@NonNull View itemView) {
            super(itemView);
            meja = itemView.findViewById(R.id.tvSpinMeja);
        }
    }
}
