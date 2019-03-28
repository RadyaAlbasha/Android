package android.day6.lab1.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    ImageView resultImg;
    EditText urlTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultImg = (ImageView) findViewById(R.id.imageView);
        urlTxt = (EditText) findViewById(R.id.editTextURL);
    }

    public void downloadImg(View view) {
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(urlTxt.getText().toString());
    }
    public Bitmap download(String url)throws IOException{
        Bitmap result = null;
        URL urlObj=null;
        HttpsURLConnection httpConn=null;
        InputStream inputStream=null;
        try {
            urlObj = new URL(url);
            httpConn = (HttpsURLConnection) urlObj.openConnection();
            httpConn.connect();
            inputStream=httpConn.getInputStream();
            result= BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream != null)
            {
                inputStream.close();
            }
        }
        return result;
    }
    public class MyAsyncTask extends AsyncTask<String,Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... uris) {
            Bitmap result = null;
            try {
                result = download(uris[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            resultImg.setImageBitmap(bitmap);
            Toast.makeText(MainActivity.this, "Download  Sucessfuly", Toast.LENGTH_SHORT).show();
        }

    }
}
