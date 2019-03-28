package android.day1.lab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtCounter;
    Integer count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null)
        {
            count = savedInstanceState.getInt("mycount");
        }
        txtCounter = (TextView) findViewById(R.id.counter);
        txtCounter.setText(count.toString());
        count++;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mycount",count);
    }
}
