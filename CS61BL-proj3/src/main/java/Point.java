/**
 * Created by rober on 7/21/2016.
 */
public class Point {
    private double latitude;
    private double longitude;

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Point(Point in) {
        this.latitude = in.getLatitude();
        this.longitude = in.getLongitude();
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Point average(Point node) {
        double newlatitude = (latitude + node.getLatitude()) * 0.5;
        double newlongitude = (longitude + node.getLongitude()) * 0.5;
        Point result = new Point(newlatitude, newlongitude);
        return result;
    }

    //inside returns true if test is inside range of upper and lower
    public static boolean isInside(Point test, Point upper, Point lower) {
        if (test.getLatitude() >= lower.getLatitude()
                && test.getLatitude() <= upper.getLatitude()
                && test.getLongitude() >= upper.getLongitude()
                && test.getLongitude() <= lower.getLongitude()) {
            return true;
        }
        return false;
    }
}
