package android.day2.lab4.playwithfregmant;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Fragment1 myFragment1;
    Fragment2 myFragment2;
    Fragment3 myFragment3;
    Fragment4 myFragment4;
    int flag;
    FragmentManager mgr;
    FragmentTransaction trnc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mgr = getSupportFragmentManager();
        trnc = mgr.beginTransaction();
    }

    public void addFregmant3(View view) {
        myFragment3 = new Fragment3();
        addFragmengToBackStack(myFragment3 , "myFragment3");
    }

    public void addFregmant2(View view) {
        myFragment2 = new Fragment2();
        addFragmengToBackStack(myFragment2 , "myFragment2");
    }

    public void addFregmant1(View view) {
        myFragment1 = new Fragment1();
        addFragmengToBackStack(myFragment1 , "myFragment1");
    }

    public void replaceFregmant(View view) {
        myFragment4 = new Fragment4();
        trnc = mgr.beginTransaction();
        if(mgr.getBackStackEntryCount() >0)
        {
            mgr.popBackStack();
        }
        trnc.replace(R.id.framLayoutFreg , myFragment4 ,"myFragment4");
        //trnc.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trnc.addToBackStack("myFragment4");
        trnc.commit();
    }

    public void removeFregmant(View view) {
        if(mgr.getBackStackEntryCount() >0)
        {
            trnc = mgr.beginTransaction();
            trnc.remove(getLastFragment());
            trnc.commit();
            mgr.popBackStack();
        }
        else
        {
            Toast.makeText(MainActivity.this , "No Fragment to Remove" ,Toast.LENGTH_SHORT).show();
        }
    }
    public Fragment getLastFragment()
    {
        int index = mgr.getBackStackEntryCount()-1;
        Log.i("teeeeeeeeeeeest",String.valueOf(index));
        FragmentManager.BackStackEntry backEntry = mgr.getBackStackEntryAt(index);
        String fregTag = backEntry.getName();
        Log.i("teeeeeeeeeeeest",fregTag);
        Fragment frag=mgr.findFragmentByTag(fregTag);

        return frag;
    }

    public void addFragmengToBackStack(Fragment frag , String fragmentTage)
    {
        trnc = mgr.beginTransaction();
        trnc.add(R.id.framLayoutFreg , frag ,fragmentTage);
        if(mgr.getBackStackEntryCount() >0)
            trnc.hide(getLastFragment());
        trnc.addToBackStack(fragmentTage);
        trnc.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
       // trnc.commit();
    }
}
