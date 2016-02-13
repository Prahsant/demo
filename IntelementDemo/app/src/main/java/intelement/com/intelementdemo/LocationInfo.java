package intelement.com.intelementdemo;

/**
 * Created by prashantp on 12/2/16.
 */
public class LocationInfo {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromCentral() {
        return fromCentral;
    }

    public void setFromCentral(String fromCentral) {
        this.fromCentral = fromCentral;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private String name;
    private int id;
    private String fromCentral;
    private double latitude;
    private double longitude;
}
