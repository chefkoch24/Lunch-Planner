package group.greenbyte.lunchplanner.location.database;

import group.greenbyte.lunchplanner.user.database.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class LocationAdmin implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="locationId")
    private Location location;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userName")
    private User userAdmin;
}
