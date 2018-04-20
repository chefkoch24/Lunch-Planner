package group.greenbyte.lunchplanner.team;

import group.greenbyte.lunchplanner.AppConfig;
import group.greenbyte.lunchplanner.exceptions.HttpRequestException;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import group.greenbyte.lunchplanner.user.UserLogic;
import org.junit.Before;
>>>>>>> developement
=======
import group.greenbyte.lunchplanner.user.UserLogic;
import org.junit.Before;
>>>>>>> origin/jdbc-datenbank
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
<<<<<<< HEAD

import static group.greenbyte.lunchplanner.Utils.createString;
<<<<<<< HEAD
=======
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static group.greenbyte.lunchplanner.Utils.createString;
import static group.greenbyte.lunchplanner.event.Utils.createEvent;
import static group.greenbyte.lunchplanner.location.Utils.createLocation;
import static group.greenbyte.lunchplanner.team.Utils.createTeam;
import static group.greenbyte.lunchplanner.user.Utils.createUserIfNotExists;
>>>>>>> developement
=======
import static group.greenbyte.lunchplanner.team.Utils.createTeamWithoutParent;
import static group.greenbyte.lunchplanner.user.Utils.createUserIfNotExists;
>>>>>>> origin/jdbc-datenbank
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@ActiveProfiles("application-test.properties")
public class TeamLogicTest {

<<<<<<< HEAD
    @Autowired
    private TeamLogic teamLogic;

=======
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TeamLogic teamLogic;

    @Autowired
    private UserLogic userLogic;

    private String userName;
<<<<<<< HEAD
    private int teamId;
    private String userToInvite;
=======
    private int parent;
>>>>>>> origin/jdbc-datenbank

    @Before
    public void setUp() throws Exception {
        userName = createUserIfNotExists(userLogic, "dummy");
<<<<<<< HEAD
        userToInvite = createUserIfNotExists(userLogic, createString(50));
        teamId = createTeam(teamLogic, userName,0);



        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

>>>>>>> developement
=======
        parent = createTeamWithoutParent(teamLogic, userName, createString(10), createString(10));
    }

>>>>>>> origin/jdbc-datenbank
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

<<<<<<< HEAD

=======
    // ------------------------- INVITE TEAM MEMBER ------------------------------

    @Test
    public void test1InviteTeamMemberWithMinLength() throws Exception {
        String userName = createUserIfNotExists(userLogic, "A");
        String userToInvite = createUserIfNotExists(userLogic, "A");
        //int teamId = 1;

        teamLogic.inviteTeamMember(userName, userToInvite, teamId);
    }

    @Test
    public void test2InviteTeamMemberWithMaxLength() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(50));
        String userToInvite = createUserIfNotExists(userLogic, createString(50));

        teamLogic.inviteTeamMember(userName, userToInvite, teamId);
    }

    @Test(expected = HttpRequestException.class)
    public void test3InviteTeamMemberUserNameTooLong() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(51));
        String userToInvite = createUserIfNotExists(userLogic, createString(50));

        teamLogic.inviteTeamMember(userName, userToInvite, teamId);
    }

    @Test(expected = HttpRequestException.class)
    public void test4InviteTeamMemberWithNoUserName() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(0));
        String userToInvite = createUserIfNotExists(userLogic, createString(50));

        teamLogic.inviteTeamMember(userName, userToInvite, teamId);
    }

    @Test(expected = HttpRequestException.class)
    public void test5InviteTeamMemberUserToInviteTooLong() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(50));
        String userToInvite = createUserIfNotExists(userLogic, createString(51));

        teamLogic.inviteTeamMember(userName, userToInvite, teamId);
    }

    @Test(expected = HttpRequestException.class)
    public void test6InviteTeamMemberWithNoUserToInvite() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(50));
        String userToInvite = createUserIfNotExists(userLogic, createString(0));

        teamLogic.inviteTeamMember(userName, userToInvite, teamId);
    }
>>>>>>> developement
}