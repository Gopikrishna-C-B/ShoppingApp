package com.gpk.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> implements Filterable {

    private static final String TAG = "ShopAdapter";

    private List<String> shopsList;
    private List<String> shopsListAll;
    private Context context;
    private ShopSelectionListener selectionListener;

    public ShopAdapter(List<String> shopsList, Context context) {
        this.shopsList = shopsList;
        this.context = context;
        selectionListener = (MainActivity) context;
        this.shopsListAll = new ArrayList<>(shopsList);
    }


    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item, parent, false);

        return new ShopViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopViewHolder holder, int position) {

        String shopName = shopsList.get(position);
        holder.shopName.setText(shopName);

        holder.shopName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectionListener.onShopSelected(shopsList.get(holder.getAdapterPosition()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return shopsList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<String> filteredList = new ArrayList<>();

            if (charSequence.toString().isEmpty()) {

                filteredList.addAll(shopsListAll);

            } else {
                for (String shop : shopsListAll) {
                    if (shop.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(shop);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {


            shopsList.clear();
            shopsList.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();

        }
    };


    class ShopViewHolder extends RecyclerView.ViewHolder {

        public TextView shopName;

        public ShopViewHolder(@NonNull TextView itemView) {
            super(itemView);
            shopName = itemView;

        }
    }
}




