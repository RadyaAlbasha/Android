package android.day7.lab4.showcountrieslistview;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class Country implements Parcelable {
    private String rank;
    private String country;
    private String population;
    private String flag;
    private Bitmap imgFlag;

    public Country() {

    }

    public Country(String rank, String country, String population, String flag) {
        this.rank = rank;
        this.country = country;
        this.population = population;
        this.flag = flag;
    }

    protected Country(Parcel in) {
        rank = in.readString();
        country = in.readString();
        population = in.readString();
        flag = in.readString();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Bitmap getImgFlag() {
        return imgFlag;
    }

    public void setImgFlag(Bitmap imgFlag) {
        this.imgFlag = imgFlag;
    }

    @Override
    public String toString() {
        return  rank + '\n' +
                country + '\n' +
                population + '\n' +
                flag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.rank);
        dest.writeString(this.country);
        dest.writeString(this.population);
        dest.writeString(this.flag);
    }
}
