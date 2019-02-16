package com.learn.dntl.cryptocoins.App;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.learn.dntl.cryptocoins.Adapters.ChoiceCoinsAdapter;
import com.learn.dntl.cryptocoins.Adapters.InfoCoinsAdapter;
import com.learn.dntl.cryptocoins.Model.CryptoCoin;
import com.learn.dntl.cryptocoins.R;
import com.learn.dntl.cryptocoins.Service.APIService;
import com.learn.dntl.cryptocoins.Service.RestClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.floating_button_addNewCoin)
    FloatingActionButton addNewCoin;

    public static ArrayList<String> userCryptoCoins = new ArrayList<String>() {
        {
            add("bitcoin".toUpperCase());
        }
    };

    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadCoins();
    }

    private void loadCoins() {
        recyclerView.setAdapter(null);

        apiService = RestClient.getSource();

        final ArrayList<CryptoCoin> cryptoCoins = new ArrayList<>();

        apiService.getCryptoCoins().enqueue(new Callback<List<CryptoCoin>>() {
            @Override
            public void onResponse(Call<List<CryptoCoin>> call, Response<List<CryptoCoin>> response) {
                cryptoCoins.addAll(response.body());
                recyclerView.setAdapter(new InfoCoinsAdapter(cryptoCoins));
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

    @OnClick(R.id.floating_button_addNewCoin)
    public void addNewCoin() {
        recyclerView.setAdapter(null);

        apiService = RestClient.getSource();

        final ArrayList<CryptoCoin> cryptoCoins = new ArrayList<>();

        apiService.getCryptoCoins().enqueue(new Callback<List<CryptoCoin>>() {
            @Override
            public void onResponse(Call<List<CryptoCoin>> call, Response<List<CryptoCoin>> response) {
                cryptoCoins.addAll(response.body());
                recyclerView.setAdapter(new ChoiceCoinsAdapter(cryptoCoins));
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
