package com.letbemagi.magi.domma.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.letbemagi.magi.domma.Adapter.WishListAdapter;
import com.letbemagi.magi.domma.Model.WishList;
import com.letbemagi.magi.domma.PrefModel.ListWish;
import com.letbemagi.magi.domma.Preferences.PrefWish;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magi on 08/11/2017.
 */

public class WishListFragment extends Fragment{
    EditText edtNamaWisList, edtDescWishList, edtHargaWishList;
    Button btnAddWish;
    RecyclerView rvWish;
    WishListAdapter adapterWishList;
    List<WishList> listWish = new ArrayList<>();

    public WishListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        edtNamaWisList = view.findViewById(R.id.edtNamaWishList);
        edtDescWishList = view.findViewById(R.id.edtDescWishList);
        edtHargaWishList = view.findViewById(R.id.edtMountWishList);
        btnAddWish = view.findViewById(R.id.btnAddWishList);
        rvWish = view.findViewById(R.id.rvWishList);

        rvWish.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterWishList = new WishListAdapter(getActivity(), listWish);
        rvWish.setAdapter(adapterWishList);

        loadData();

        btnAddWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WishList wish = new WishList(edtNamaWisList.getText().toString(),
                        edtDescWishList.getText().toString(),Integer.valueOf(edtHargaWishList.getText().toString()));
                listWish.add(wish);
                String namaWish = edtNamaWisList.getText().toString();
                String descWish = edtDescWishList.getText().toString();
                String mountWish = edtHargaWishList.getText().toString();
                // jika ada data
                if (namaWish.trim().length() > 0 && descWish.trim().length() > 0 && mountWish.trim().length() > 0) {
                    if (PrefWish.load(getActivity().getApplicationContext()) != null) {
                        ListWish listWishList = PrefWish.load(getActivity().getApplicationContext());

                        List<WishList> list = listWishList.getListWish();
                        list.add(wish);
                        listWishList.setListWish(list);

                        PrefWish.save(listWishList, getActivity().getApplicationContext());
                    }
                    // kalau data masih kosong
                    else {
                        //Log.i("fakritest", "onClick: data kosong");
                        List<WishList> list = new ArrayList<>();
                        list.add(wish);

                        PrefWish.save(new ListWish(list), getActivity().getApplicationContext());
                    }
                    adapterWishList.notifyDataSetChanged();
                }else {

                    // user didn't entered username or password
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Please enter wish name etc",
                            Toast.LENGTH_LONG).show();

                }

            }
        });
        return view;
    }

    private void loadData() {
        // jika ada data
        if (PrefWish.load(getActivity().getApplicationContext()) != null){
            ListWish listWishlist = PrefWish.load(getActivity().getApplicationContext());
            //Log.i("Cobacoba", "loadData: "+PrefCategory.getJSON(getActivity()));
            listWish.addAll(listWishlist.getListWish());
        }
    }
}
