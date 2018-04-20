package group.greenbyte.lunchplanner.event.database;

import org.springframework.data.repository.CrudRepository;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> developement

public interface EventDatabaseConnector extends CrudRepository<Event, Integer> {

    Event getByEventId(int eventId);

}
