package com.learn.dntl.cryptocoins.App;

import android.util.Log;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.learn.dntl.cryptocoins.Model.CryptoCoin;
import com.learn.dntl.cryptocoins.R;
import com.learn.dntl.cryptocoins.RecyclerAdapter;
import com.learn.dntl.cryptocoins.Service.APIService;
import com.learn.dntl.cryptocoins.Service.RestClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        apiService = RestClient.getSource();

        final ArrayList<CryptoCoin> cryptoCoins = new ArrayList<>();

        apiService.getCryptoCoins().enqueue(new Callback<List<CryptoCoin>>() {//erro
            @Override
            public void onResponse(Call<List<CryptoCoin>> call, Response<List<CryptoCoin>> response) {
                cryptoCoins.addAll(response.body());
                recyclerView.setAdapter(new RecyclerAdapter(cryptoCoins));
            }

            @Override
            public void onFailure(Call<List<CryptoCoin>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayoutManager linearLayout = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayout);
    }
}
