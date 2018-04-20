package group.greenbyte.lunchplanner.team;

import group.greenbyte.lunchplanner.AppConfig;
import group.greenbyte.lunchplanner.exceptions.HttpRequestException;
import group.greenbyte.lunchplanner.user.UserLogic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static group.greenbyte.lunchplanner.Utils.createString;
import static group.greenbyte.lunchplanner.team.Utils.createTeamWithoutParent;
import static group.greenbyte.lunchplanner.user.Utils.createUserIfNotExists;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@ActiveProfiles("application-test.properties")
public class TeamLogicTest {

    @Autowired
    private TeamLogic teamLogic;

    @Autowired
    private UserLogic userLogic;

    private String userName;
    private int parent;

    @Before
    public void setUp() throws Exception {
        userName = createUserIfNotExists(userLogic, "dummy");
        parent = createTeamWithoutParent(teamLogic, userName, createString(10), createString(10));
    }

    // ------------------------- CREATE TEAM ------------------------------

    @Test
    public void test1CreateTeamWithNoDescription() throws Exception {
        String teamName = "A";
        String description = "";

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
    }

    @Test
    public void test2CreateTeamWithNormalDescriptionMaxUserNameMaxTeamName() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(50));
        String teamName = createString(50);
        String description = "Super Team";

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
    }

    @Test
    public void test3CreateTeamWithMaxDescriptionMaxUserNameMaxTeamName() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(50));
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test4CreateTeamWithNoUserName() throws Exception {
        String userName = "";
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test5CreateTeamUserNameTooLong() throws Exception {
        String userName = createString(51);
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test6CreateTeamWithNoTeamName() throws Exception {
        String teamName = "";
        String description = createString(1000);

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test7CreateTeamTeamNameTooLong() throws Exception {
        String teamName = createString(51);
        String description = createString(1000);

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test6CreateTeamDescriptionTooLong() throws Exception {
        String teamName = createString(50);
        String description = createString(1001);

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
    }


}