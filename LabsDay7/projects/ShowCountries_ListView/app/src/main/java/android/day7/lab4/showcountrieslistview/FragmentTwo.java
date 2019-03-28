package android.day7.lab4.showcountrieslistview;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {


    TextView txtRank;
    TextView txtCountry;
    TextView txtPopulation;
    ImageView imgFlag;
    Country country;
    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        txtRank = (TextView) view.findViewById(R.id.textViewRank);
        txtCountry = (TextView) view.findViewById(R.id.textViewCountry);
        txtPopulation = (TextView) view.findViewById(R.id.textViewPopulation);
        imgFlag = (ImageView) view.findViewById(R.id.imageViewFlag);

        if(savedInstanceState != null)
        {
            country = savedInstanceState.getParcelable("mycountry");
        }

        return view;
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("mycountry",country);
    }
    public void changeData(Country data){
        txtRank.setText(data.getRank());
        txtCountry.setText(data.getCountry());
        txtPopulation.setText(data.getPopulation());
        if(data.getImgFlag() == null)
        {
            MyAsyncTask myAsyncTask = new MyAsyncTask(getContext(),imgFlag);
            myAsyncTask.execute(data.getFlag());
        }
        else
            imgFlag.setImageBitmap(data.getImgFlag());
    }


}
