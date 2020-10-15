package com.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "match_id")
    private Long matchID;

    private Date date;

    @Column(name = "home_team_id")
    private Long homeTeamID;

    @Column(name = "away_team_id")
    private Long awayTeamID;

    @Column(name = "goals_home")
    private int goalsHome;

    @Column(name = "goals_away")
    private int goalsAway;

    public Match(Long matchID, Date date, Long homeTeamID, Long awayTeamID, int goalsHome, int goalsAway) {
        this.matchID = matchID;
        this.date = date;
        this.homeTeamID = homeTeamID;
        this.awayTeamID = awayTeamID;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
    }

}
