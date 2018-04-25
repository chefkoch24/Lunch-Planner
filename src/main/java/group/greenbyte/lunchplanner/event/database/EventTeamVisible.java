package group.greenbyte.lunchplanner.event.database;

import group.greenbyte.lunchplanner.team.database.Team;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EventTeamVisible implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "eventId")
    private Event event;

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "teamId")
    private Team team;
}
