package com.letbemagi.magi.domma.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.letbemagi.magi.domma.Model.Budget;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by fakrypermana on 14/11/2017.
 */

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.ViewHolder> {
    Context context;
    List<Budget> listBudget;

    public BudgetAdapter(Context context, List<Budget> listBudget) {
        this.context = context;
        this.listBudget = listBudget;
    }

    @Override
    public BudgetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_budget, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BudgetAdapter.ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: "+ listBudget.get(position).getNameBudget());
        holder.namaBudget.setText(listBudget.get(position).getNameBudget());
        holder.mountBudget.setText(String.valueOf(listBudget.get(position).getMountBudget()));
    }

    @Override
    public int getItemCount() {
        return listBudget.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mountBudget,namaBudget;

        public ViewHolder(View itemView) {
            super(itemView);
            namaBudget = itemView.findViewById(R.id.tvNamaBudget);
            mountBudget = itemView.findViewById(R.id.tvMountBudget);
        }
    }
}
