package group.greenbyte.lunchplanner.team;

import group.greenbyte.lunchplanner.event.database.Event;
import group.greenbyte.lunchplanner.event.database.EventDatabase;
import group.greenbyte.lunchplanner.exceptions.DatabaseException;
import group.greenbyte.lunchplanner.team.database.TeamDatabase;
import group.greenbyte.lunchplanner.team.database.TeamMember;
import group.greenbyte.lunchplanner.user.UserDao;
import group.greenbyte.lunchplanner.user.database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import group.greenbyte.lunchplanner.team.database.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TeamDaoMySql implements TeamDao {

    private final UserDao userDao;
    private final JdbcTemplate jdbcTemplate;

    private static final String TEAM_TABLE = "team";
    private static final String TEAM_ID = "team_id";
    private static final String TEAM_NAME = "team_name";
    private static final String TEAM_DESCRIPTION = "description";
    private static final String TEAM_PUBLIC = "is_public";
    private static final String TEAM_PARENT = "parent_team";

    public static final String TEAM_MEMBER_TABLE = "team_member";
    public static final String TEAM_MEMBER_USER = "user_name";
    public static final String TEAM_MEMBER_TEAM = "team_id";
    public static final String TEAM_MEMBER_ADMIN = "is_admin";

    @Autowired
    public TeamDaoMySql(UserDao userDao, JdbcTemplate jdbcTemplate) {
        this.userDao = userDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertTeam(String teamName, String description, String adminName) throws DatabaseException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName(TEAM_TABLE).usingGeneratedKeyColumns(TEAM_ID);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(TEAM_NAME, teamName);
        parameters.put(TEAM_DESCRIPTION, description);

        try {
            Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

            addAdminToTeam(key.intValue(), adminName);

            return key.intValue();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public int insertTeamWithParent(String teamName, String description, String adminName, int parent) throws DatabaseException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName(TEAM_TABLE).usingGeneratedKeyColumns(TEAM_ID);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(TEAM_NAME, teamName);
        parameters.put(TEAM_DESCRIPTION, description);
        parameters.put(TEAM_PARENT, parent);

        try {
            Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

            addAdminToTeam(key.intValue(), adminName);

            return key.intValue();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Team getTeam(int teamId) throws DatabaseException {
        try {
            String SQL = "SELECT * FROM " + TEAM_TABLE + " WHERE " + TEAM_ID + " = ?";

            List<TeamDatabase> teams = jdbcTemplate.query(SQL,
                    new BeanPropertyRowMapper<>(TeamDatabase.class),
                    teamId);

            if (teams.size() == 0)
                return null;
            else {
                return teams.get(0).getTeam();
            }
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Team getTeamWithParent(int teamId) throws DatabaseException {
        try {
            String SQL = "SELECT * FROM " + TEAM_TABLE + " WHERE " + TEAM_ID + " = ?";

            List<TeamDatabase> teams = jdbcTemplate.query(SQL,
                    new BeanPropertyRowMapper<>(TeamDatabase.class),
                    teamId);

            if (teams.size() == 0)
                return null;
            else {
                Team team = teams.get(0).getTeam();
                if(teams.get(0).getParentTeam() != -1)
                    team.setParentTeam(getTeamWithParent(teams.get(0).getParentTeam()));

                return team;
            }
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addAdminToTeam(int teamId, String userName) throws DatabaseException {
        addUserToTeam(teamId, userName, true);
    }

    @Override
    public void changeUserToAdmin(int teamId, String userName) throws DatabaseException {
        String SQL = "UPDATE " + TEAM_MEMBER_TABLE + " SET " + TEAM_MEMBER_ADMIN + " = ? WHERE " + TEAM_MEMBER_USER +
                " = ? AND " + TEAM_MEMBER_TEAM + " = ?";

        try {
            jdbcTemplate.update(SQL,
                    true, userName, teamId);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addUserToTeam(int teamId, String userName) throws DatabaseException {
        addUserToTeam(teamId, userName, false);
    }

    @Override
    public boolean hasAdminPrivileges(int teamId, String userName) throws DatabaseException {
        try {
            String SQL = "SELECT count(*) FROM "  + TEAM_MEMBER_TABLE + " WHERE " +
                    TEAM_MEMBER_TEAM + " = ? AND " +
                    TEAM_MEMBER_USER + " = ? AND " +
                    TEAM_MEMBER_ADMIN + " = ?";

            int count = jdbcTemplate.queryForObject(SQL,
                    Integer.class,
                    teamId, userName, true);

            return count != 0;
        } catch (Exception e)  {
            throw new DatabaseException(e);
        }
    }

    @Override
    public boolean hasViewPrivileges(int teamId, String userName) throws DatabaseException {
        try {
            String SQL = "SELECT count(*) FROM "  + TEAM_MEMBER_TABLE + " WHERE " +
                    TEAM_MEMBER_TEAM + " = ? AND " +
                    TEAM_MEMBER_USER + " = ?";

            int count = jdbcTemplate.queryForObject(SQL,
                    Integer.class,
                    teamId, userName);

            return count != 0;
        } catch (Exception e)  {
            throw new DatabaseException(e);
        }
    }

    private void addUserToTeam(int teamId, String userName, boolean admin) throws DatabaseException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName(TEAM_MEMBER_TABLE);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(TEAM_MEMBER_USER, userName);
        parameters.put(TEAM_MEMBER_ADMIN, admin);
        parameters.put(TEAM_MEMBER_TEAM, teamId);

        try {
            simpleJdbcInsert.execute(new MapSqlParameterSource(parameters));
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }
}
