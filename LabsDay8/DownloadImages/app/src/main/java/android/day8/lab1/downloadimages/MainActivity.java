package android.day8.lab1.downloadimages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView imgUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgUrl = findViewById(R.id.editTextURL);
    }

    public void downloadImg(View view) {
        Intent intent = new Intent(this , MyIntentService.class);
        intent.putExtra("imgUrl",imgUrl.getText().toString());
        startService(intent);
    }
}
