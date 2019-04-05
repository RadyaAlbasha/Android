package android.firebase.labs.worldpopulationretrofit.model;


import android.content.Context;
import android.firebase.labs.worldpopulationretrofit.R;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    private Context context ;
    private ArrayList<WorldPopulation> countriesList;

    public CountryAdapter(Context _context , ArrayList<WorldPopulation> _countriesList){
        context=_context;
        countriesList=_countriesList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewRank;
        public TextView textViewpopulation;
        public TextView textViewCountry;
        public ImageView imageViewFlag;
        public ConstraintLayout constraintLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            textViewCountry = itemView.findViewById(R.id.textViewCountry);
            textViewRank = itemView.findViewById(R.id.textViewRank);
            textViewpopulation = itemView.findViewById(R.id.textViewPopulation);
            imageViewFlag = itemView.findViewById(R.id.imageViewFlag);
            constraintLayout = itemView.findViewById(R.id.ConstraintLayout_item);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(context).inflate(R.layout.item_country, viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final WorldPopulation country = countriesList.get(i);
        myViewHolder.textViewpopulation.setText(country.getPopulation());
        myViewHolder.textViewRank.setText(country.getRank());
        myViewHolder.textViewCountry.setText(country.getCountry());
        String url = country.getFlag();
        //load image from url
        Glide.with(context)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myViewHolder.imageViewFlag); //with(myViewHolder.imageViewFlag)

        myViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, country.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }


}
