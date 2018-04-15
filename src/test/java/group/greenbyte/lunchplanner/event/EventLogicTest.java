package group.greenbyte.lunchplanner.event;

import group.greenbyte.lunchplanner.AppConfig;
import group.greenbyte.lunchplanner.event.database.Event;
import group.greenbyte.lunchplanner.exceptions.HttpRequestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

import static group.greenbyte.lunchplanner.Utils.createString;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@ActiveProfiles("application-test.properties")
public class EventLogicTest {

    @Autowired
    private EventLogic eventLogic;

    // ------------------------- CREATE EVENT ------------------------------

    @Test
    public void test1createEventNoDescription() throws Exception{
        String userName = createString(50);
        String eventName = createString(50);
        String description = "";
        int locationId = 1;
        long timeStart = System.currentTimeMillis() + 10000;
        long timeEnd = timeStart + 10000;

        int result = eventLogic.createEvent(userName, eventName, description, locationId,
                new Date(timeStart), new Date (timeEnd));

    }

    @Test
    public void test2createEventLongDescription() throws Exception {
        String userName = createString(50);
        String eventName = createString(50);
        String description = createString(1000);
        int locationId = 1;
        long timeStart = System.currentTimeMillis() + 10000;
        long timeEnd = timeStart + 10000;

        int result = eventLogic.createEvent(userName, eventName, description, locationId,
                new Date(timeStart), new Date (timeEnd));
    }

    @Test(expected = HttpRequestException.class)
    public void test3createEventEmptyUserName() throws Exception {
        String userName = "";
        String eventName = createString(50);
        String description = "";
        int locationId = 1;
        long timeStart = System.currentTimeMillis() + 10000;
        long timeEnd = timeStart + 10000;

        int result = eventLogic.createEvent(userName, eventName, description, locationId,
                new Date(timeStart), new Date (timeEnd));
    }

    @Test(expected = HttpRequestException.class)
    public void test4createEventTooLongUserName() throws Exception {
        String userName = createString(51);
        String eventName = createString(50);
        String description = "";
        int locationId = 1;
        long timeStart = System.currentTimeMillis() + 10000;
        long timeEnd = timeStart + 10000;

        int result = eventLogic.createEvent(userName, eventName, description, locationId,
                new Date(timeStart), new Date (timeEnd));
    }

    @Test(expected = HttpRequestException.class)
    public void test5createEventEmptyEventName() throws Exception {
        String userName = createString(50);
        String eventName = "";
        String description = "";
        int locationId = 1;
        long timeStart = System.currentTimeMillis() + 10000;
        long timeEnd = timeStart + 10000;

        int result = eventLogic.createEvent(userName, eventName, description, locationId,
                new Date(timeStart), new Date (timeEnd));
    }

    @Test(expected = HttpRequestException.class)
    public void test6createEventTooLongEventName() throws Exception {
        String userName = createString(50);
        String eventName = createString(51);
        String description = "";
        int locationId = 1;
        long timeStart = System.currentTimeMillis() + 10000;
        long timeEnd = timeStart + 10000;

        int result = eventLogic.createEvent(userName, eventName, description, locationId,
                new Date(timeStart), new Date (timeEnd));
    }

    @Test(expected = HttpRequestException.class)
    public void test7createEventTooLongDescription() throws Exception {
        String userName = createString(50);
        String eventName = createString(50);
        String description = createString(1001);
        int locationId = 1;
        long timeStart = System.currentTimeMillis() + 10000;
        long timeEnd = timeStart + 10000;

        int result = eventLogic.createEvent(userName, eventName, description, locationId,
                new Date(timeStart), new Date (timeEnd));
    }

    @Test(expected = HttpRequestException.class)
    public void test8createEventTimeStartTooEarly() throws Exception {
        String userName = createString(50);
        String eventName = createString(50);
        String description = "";
        int locationId = 1;
        long timeStart = System.currentTimeMillis() - 10000;
        long timeEnd = timeStart + 10000;

        int result = eventLogic.createEvent(userName, eventName, description, locationId,
                new Date(timeStart), new Date (timeEnd));
    }

    @Test(expected = HttpRequestException.class)
    public void test4createEventTimeStartAfterTimeEnd() throws Exception {
        String userName = createString(50);
        String eventName = createString(50);
        String description = "";
        int locationId = 1;
        long timeStart = System.currentTimeMillis() + 10000;
        long timeEnd = timeStart - 10000;

        int result = eventLogic.createEvent(userName, eventName, description, locationId,
                new Date(timeStart), new Date (timeEnd));
    }


    // ------------------------- GET ALL EVENTS ------------------------------

    @Test(expected = HttpRequestException.class)
    public void test1getAllEventsEmptyUsername() throws Exception {
        String userName  = createString(0);
        String searchword = createString(0);

        List<Event> result = eventLogic.getAllEvents(userName, searchword);
    }

    @Test (expected = HttpRequestException.class)
    public void test2getAllEventsUsernameIsToLong()throws Exception{
        String userName = createString(51);
        String searchword = createString(0);

        List<Event> result = eventLogic.getAllEvents(userName, searchword);
    }

    @Test(expected = HttpRequestException.class)
    public void test3getAllEventsSearchwordIsToLong() throws Exception {
        String userName = createString(50);
        String searchword = createString(51);

        List<Event> result = eventLogic.getAllEvents(userName, searchword);
    }

    @Test(expected = HttpRequestException.class)
    public void test4getAllEventsSearchwordIsNull() throws Exception {
        String userName = createString(50);
        String searchword = null;

        List<Event> result = eventLogic.getAllEvents(userName, searchword);
    }

    @Test
    public void test5getAllEventsOk() throws Exception {
        String userName  = createString(50);
        String searchword = createString(0);

        List<Event> result = eventLogic.getAllEvents(userName, searchword);
    }

    // ------------------------- UPDATE EVENT DESCRIPTION ------------------------------

    @Test
    public void test1updateEventUsernameLengthNoDescription() throws Exception {
        String userName = createString(50);
        int eventId = 1;
        String description = createString(0);

        eventLogic.updateEventDescription(userName, eventId, description);
    }

    @Test
    public void test2updateEventUsernameLengthMaxLengthDescription() throws Exception {
        String userName = createString(50);
        int eventId = 1;
        String description = createString(1000);

        eventLogic.updateEventDescription(userName, eventId, description);
    }

    @Test (expected = HttpRequestException.class)
    public void test3updateEventEmptyUsernameMaxLengthDescription() throws Exception {
        String userName = createString(51);
        int eventId = 1;
        String description = createString(1000);

        eventLogic.updateEventDescription(userName, eventId, description);
    }

    @Test (expected = HttpRequestException.class)
    public void test4updateEventInvalidUsernameMaxLengthDescription() throws Exception {
        String userName = createString(51);
        int eventId = 1;
        String description = createString(1000);

        eventLogic.updateEventDescription(userName, eventId, description);
    }

    @Test (expected = HttpRequestException.class)
    public void test5updateEventUsernameInvalidDescription() throws Exception {
        String userName = createString(50);
        int eventId = 1;
        String description = createString(1001);

        eventLogic.updateEventDescription(userName, eventId, description);
    }
}