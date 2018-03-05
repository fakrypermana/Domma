package com.letbemagi.magi.domma.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.letbemagi.magi.domma.Model.Category;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fakrypermana on 12/11/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    List<Category> listCat = new ArrayList<>();

    public CategoryAdapter(Context context, List<Category> listCat) {
        this.context = context;
        this.listCat = listCat;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvCateg.setText(listCat.get(position).getNameCateg());
    }

    @Override
    public int getItemCount() {
        return listCat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvCateg;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCateg = itemView.findViewById(R.id.tvCateg);
        }
    }
}
