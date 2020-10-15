package com.example.service;

import com.example.model.Match;
import com.example.model.Team;
import com.example.repository.MatchRepository;
import com.example.repository.TeamRepository;
import org.h2.value.CaseInsensitiveMap;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public ApplicationService(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    public List<Team> getAllTeam() {
        return teamRepository.findAll();
    }

    public List<Match> getAllMatch() {
        return matchRepository.findAll();
    }

    public Team bestTeam() {
        List<Match> matchList = matchRepository.findAll();
//        Map<Long, Integer> homeGoals = matchList.stream().collect(Collectors.toMap((Match::getHomeTeamID, Match::getGoalsHome));
//        Map<Long, Integer> awayGoals = matchList.stream().collect(Collectors.toMap(Match::getAwayTeamID, Match::getGoalsAway));


//        Map<Long, Match> goalsScored = matchList.stream().collect(Collectors.toMap(Match::getHomeTeamID, Match::getHomeTeamID));

        Long id = 1L;
        return teamRepository.findByTeamID(id);
    }

    public List<Team> teamsContainsFC() {
        List<Team> teamList = teamRepository.findAll();
        return teamList.stream().filter(team -> team.getName().contains("FC")).collect(Collectors.toList());
    }

    public List<Match> matchWithMostGoals() {
        List<Match> matchList = matchRepository.findAll();
        Integer bestScore = matchList.stream()
                                     .mapToInt(match -> match.getGoalsHome() + match.getGoalsAway())
                                     .max()
                                     .orElseThrow(NoSuchElementException::new);

        return matchList.stream()
                        .filter(match -> (match.getGoalsHome() + match.getGoalsAway()) == bestScore)
                        .collect(Collectors.toList());
    }

    public List<Team> teamPlayedMoreOne() {

        return teamRepository.findAll();
    }

    public Map<String, Integer> teamAndTheirGoals() {
        List<Match> matchList = matchRepository.findAll();
        List<Team> teamList = teamRepository.findAll();

//        Map<Long, Integer> map = matchList.stream().flatMap();

        return new HashMap<>();
    }

    public List<Match> matchesLastWeek() {
        return matchRepository.findAll();
    }
}
