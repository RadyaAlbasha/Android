package android.day3.lab1.loginnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnNext;
    Button btnClose;
    EditText title;
    EditText noteBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (EditText) findViewById(R.id.txtTitleValue);
        noteBody = (EditText) findViewById(R.id.noteValue);

    }

    public void nextForm(View view) {
        Intent outIntent = new Intent(MainActivity.this,ActivityTwo.class);
        outIntent.putExtra("titleNumber",title.getText().toString());
        outIntent.putExtra("noteBody",noteBody.getText().toString());
        startActivity(outIntent);
    }

    public void closeApp(View view) {
        finish();
    }
}
