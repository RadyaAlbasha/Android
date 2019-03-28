package android.day7.lab4.showcountrieslistview;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    private View currentView;//my row
    private ImageView imgIcon;
    private TextView txtCountry;
    private String urlImg;
    private Bitmap ImgFlagBitmap;

    public ViewHolder(View currentView) {
        this.currentView = currentView;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public Bitmap getImgFlagBitmap() {
        return ImgFlagBitmap;
    }

    public ImageView getImgIcon() {
        if(imgIcon == null)
        {
            imgIcon = currentView.findViewById(R.id.imageViewIcon);
           /* MyAsyncTask myAsyncTask = new MyAsyncTask(currentView.getContext(),imgIcon);
            myAsyncTask.execute(urlImg);
            ImgFlagBitmap = myAsyncTask.getImgFlagBitmap();*/

        }
        return imgIcon;
    }

    public TextView getTxtCountry() {
        if(txtCountry == null)
            txtCountry = currentView.findViewById(R.id.txtCountry);
        return txtCountry;
    }
}
