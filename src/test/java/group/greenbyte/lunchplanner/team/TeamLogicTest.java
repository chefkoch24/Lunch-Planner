package group.greenbyte.lunchplanner.team;

import group.greenbyte.lunchplanner.AppConfig;
import group.greenbyte.lunchplanner.exceptions.HttpRequestException;
<<<<<<< HEAD
=======
import group.greenbyte.lunchplanner.user.UserLogic;
import org.junit.Before;
>>>>>>> developement
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
<<<<<<< HEAD

import static group.greenbyte.lunchplanner.Utils.createString;
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
    private int teamId;
    private String userToInvite;

    @Before
    public void setUp() throws Exception {
        userName = createUserIfNotExists(userLogic, "dummy");
        userToInvite = createUserIfNotExists(userLogic, createString(50));
        teamId = createTeam(teamLogic, userName,0);



        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

>>>>>>> developement
    // ------------------------- CREATE TEAM ------------------------------

    @Test
    public void test1CreateTeamWithNoDescription() throws Exception {
        String userName = "A";
        int parent = 1;
        String teamName = "A";
        String description = "";

        teamLogic.createTeam(userName, parent, teamName, description);
    }

    @Test
    public void test2CreateTeamWithNormalDescriptionMaxUserNameMaxTeamName() throws Exception {
        String userName = createString(50);
        int parent = 1;
        String teamName = createString(50);
        String description = "Super Team";

        teamLogic.createTeam(userName, parent, teamName, description);
    }

    @Test
    public void test3CreateTeamWithMaxDescriptionMaxUserNameMaxTeamName() throws Exception {
        String userName = createString(50);
        int parent = 1;
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeam(userName, parent, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test4CreateTeamWithNoUserName() throws Exception {
        String userName = "";
        int parent = 1;
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeam(userName, parent, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test5CreateTeamUserNameTooLong() throws Exception {
        String userName = createString(51);
        int parent = 1;
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeam(userName, parent, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test6CreateTeamWithNoTeamName() throws Exception {
        String userName = createString(50);
        int parent = 1;
        String teamName = "";
        String description = createString(1000);

        teamLogic.createTeam(userName, parent, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test7CreateTeamTeamNameTooLong() throws Exception {
        String userName = createString(50);
        int parent = 1;
        String teamName = createString(51);
        String description = createString(1000);

        teamLogic.createTeam(userName, parent, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test6CreateTeamDescriptionTooLong() throws Exception {
        String userName = createString(50);
        int parent = 1;
        String teamName = createString(50);
        String description = createString(1001);

        teamLogic.createTeam(userName, parent, teamName, description);
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