package android.day7.lab3.complexlistviewdays_goodway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Day> days;
    private MyAdapter adapter;
    private ListView listDays;
    public MainActivity() {
        days = new ArrayList<Day>();

        days.add(new Day("Saturday", "Today is Saturday", R.drawable.one));
        days.add(new Day("Sunday", "Today is Sunday", R.drawable.two));
        days.add(new Day("Monday", "Today is Monday", R.drawable.three));
        days.add(new Day("Tuesday", "Today is Tuesday", R.drawable.four));
        days.add(new Day("Wednesday", "Today is Wednesday", R.drawable.five));
        days.add(new Day("Thursday", "Today is Thursday", R.drawable.six));
        days.add(new Day("Friday", "Today is Friday", R.drawable.seven));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listDays = (ListView) findViewById(R.id.listViewDays);
        adapter = new MyAdapter(getApplicationContext(),days);
        listDays.setAdapter(adapter);
        listDays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), (String)parent.getItemAtPosition(position).toString() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}