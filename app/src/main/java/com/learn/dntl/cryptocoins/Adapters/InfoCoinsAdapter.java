package com.learn.dntl.cryptocoins.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.learn.dntl.cryptocoins.App.MainActivity;
import com.learn.dntl.cryptocoins.Model.CryptoCoin;
import com.learn.dntl.cryptocoins.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoCoinsAdapter extends RecyclerView.Adapter<InfoCoinsAdapter.ViewHolder> {

    ArrayList<CryptoCoin> cryptoCoins;

    public InfoCoinsAdapter(ArrayList<CryptoCoin> cryptoCoins) {
        this.cryptoCoins = cryptoCoins;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_info_coins, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CryptoCoin cryptoCoin = cryptoCoins.get(i);

        viewHolder.name.setText(cryptoCoin.getName());
        viewHolder.price.setText(cryptoCoin.getPriceUsd());

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cryptoCoins.remove(i);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cryptoCoins.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_value_name)
        TextView name;
        @BindView(R.id.text_view_value_price)
        TextView price;
        @BindView(R.id.button_delete)
        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
