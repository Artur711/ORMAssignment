package com.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long teamID;

    private String name;
    private String country;
    private String city;

    public Team(Long teamID, String name, String country, String city) {
        this.teamID = teamID;
        this.name = name;
        this.country = country;
        this.city = city;
    }
}
