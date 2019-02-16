package com.learn.dntl.cryptocoins.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.learn.dntl.cryptocoins.App.MainActivity;
import com.learn.dntl.cryptocoins.Model.CryptoCoin;
import com.learn.dntl.cryptocoins.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChoiceCoinsAdapter extends RecyclerView.Adapter<ChoiceCoinsAdapter.ViewHolder> {
    ArrayList<CryptoCoin> cryptoCoins;

    public ChoiceCoinsAdapter(ArrayList<CryptoCoin> cryptoCoins) {
        this.cryptoCoins = cryptoCoins;
    }

    @NonNull
    @Override
    public ChoiceCoinsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_coins, viewGroup, false);
        return new ChoiceCoinsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChoiceCoinsAdapter.ViewHolder viewHolder, int i) {
        CryptoCoin cryptoCoin = cryptoCoins.get(i);

        viewHolder.name.setText(cryptoCoin.getName());

        CheckBox ckb = (CheckBox) viewHolder.name;

        ckb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                notifyItemChanged(i, cryptoCoins.size());

                if (ckb.isChecked()) {

                    if (MainActivity.userCryptoCoins.contains(ckb.getText().toString().toUpperCase())) {

                    } else {
                        MainActivity.userCryptoCoins.add(ckb.getText().toString().toUpperCase());
                    }
                } else {

                    if (MainActivity.userCryptoCoins.contains(ckb.getText().toString().toUpperCase())) {
                        MainActivity.userCryptoCoins.remove(ckb.getText().toString().toUpperCase());
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cryptoCoins.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.checkbox_name)
        CheckBox name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
