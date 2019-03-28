package android.day7.lab3.complexlistviewdays;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class MyAdapter extends ArrayAdapter {

    List<Day> days;
    Context context;
    int resource;

    public MyAdapter(Context _context,List _days) {
        super(_context, R.layout.single_row,R.id.textViewName, _days);
        days = _days;
        context = _context;
        resource = R.layout.single_row;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup listView) {
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(resource,listView,false);
        ImageView imgView = row.findViewById(R.id.imageViewIcon);
        TextView txtName = row.findViewById(R.id.textViewName);
        TextView txtDesc = row.findViewById(R.id.textViewDesc);
        imgView.setImageResource(days.get(position).getImageRes());
        txtName.setText(days.get(position).getDayName());
        txtDesc.setText(days.get(position).getDayDesc());
        return row;
    }
}
