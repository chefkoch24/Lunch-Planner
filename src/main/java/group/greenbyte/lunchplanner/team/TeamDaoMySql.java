package group.greenbyte.lunchplanner.team;

import group.greenbyte.lunchplanner.exceptions.DatabaseException;
import group.greenbyte.lunchplanner.team.database.TeamDatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import group.greenbyte.lunchplanner.exceptions.DatabaseException;
import group.greenbyte.lunchplanner.team.database.Team;

@Repository
public class TeamDaoMySql implements TeamDao {

    private TeamDatabaseConnector tdc;
    @Override
    public int insertTeam(String teamName, String description, String adminName, int parent) throws DatabaseException {
        Team team = new Team();
        team.setTeamName(teamName);
        team.setDescription(description);
        team.setParent(parent);

        //TODO adminName
        //TODO parent object or parent Integer?

        try {
            return tdc.save(team).getTeamId();
        } catch(Exception e){
            throw new DatabaseException();
        }

    }

    @Override
    public Team getTeam(int teamId) throws DatabaseException {
        return null;
    }

    @Autowired
    public void setTdc(TeamDatabaseConnector tdc) {
        this.tdc = tdc;
    }
}
