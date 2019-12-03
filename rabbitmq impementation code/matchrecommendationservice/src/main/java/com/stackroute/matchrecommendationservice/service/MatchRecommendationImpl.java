package com.stackroute.matchrecommendationservice.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.matchrecommendationservice.domain.MatchCount;
import com.stackroute.matchrecommendationservice.exception.MatchAlreadyExistsException;
import com.stackroute.matchrecommendationservice.exception.MatchNotFoundException;
import com.stackroute.matchrecommendationservice.repository.MatchCountRepository;

import com.stackroute.rabbitmq.domain.MatchCountDTO;


@Service
public class MatchRecommendationImpl implements MatchRecommendationService{
    private MatchCountRepository matchCountRepository;

    @Autowired
    public MatchRecommendationImpl(MatchCountRepository matchCountRepository) {
        this.matchCountRepository = matchCountRepository;
    }

    @Override
    public boolean saveMatch(MatchCount match) throws MatchAlreadyExistsException {

        final Optional<MatchCount> matchObj = matchCountRepository.findById(match.getId());
        if (matchObj.isPresent()) {
            throw new MatchAlreadyExistsException("Could not save match. Match is already exists");
        }
        matchCountRepository.save(match);
        return true;
    }

    @Override
    public boolean updateMatch(MatchCount updateMatch) throws MatchNotFoundException {
        final Optional<MatchCount> matchOpt = matchCountRepository.findById(updateMatch.getId());

        if (matchOpt.isPresent()) {
            MatchCount match = matchOpt.get();
            match.setMatchStarted(updateMatch.isMatchStarted());
            matchCountRepository.save(match);
            return true;
        }
        else {
            throw new MatchNotFoundException("Could not update match. Match not found");
        }
    }

    @Override
    public boolean deleteMatchById(int id) throws MatchNotFoundException {
        final Optional<MatchCount> matchOpt = matchCountRepository.findById(id);

        if (matchOpt.isPresent()) {
            MatchCount match = matchOpt.get();
            matchCountRepository.delete(match);
            return true;
        }
        else {
            throw new MatchNotFoundException("Could not delete match. Match not found");
        }
    }

    @Override
    public MatchCount getMatchById(int id) throws MatchNotFoundException {
        final Optional<MatchCount> matchOpt = matchCountRepository.findById(id);
        if (matchOpt.isPresent()) {
            return matchOpt.get();
        }
        else {
            throw new MatchNotFoundException("Match not found");
        }
    }

    @Override
    public List<MatchCount> getAllMatches() {
        return matchCountRepository.findAll();
    }

    @Override
    public List<MatchCount> getMyMatches(String userId) {
        return matchCountRepository.findByUserId(userId);
    }
}
