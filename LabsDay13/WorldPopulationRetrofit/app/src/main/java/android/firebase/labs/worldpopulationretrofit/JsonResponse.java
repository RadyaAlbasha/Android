package android.firebase.labs.worldpopulationretrofit;

import android.firebase.labs.worldpopulationretrofit.model.WorldPopulation;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JsonResponse {
    @SerializedName("worldpopulation")
    ArrayList<WorldPopulation> worldpopulation;

    public ArrayList<WorldPopulation> getWorldpopulation() {
        return worldpopulation;
    }
}
