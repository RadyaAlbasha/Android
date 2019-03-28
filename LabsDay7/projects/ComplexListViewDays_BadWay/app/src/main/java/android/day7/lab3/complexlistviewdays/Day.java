package android.day7.lab3.complexlistviewdays;

public class Day {
    String dayName;
    String dayDesc;
    int imageRes;

    public Day(String dayName, String dayDesc, int imageRes) {
        this.dayName = dayName;
        this.dayDesc = dayDesc;
        this.imageRes = imageRes;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getDayDesc() {
        return dayDesc;
    }

    public void setDayDesc(String dayDesc) {
        this.dayDesc = dayDesc;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    @Override
    public String toString() {
        return  dayName + '\n' +
                dayDesc;
    }
}
