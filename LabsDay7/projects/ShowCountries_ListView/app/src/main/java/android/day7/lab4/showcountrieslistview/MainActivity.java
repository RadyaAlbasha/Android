package android.day7.lab4.showcountrieslistview;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Communicator{

    FragmentTwo myFragment2;
    FragmentOne myFragment1;
    FragmentManager mgr;
    int orientation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mgr = getSupportFragmentManager();
        //FragmentTransaction trnc = mgr.beginTransaction();
        orientation = getResources().getConfiguration().orientation;
        myFragment1 = (FragmentOne) mgr.findFragmentById(R.id.myFragment1);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            myFragment2 = (FragmentTwo) mgr.findFragmentById(R.id.myFragment2);
        }
    }

    @Override
    public int getOrientation() {
        return orientation;
    }

    @Override
    public Intent getCurrentIntent() {
        return null;
    }

    @Override
    public Country getCurrentCountry(Country c) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            myFragment2 = (FragmentTwo) mgr.findFragmentById(R.id.myFragment2);
            myFragment2.changeData(c);
        }
        else
        {
            Intent outIntent;
            outIntent = new Intent(getApplicationContext(),ActivityTwo.class);
            outIntent.putExtra("Country", (Parcelable) c);
            startActivity(outIntent);
        }
        return c;
    }
}
