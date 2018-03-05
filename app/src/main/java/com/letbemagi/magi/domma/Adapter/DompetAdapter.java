package com.letbemagi.magi.domma.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.letbemagi.magi.domma.Model.Dompet;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magi on 03/12/2017.
 */

public class DompetAdapter extends RecyclerView.Adapter<DompetAdapter.ViewHolder> {
    Context context;
    List<Dompet> listDompet;

    public DompetAdapter(Context context, List<Dompet> listDompet) {
        this.context = context;
        this.listDompet = listDompet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_dompet, parent, false);
        return new DompetAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNamaDompet.setText(listDompet.get(position).getNameDompet());
        holder.tvDescDompet.setText(listDompet.get(position).getDescDompet());
    }

    @Override
    public int getItemCount() {
        return listDompet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaDompet,tvDescDompet;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNamaDompet = itemView.findViewById(R.id.tvNamaDompet);
            tvDescDompet = itemView.findViewById(R.id.tvDescDompet);
        }
    }
}
