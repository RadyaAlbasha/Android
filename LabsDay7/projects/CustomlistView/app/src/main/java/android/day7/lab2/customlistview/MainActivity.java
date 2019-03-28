package android.day7.lab2.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] days;
    private ArrayAdapter<String> adapter;
    private ListView listDays;
    public MainActivity() {
        days = new String[]{"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listDays = (ListView) findViewById(R.id.listViewDays);
        adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.single_row,R.id.textView,days);
        listDays.setAdapter(adapter);
        listDays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), (String)parent.getItemAtPosition(position) , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
