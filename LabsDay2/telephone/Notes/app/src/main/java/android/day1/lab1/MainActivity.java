package android.day1.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnNext;
    Button btnClose;
    EditText phone;
    EditText message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = (EditText) findViewById(R.id.mobileNumberValue);
        message = (EditText) findViewById(R.id.messageValue);

    }

    public void nextForm(View view) {
        Intent outIntent = new Intent(MainActivity.this,ActivityTow.class);
        outIntent.putExtra("PhoneNumber",phone.getText().toString());
        outIntent.putExtra("Message",message.getText().toString());
        startActivity(outIntent);
    }

    public void closeApp(View view) {
        finish();
    }
}
