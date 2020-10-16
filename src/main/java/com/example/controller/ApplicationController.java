package com.example.controller;

import com.example.service.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    private ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @GetMapping("/menu")
    public String getMenu() {
        return "menu";
    }

    @GetMapping("/all")
    public String getTeamsAndMatches(Model model) {
        model.addAttribute("teams", service.getAllTeam());
        model.addAttribute("matches", service.getAllMatch());
        return "all";
    }

    @GetMapping("/best")
    public String getBestTeam(Model model) {
        model.addAttribute("team", service.bestTeam());
        return "best";
    }

    @GetMapping("/fc")
    public String getTeamsContainsFC(Model model) {
        model.addAttribute("teamsfc", service.teamsContainsFC());
        return "fc";
    }

    @GetMapping("/match")
    public String getMatchWithMostGoals(Model model) {
        model.addAttribute("matches", service.matchWithMostGoals());
        return "match";
    }

    @GetMapping("/more_than_one")
    public String getTeamsPlayedMoreThanOne(Model model) {
        model.addAttribute("teams", service.teamPlayedMoreOne());
        return "more_than_one";
    }

    @GetMapping("/total_goals")
    public String getTeamsTotalGoals(Model model) {
        model.addAttribute("mapTeams", service.teamAndTheirGoals());
        return "total_goals";
    }

    @GetMapping("/last_week")
    public String getMatchFromLastWeek(Model model) {
        model.addAttribute("matches", service.matchesLastWeek());
        return "last_week";
    }
}
