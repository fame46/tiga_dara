package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.MejaModel;
import com.example.myapplication.Model.UserModel;
import com.example.myapplication.R;

import java.util.List;

public class AdapterMeja extends RecyclerView.Adapter<AdapterMeja.MejaVierwHolder>{

    Context context;
    private List<MejaModel> mejaModelList;
    HapusListener hapusListener;

    public AdapterMeja(Context context, List<MejaModel> mejaModelList, HapusListener hapusListener) {
        this.context = context;
        this.mejaModelList = mejaModelList;
        this.hapusListener = hapusListener;
    }

    @NonNull
    @Override
    public AdapterMeja.MejaVierwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_one_button, parent, false);
        AdapterMeja.MejaVierwHolder mejaVierwHolder = new AdapterMeja.MejaVierwHolder(view);
        return mejaVierwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMeja.MejaVierwHolder holder, final int position) {
        holder.tv.setText(String.valueOf(mejaModelList.get(position).getMeja()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "coba hapus", Toast.LENGTH_SHORT).show();
                hapusListener.hapusId(String.valueOf(mejaModelList.get(position).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mejaModelList.size();
    }

    public class MejaVierwHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView delete;
        public MejaVierwHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            delete  = itemView.findViewById(R.id.btnDelete);
        }
    }

    public interface HapusListener{
        public void hapusId(String id);
    }
}
