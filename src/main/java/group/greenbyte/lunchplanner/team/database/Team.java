package group.greenbyte.lunchplanner.team.database;

import javax.persistence.*;

@Entity
public class Team {

    static final public int MAX_NAME_LENGTH = 50;
    static final public int MAX_DESCRIPION_LENGTH = 1000;
    static final public int MAX_USERNAME_LENGTH = 50;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer teamId;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    private String teamName;

    @Column(length = MAX_DESCRIPION_LENGTH)
    private String description;

    @Column(nullable = false, length = MAX_USERNAME_LENGTH)
    private String adminName;

    @Column
    private Integer parent;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
