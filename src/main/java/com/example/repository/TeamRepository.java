package com.example.repository;

import com.example.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {

    List<Team> findAll();

    Team findByTeamID(Long id);
}
