package group.greenbyte.lunchplanner.team;

import group.greenbyte.lunchplanner.exceptions.DatabaseException;
import group.greenbyte.lunchplanner.team.database.Team;

public interface TeamDao {

    /**
     *
     * @param teamName name of the team
     * @param description description of the team
     * @param adminName creator of the team
     * @return returns the teamId created by the database
     * @throws DatabaseException
     */
    int insertTeam(String teamName, String description, String adminName) throws DatabaseException;

    /**
     *
     * @param teamName name of the team
     * @param description description of the team
     * @param adminName creator of the team
     * @param parent parent of the team
     * @return returns the teamId created by the database
     * @throws DatabaseException
     */
    int insertTeamWithParent(String teamName, String description, String adminName, int parent) throws DatabaseException;


    /**
     *
     *
     * @param teamId id of the team
     * @return returns a team object wihtout data for the relations (as parent, ...)
     * @throws DatabaseException
     */
    Team getTeam(int teamId) throws DatabaseException;

<<<<<<< HEAD

    /**
     * Insert an new invited user into a team
     *
     * @param userToInvite id of the user who is invited
     * @param teamId id of the event
     * @return the team of the teamID
     *
     * @throws DatabaseException when an unexpected error happens
     */
    Team putUserTeamMember(String userToInvite, int teamId) throws DatabaseException;
=======
    /**
     *  Gets the team object with his parent objects
     *
     * @param teamId id of the team
     * @return returns a team object
     * @throws DatabaseException
     */
    Team getTeamWithParent(int teamId) throws DatabaseException;

    /**
     * Adds an admin to the existing team. The user wasnt in the team before
     *
     * @param teamId id of the team
     * @param userName user who is admin
     * @throws DatabaseException
     */
    void addAdminToTeam(int teamId, String userName) throws DatabaseException;

    /**
     * Changes the role of a normal user to a admin role
     *
     * @param teamId
     * @param userName
     * @throws DatabaseException
     */
    void changeUserToAdmin(int teamId, String userName) throws DatabaseException;

    /**
     * Adds an user to the existing team
     *
     * @param teamId id of the team
     * @param userName user who is admin
     * @throws DatabaseException
     */
    void addUserToTeam(int teamId, String userName) throws DatabaseException;

>>>>>>> origin/jdbc-datenbank
}
