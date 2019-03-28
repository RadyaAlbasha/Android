package android.day2.lab2.fragmentdinamic;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DinamicFragment myFragment = new DinamicFragment();
        FragmentManager mgr = getSupportFragmentManager();
        FragmentTransaction trnc = mgr.beginTransaction();
        trnc.add(R.id.myFreg , myFragment ,"myFragment");
        trnc.commit();
    }
}
