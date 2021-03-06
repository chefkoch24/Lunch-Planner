package group.greenbyte.lunchplanner.location.database;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Coordinate {

    @Column
    private double xCoordinate;

    @Column
    private double yCoordinate;

    public Coordinate(double x, double y) {
        xCoordinate = x;
        yCoordinate = y;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }
}
