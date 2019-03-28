package android.day7.lab3.complexlistviewdays_goodway;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    private View currentView;//my row
    private ImageView imgIcon;
    private TextView txtName;
    private TextView txtDesc;

    public ViewHolder(View currentView) {
        this.currentView = currentView;
    }

    public ImageView getImgIcon() {
        if(imgIcon == null)
            imgIcon = currentView.findViewById(R.id.imageViewIcon);
        return imgIcon;
    }

    public TextView getTxtName() {
        if(txtName == null)
            txtName = currentView.findViewById(R.id.textViewName);
        return txtName;
    }

    public TextView getTxtDesc() {
        if(txtDesc == null)
            txtDesc = currentView.findViewById(R.id.textViewDesc);
        return txtDesc;
    }
}
