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

    @Column
    private Team parent;
    
}
