package android.day8.lab1.downloadimages;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MyReceiver extends BroadcastReceiver {

    String fileName;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
       // throw new UnsupportedOperationException("Not yet implemented");
        if(intent.getAction().equals("Action_GetImage"))
        {
            fileName = intent.getStringExtra("fileName");
            Intent intentSend = new Intent(context,ShowImageActivity.class);
            intentSend.putExtra("fileName",fileName);
            intentSend.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentSend );
        }
    }

}
