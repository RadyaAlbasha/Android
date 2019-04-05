package android.firebase.labs.worldpopulationretrofit.model;

import com.google.gson.annotations.SerializedName;

public class WorldPopulation {
    @SerializedName("rank")
    private String rank;
    @SerializedName("country")
    private String country;
    @SerializedName("population")
    private String population;
    @SerializedName("flag")
    private String flag;

    public WorldPopulation() {
    }

    public WorldPopulation(String rank, String country, String population, String flag) {
        this.rank = rank;
        this.country = country;
        this.population = population;
        this.flag = flag;
    }

    public String getRank() {
        return rank;
    }

    public String getCountry() {
        return country;
    }

    public String getPopulation() {
        return population;
    }

    public String getFlag() {
        return flag;
    }


    @Override
    public String toString() {
       String countryStr = rank +"\n"+country +"\n"+population;
       return countryStr;
    }
}
