package com.letbemagi.magi.domma.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.letbemagi.magi.domma.Model.Category;
import com.letbemagi.magi.domma.Model.WishList;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magi on 03/12/2017.
 */

public class WishListAdapter extends  RecyclerView.Adapter<WishListAdapter.ViewHolder>{
    Context context;
    List<WishList> listWish = new ArrayList<>();

    public WishListAdapter(Context context, List<WishList> listWish) {
        this.context = context;
        this.listWish = listWish;
    }

    @Override
    public WishListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_wishlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WishListAdapter.ViewHolder holder, int position) {
        holder.tvNamaWishList.setText(listWish.get(position).getNameWish());
        holder.tvDescWishList.setText(listWish.get(position).getDescWish());
        holder.tvhargaWishlist.setText(String.valueOf(listWish.get(position).getHargaWish()));
    }

    @Override
    public int getItemCount() {
        return listWish.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaWishList, tvDescWishList, tvhargaWishlist;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNamaWishList = itemView.findViewById(R.id.tvNamaWishList);
            tvDescWishList = itemView.findViewById(R.id.tvDescWishlist);
            tvhargaWishlist = itemView.findViewById(R.id.tvhargaWishlist);
        }
    }
}
