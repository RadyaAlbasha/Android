package android.day2.lab3.fregmantcounter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Communicator{
    FragmentTwo myFragment2;
    FragmentOne myFragment1;
    FragmentManager mgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mgr = getSupportFragmentManager();
        FragmentTransaction trnc = mgr.beginTransaction();
        if(savedInstanceState == null)
        {
            myFragment1 = new FragmentOne();
            myFragment2 = new FragmentTwo();
            trnc.add(R.id.linearLayout1 , myFragment1 ,"myFragment1");
            trnc.add(R.id.linearLayout2 , myFragment2 ,"myFragment2");
            trnc.commit();
        }
        else
        {
            myFragment1 = (FragmentOne) mgr.findFragmentByTag("myFragment1");
            myFragment2 = (FragmentTwo)mgr.findFragmentByTag("myFragment2");
        }


    }

    @Override
    public void respond(int c) {
        myFragment2 =  (FragmentTwo)mgr.findFragmentByTag("myFragment2");
        myFragment2.changeData(c);
    }
}
