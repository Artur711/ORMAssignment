package com.example.service;

import com.example.model.Match;
import com.example.model.Team;
import com.example.repository.MatchRepository;
import com.example.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.*;
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
        Map<Team, Integer> map = teamAndTheirGoals();
        return map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
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
        List<Team> teamList = teamRepository.findAll();
        return teamList.stream().filter(team -> matchRepository.countByHomeTeamIDOrAwayTeamID(team.getTeamID()) > 1).collect(Collectors.toList());
    }

    public Map<Team, Integer> teamAndTheirGoals() {
        List<Team> teamList = teamRepository.findAll();
        Map<Team, Integer> map = new HashMap<>();

        for (Team team : teamList) {
            List<Match> matchList = matchRepository.findByHomeTeamIDOrAndAwayTeamID(team.getTeamID());
            Integer homeGoals = matchList.stream().filter(match -> match.getHomeTeamID() == team.getTeamID()).mapToInt(match1 -> match1.getGoalsHome()).sum();
            Integer awayGoals = matchList.stream().filter(match -> match.getAwayTeamID() == team.getTeamID()).mapToInt(match1 -> match1.getGoalsAway()).sum();
            map.put(team, homeGoals + awayGoals);
        }
        return map;
    }

    public List<Match> matchesLastWeek() {
        List<Match> matchList = matchRepository.findAll();
        Long weekInMs = 1209600000L;
        Date date = new Date();
        return matchList.stream().filter(match -> date.getTime() - match.getDate().getTime() < weekInMs).collect(Collectors.toList());
    }
}
