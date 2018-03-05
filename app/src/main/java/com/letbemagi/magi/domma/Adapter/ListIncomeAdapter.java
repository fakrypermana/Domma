package com.letbemagi.magi.domma.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.letbemagi.magi.domma.Model.ItemTransIncome;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by fakrypermana on 10/11/2017.
 */

public class ListIncomeAdapter extends RecyclerView.Adapter<ListIncomeAdapter.ViewHolder> {
    private Context context;
    private List<ItemTransIncome> listIncome = new ArrayList<>();

    public ListIncomeAdapter(Context context, List<ItemTransIncome> listIncome) {
        this.context = context;
        this.listIncome = listIncome;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTanggalInc,tvTanggalOuc,tvCategoriesOutc,tvMountOutc, tvCategoriesInc, tvMountInc;
        CardView cvTransInc, cvTransOutc;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTanggalInc = itemView.findViewById(R.id.tvtglListInc);
            tvCategoriesInc = itemView.findViewById(R.id.tvcatListInc);
            tvMountInc = itemView.findViewById(R.id.tvhargaListInc);
            tvTanggalOuc = itemView.findViewById(R.id.tvtglListOutc);
            tvCategoriesOutc = itemView.findViewById(R.id.tvcatListOutc);
            tvMountOutc = itemView.findViewById(R.id.tvhargaListOutc);
            cvTransInc = itemView.findViewById(R.id.cvTransInc);
            cvTransOutc = itemView.findViewById(R.id.cvTransOuc);
        }
    }

    @Override
    public ListIncomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_income_perdata, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListIncomeAdapter.ViewHolder holder, int position) {
        holder.tvTanggalInc.setText(listIncome.get(position).getTanggal());
        holder.tvCategoriesInc.setText(listIncome.get(position).getKategori());
        holder.tvMountInc.setText(String.valueOf(listIncome.get(position).getMount()));
        holder.tvTanggalOuc.setText(listIncome.get(position).getTanggal());
        holder.tvCategoriesOutc.setText(listIncome.get(position).getKategori());
        holder.tvMountOutc.setText(String.valueOf(listIncome.get(position).getMount()));

        if (listIncome.get(position).getType() == 0 ){
            holder.cvTransInc.setVisibility(View.VISIBLE);
            holder.cvTransOutc.setVisibility(View.GONE);
        } else if(listIncome.get(position).getType() == 1){
            holder.cvTransInc.setVisibility(View.GONE);
            holder.cvTransOutc.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listIncome.size();
    }



}
