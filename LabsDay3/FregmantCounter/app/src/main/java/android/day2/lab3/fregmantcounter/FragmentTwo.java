package android.day2.lab3.fregmantcounter;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    TextView txtCounter;
    int saveCount;
    View view;
    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_two, container, false);
        txtCounter = (TextView) view.findViewById(R.id.textViewConter);
        if(savedInstanceState != null)
        {
            txtCounter.setText(String.valueOf(savedInstanceState.getInt("mycount")));
        }
        return view;
    }
    public void changeData(int data){
        txtCounter.setText(Integer.toString(data));
        saveCount = data;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mycount",saveCount);
    }

}
