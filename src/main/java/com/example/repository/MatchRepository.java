package com.example.repository;

import com.example.model.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> findAll();

    @Query("SELECT m FROM Match m where m.homeTeamID = :ID or m.awayTeamID = :ID")
    List<Match> findByHomeTeamIDOrAndAwayTeamID(@Param("ID") Long id);
}
