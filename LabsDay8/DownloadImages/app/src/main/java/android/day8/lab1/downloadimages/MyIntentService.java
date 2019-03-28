package android.day8.lab1.downloadimages;

import android.app.IntentService;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {

    private String imgURL;
    Bitmap resultBitmap;
    MyReceiver myReceiver;
    public MyIntentService()
    {
        super("MyIntentService");
        imgURL = null;
        resultBitmap = null;
        myReceiver = new MyReceiver();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter registerFilter = new IntentFilter("Action_GetImage");
        registerReceiver(myReceiver,registerFilter);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
         imgURL=intent.getStringExtra("imgUrl");
        try {
            resultBitmap = download(imgURL);
             String fileName = saveToInternalStorage(resultBitmap);
            broadCastIntent(fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    public Bitmap download(String url)throws IOException {
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

    /*
    public void saveInternalStorage(Bitmap _imgBitmap){
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            _imgBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            DataOutputStream dos = new DataOutputStream(openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
            dos.write(byteArray);
            dos.flush();
            dos.close();

            //Toast.makeText(ActivityTwo.this , "Save Sucessfuly" ,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    private String saveToInternalStorage(Bitmap bitmapImage)
    {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    public void  broadCastIntent(String fileName)
    {
        Intent intent = new Intent();
        intent.setAction("Action_GetImage");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("fileName",fileName);
        sendBroadcast(intent);
    }

}
