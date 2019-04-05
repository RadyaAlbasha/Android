package android.firebase.labs.worldpopulationretrofit;

import android.firebase.labs.worldpopulationretrofit.model.CountryAdapter;
import android.firebase.labs.worldpopulationretrofit.model.WorldPopulation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.ArrayList;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<WorldPopulation> data;
    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewCountries);
        loadJson();
    }

    private void loadJson(){
        int cachSize= 10*1024*1024;//10mp
        Cache cache= new Cache(getCacheDir(),cachSize);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.androidbegin.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<JsonResponse> call = request.getJSON();

        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                data = response.body().getWorldpopulation();

                countryAdapter = new CountryAdapter(MainActivity.this,data);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(countryAdapter);
                //AnimationUtils.loadLayoutAnimation(recyclerView.getContext(),R.anim.);
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
