package android.day7.lab4.showcountrieslistview;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityTwo extends AppCompatActivity implements Communicator {

    FragmentTwo myFragment2;
    FragmentOne myFragment1;
    FragmentManager mgr;
    int orientation;
    Intent intent;
    //Country country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mgr = getSupportFragmentManager();
        orientation = getResources().getConfiguration().orientation;
        myFragment2 = (FragmentTwo) mgr.findFragmentById(R.id.myFragment2);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            myFragment1 = (FragmentOne) mgr.findFragmentById(R.id.myFragment1);
        }
        Bundle data = getIntent().getExtras();
        Country country = (Country) data.getParcelable("Country");
        myFragment2.changeData(country);
    }


    @Override
    protected void onStart() {
        super.onStart();
        /*if(country == null)
        {
            Bundle data = getIntent().getExtras();
            country = (Country) data.getParcelable("Country");
        }
        myFragment2.changeData(country);*/
    }

    @Override
    public Country getCurrentCountry(Country c) {
        myFragment2 = (FragmentTwo) mgr.findFragmentById(R.id.myFragment2);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            myFragment2.changeData(c);
            return c;
        }
        else
        {
            Bundle data = getIntent().getExtras();
            Country country = (Country) data.getParcelable("Country");
            myFragment2.changeData(country);
            return country;
        }
    }

    @Override
    public int getOrientation() {
        return orientation;
    }
    @Override
    public Intent getCurrentIntent() {
        return getIntent();
    }
}
