package android.day8.lab1.downloadimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ShowImageActivity extends AppCompatActivity {

    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        imgView = findViewById(R.id.imageView);
        String fileName = getIntent().getStringExtra("fileName");

        Bitmap imgBitmap =loadImageFromStorage(fileName);
        //imgView.setImageBitmap(Bitmap.createScaledBitmap(imgBitmap, imgView.getWidth(),imgView.getHeight(), false));
        imgView.setImageBitmap(imgBitmap);
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
}
