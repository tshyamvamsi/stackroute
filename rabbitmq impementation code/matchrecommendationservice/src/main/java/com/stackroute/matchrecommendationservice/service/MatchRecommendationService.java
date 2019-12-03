package com.stackroute.matchrecommendationservice.service;

import com.stackroute.matchrecommendationservice.domain.MatchCount;
import com.stackroute.matchrecommendationservice.exception.MatchAlreadyExistsException;
import com.stackroute.matchrecommendationservice.exception.MatchNotFoundException;

import java.util.List;

public interface MatchRecommendationService {

    boolean saveMatch(MatchCount match) throws MatchAlreadyExistsException;

    boolean updateMatch(MatchCount match) throws MatchNotFoundException;

    boolean deleteMatchById(int id) throws MatchNotFoundException;

    MatchCount getMatchById(int id) throws MatchNotFoundException;

    List<MatchCount> getAllMatches();

    List<MatchCount> getMyMatches(String userId);
}
