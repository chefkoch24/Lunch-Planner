package group.greenbyte.lunchplanner.team;

import group.greenbyte.lunchplanner.exceptions.DatabaseException;
import group.greenbyte.lunchplanner.exceptions.HttpRequestException;
import group.greenbyte.lunchplanner.location.database.Coordinate;
import group.greenbyte.lunchplanner.location.database.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TeamLogic {

    private TeamDao teamdao;
    /**
     * Create an event. At least the eventName and a location or timeStart is needed
     *
     * @param userName userName that is logged in
     * @param parent parent of the new team
     * @param teamName name of the new team
     * @param description description of the new location
     * @return the id of the new team
     * @throws HttpRequestException when teamName, userName, description not valid
     * or an Database error happens
     */

    int createTeam(String userName, int parent, String teamName, String description) throws HttpRequestException {


    }

    @Autowired
    public void setTeamdao(TeamDao teamdao) {
        this.teamdao = teamdao;
    }
}
