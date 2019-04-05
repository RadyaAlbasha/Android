package android.day8.lab1.downloadimages;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ShowImageActivity extends AppCompatActivity {

    ImageView imgView;
    Bitmap imgBitmap;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        imgView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        fileName = intent.getStringExtra("fileName");

        imgBitmap =loadImageFromStorage(fileName);
        //imgView.setImageBitmap(Bitmap.createScaledBitmap(imgBitmap, imgView.getWidth(),imgView.getHeight(), false));
        imgView.setImageBitmap(imgBitmap);
        receivImage(intent);
    }

   /* public byte[] loadInternalStorage(String _fileName)
    {
        byte[] byteArray =new byte[]{};
        try {
            DataInputStream dis = new DataInputStream(openFileInput(_fileName));
            dis.read(byteArray);
            dis.close();
            // Toast.makeText(ActivityTwo.this , "Load Sucessfuly" ,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public Bitmap convertByteArrayToBitmap(byte[] byteArrayImg)
    {
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArrayImg, 0, byteArrayImg.length);
        return bmp;
    }*/

    private Bitmap loadImageFromStorage(String path)
    {
        Bitmap bitmap=null;
        try {
            File file=new File(path, "profile.jpg");
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }
    //receiv image from another app
    private void receivImage(Intent intent)
    {
        String action = intent.getAction();
        String type = intent.getType();
        if(Intent.ACTION_SEND.equals(action) && type!=null)
        {
            if(type.startsWith("image/")){
                Uri imgUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
                if(imgUri!=null){
                    ImageView imgView = findViewById(R.id.imageView);
                    imgView.setImageURI(imgUri);
                    Log.i("testing incoming image ","oncreate ShowImageActivity : "+imgUri);
                }
            }
        }
    }

    public void shareImage(View view) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpeg");
        final File photoFile = new File(fileName, "profile.jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(photoFile));
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }
}
