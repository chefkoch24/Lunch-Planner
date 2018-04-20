package group.greenbyte.lunchplanner.event;

import group.greenbyte.lunchplanner.event.database.Event;
import group.greenbyte.lunchplanner.exceptions.DatabaseException;
import org.hibernate.dialect.Database;

import java.util.Date;
import java.util.List;

public interface EventDao {

    /**
     * Insert an event into the database
     *
     * @param userName id of the user who creates the events
     * @param eventName name of the event
     * @param description description of the event
     * @param locationId id of the location
     * @param timeStart time when the event starts
     * @param timeEnd time when the events ends
     * @return the inserted Event
     *
     * @throws DatabaseException when an unexpected error happens
     */
    Event insertEvent(String userName,
                      String eventName,
                      String description,
                      int locationId,
                      Date timeStart,
                      Date timeEnd) throws DatabaseException;

    /**
     * Get all events for a specific searchword
     *
     * @param username id of the user who creates the events
     * @param searchword word for which is searched
     * @return List of events with this searchword
     *
     * @throws DatabaseException when an unexpected error happens
     */
    List<Event> search(String username,
                       String searchword)throws DatabaseException;

    /**
     * Gets the event with location but without usersInvited and teamsVisible
     *
     * @param eventId id of the event
     * @return the searched event
     * @throws DatabaseException when an unexpected error happens
     */
    Event getEvent(int eventId) throws DatabaseException;

    /**
     *
     * @param eventId id of the event
     * @param eventName name of the event
     * @return the updated event
     * @throws DatabaseException
     */
    Event updateEventName(int eventId,
                          String eventName) throws DatabaseException;


    /**
     *
     * @param eventId id of the event
     * @param description description of the event
     * @return the updated event
     * @throws DatabaseException when an unexpected error happens
     */
    Event updateEventDescription(int eventId,
                                 String description
                                 ) throws DatabaseException;

    /**
     *
     * @param eventId id of the event
     * @param locationId id of the location
     * @return the updated event
     * @throws DatabaseException when an unexpected error happens
     */
    Event updateEventLocation(int eventId,
                              int locationId) throws DatabaseException;

    /**
     *
     * @param eventId id of the event
     * @param timeStart time when the event starts
     * @return the updated event
     * @throws DatabaseException when an unexpected error happens
     */
    Event updateEventTimeStart(int eventId,
                               Date timeStart) throws DatabaseException;

    /**
     *
     * @param eventId id of the event
     * @param timeEnd time when the events ends
     * @return the updated event
     * @throws DatabaseException when an unexpected error happens
     */
    Event updateEventTimeEnd(int  eventId,
                             Date timeEnd) throws DatabaseException;

    /**
     * Insert an new invited user into an event
     *
     * @param userToInviteName id of the user who is invited
     * @param eventId id of the event
     * @return the Event of the invitation
     *
     * @throws DatabaseException when an unexpected error happens
     */
    Event putUserInviteToEvent (String userToInviteName, int eventId) throws DatabaseException;

    /**
     * Insert an new invited user into an event as admin role
     *
     * @param userToInviteName id of the user who is invited
     * @param eventId id of the event
     * @return the Event of the invitation
     *
     * @throws DatabaseException when an unexpected error happens
     */
    Event putUserInviteToEventAsAdmin (String userToInviteName, int eventId) throws DatabaseException;
}
