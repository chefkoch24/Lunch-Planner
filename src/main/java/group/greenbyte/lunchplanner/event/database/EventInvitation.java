package group.greenbyte.lunchplanner.event.database;

import group.greenbyte.lunchplanner.event.InvitationAnswer;
import group.greenbyte.lunchplanner.user.database.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EventInvitation implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "eventId")
    private Event eventInvited;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userName")
    private User userInvited;

    private boolean isAdmin;

    private InvitationAnswer answer;
}
