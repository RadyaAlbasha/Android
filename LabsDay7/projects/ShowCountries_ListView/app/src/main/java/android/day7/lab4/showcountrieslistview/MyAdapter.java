package android.day7.lab4.showcountrieslistview;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class MyAdapter extends ArrayAdapter {

    List<Country> countries;
    Context context;
    int resource;
    Bitmap imgFlag;

    public MyAdapter(Context _context, List _countries) {
        super(_context, R.layout.single_row,R.id.textViewCountry, _countries);
        countries = _countries;
        context = _context;
        resource = R.layout.single_row;
    }

    public Bitmap getImgFlag() {
        return imgFlag;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup listView) {
        View row = convertView;
        ViewHolder viewHolder; // represent single_row data
        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(resource,listView,false);
            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) row.getTag();
        }
        viewHolder.setUrlImg(countries.get(position).getFlag());
        viewHolder.getImgIcon().setImageBitmap(countries.get(position).getImgFlag());
        //Log.i("in adapter",String.valueOf(countries.get(position).getImgFlag()==null));
     //   viewHolder.getImgIcon();
      //  imgFlag= viewHolder.getImgFlagBitmap();
        viewHolder.getTxtCountry().setText(countries.get(position).getCountry());
        return row;
    }
}
