package com.stackroute.rabbitmq.domain;

public class MatchCountDTO {

    private int unique_id;
    private String teamOne;
    private String teamTwo;
    private String matchDate;
    private boolean matchStarted;
    private String userId;
    private String weight;

    public MatchCountDTO() {
    }

    public MatchCountDTO(int unique_id, String teamOne, String teamTwo, String matchDate, boolean matchStarted, String userId, String weight) {
        this.unique_id = unique_id;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.matchDate = matchDate;
        this.matchStarted = matchStarted;
        this.userId = userId;
        this.weight = weight;
    }

    public int getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(int unique_id) {
        this.unique_id = unique_id;
    }

    public String getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }

    public String getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(String teamTwo) {
        this.teamTwo = teamTwo;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public boolean isMatchStarted() {
        return matchStarted;
    }

    public void setMatchStarted(boolean matchStarted) {
        this.matchStarted = matchStarted;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}

