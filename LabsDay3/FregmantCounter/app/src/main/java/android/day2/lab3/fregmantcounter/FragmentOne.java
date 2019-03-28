package android.day2.lab3.fregmantcounter;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {


    Button btnCounter;
    int counter = 1;
    Communicator comm;

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mycount",counter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_fragment_one, container, false);
        if(savedInstanceState != null)
        {
            counter = savedInstanceState.getInt("mycount");
        }
        btnCounter=(Button) view.findViewById(R.id.btnCount);
        btnCounter.setOnClickListener((v) -> {
            int y = counter ++;
            comm.respond(y);
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm= (Communicator)getActivity();
    }

}
