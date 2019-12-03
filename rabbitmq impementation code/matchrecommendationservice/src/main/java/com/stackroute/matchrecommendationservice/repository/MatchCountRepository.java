package com.stackroute.matchrecommendationservice.repository;


import com.stackroute.matchrecommendationservice.domain.MatchCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Map;

@RepositoryRestResource
public interface MatchCountRepository extends JpaRepository<MatchCount, Integer> {

    List<MatchCount> findByUserId(String userId);

    @Query(value = "SELECT unique_id, date, match_started, team1, team2, count(unique_id) AS weight FROM cricket_match GROUP BY unique_id, date, match_started, team1, team2", nativeQuery = true)
    public List<Map<String,Object>> findRecommendations();
}