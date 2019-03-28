package android.day7.lab4.showcountrieslistview;

import android.content.Intent;

public interface Communicator {
    public Country getCurrentCountry(Country c);
    public int getOrientation();
    public Intent getCurrentIntent();
}
