package android.day1.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTow extends AppCompatActivity {

    TextView phone;
    TextView message;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow);
        phone = (TextView) findViewById(R.id.textViewPhone);
        message = (TextView) findViewById(R.id.textViewMsg);
        btnBack= (Button) findViewById(R.id.btnCloseB);
        Intent intent = getIntent();
        phone.setText(intent.getStringExtra("PhoneNumber").toString());
        message.setText(intent.getStringExtra("Message").toString());
        btnBack.setOnClickListener((v) -> {
            //Intent outIntent = new Intent(ActivityTow.this,MainActivity.class);
            // startActivity(outIntent);
            finish();
        });
    }

}
