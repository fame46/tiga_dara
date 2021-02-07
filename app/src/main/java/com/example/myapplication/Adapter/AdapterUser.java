package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.UserModel;
import com.example.myapplication.R;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.UserVierwHolder> {

    Context context;
    private List<UserModel> userModelList;
    HapusListener hapusListener;

    public AdapterUser(Context context, List<UserModel> userModelList,HapusListener hapusListener) {
        this.context = context;
        this.userModelList = userModelList;
        this.hapusListener = hapusListener;
    }

    @NonNull
    @Override
    public AdapterUser.UserVierwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_one_button, parent, false);
        AdapterUser.UserVierwHolder userVierwHolder = new AdapterUser.UserVierwHolder(view);
        return userVierwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUser.UserVierwHolder holder, final int position) {
        holder.tv.setText(userModelList.get(position).getUsername());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "coba hapus", Toast.LENGTH_SHORT).show();
                hapusListener.hapusId(String.valueOf(userModelList.get(position).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public class UserVierwHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView delete;
        public UserVierwHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            delete  = itemView.findViewById(R.id.btnDelete);
        }
    }

    public interface HapusListener{
        public void hapusId(String id);
    }
}
