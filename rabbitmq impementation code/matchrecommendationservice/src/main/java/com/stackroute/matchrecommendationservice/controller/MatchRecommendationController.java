package com.stackroute.matchrecommendationservice.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stackroute.matchrecommendationservice.repository.MatchCountRepository;
import com.stackroute.matchrecommendationservice.service.MatchRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.matchrecommendationservice.domain.MatchCount;
import com.stackroute.matchrecommendationservice.exception.MatchAlreadyExistsException;
import com.stackroute.matchrecommendationservice.exception.MatchNotFoundException;
import com.stackroute.matchrecommendationservice.service.MatchRecommendationService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/matchservice")
public class MatchRecommendationController {


    private ResponseEntity responseEntity;
    private MatchRecommendationService matchRecommendationService;

    /**
     *
     * @param matchService
     */
    @Autowired
    public MatchRecommendationController(final MatchRecommendationService matchService) {
        this.matchRecommendationService = matchRecommendationService;
    }

    @Autowired
    private MatchCountRepository matchCountRepository;

    /**
     * API to save match
     * @param match
     * @return
     */
    @PostMapping (path = "/{userId}/match")
    @ExceptionHandler(MatchAlreadyExistsException.class)
    public ResponseEntity<?> saveNewMatch(@RequestBody MatchCount match, @PathVariable("userId") String userId) throws MatchAlreadyExistsException {


        try {
            match.setUserId(userId);
            matchRecommendationService.saveMatch(match);
            responseEntity = new ResponseEntity<MatchCount>(match, HttpStatus.CREATED);
        } catch (MatchAlreadyExistsException e) {
            responseEntity = new ResponseEntity<String>("{\"message\" : \"" + e.getMessage() + "\" }",
                    HttpStatus.CONFLICT);
        }
        return responseEntity;
    }



    /**
     * API to update match using match id.
     * @param id
     * @param match
     * @return
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateMatch(@PathVariable final Integer id, @RequestBody MatchCount match) {

        try {
            matchRecommendationService.updateMatch(match);
            responseEntity = new ResponseEntity<MatchCount>(match, HttpStatus.OK);
        } catch (MatchNotFoundException e) {
            responseEntity = new ResponseEntity<String>("{\"message\" : \"" + e.getMessage() + "\" }",
                    HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    /**
     * API to delete match using id.
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{userId}/{id}")
    public ResponseEntity<?> deleteMatch(@PathVariable final Integer id, @PathVariable("userId") String userId) {

        try {
            matchRecommendationService.deleteMatchById(id);
            responseEntity = new ResponseEntity<String>("Match deleted succesfully", HttpStatus.OK);
        } catch (MatchNotFoundException e) {
            responseEntity = new ResponseEntity<String>("{\"message\" : \"" + e.getMessage() + "\" }",
                    HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * API to get match using id.
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> fetchMatchById(@PathVariable final Integer id) {

        try {
            MatchCount match= matchRecommendationService.getMatchById(id);
            responseEntity = new ResponseEntity<MatchCount>(match, HttpStatus.OK);
        } catch (MatchNotFoundException e) {
            responseEntity = new ResponseEntity<String>("{\"message\" : \"" + e.getMessage() + "\" }",
                    HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * API to get all matches.
     * @return
     */
    @GetMapping
    public ResponseEntity<?> fetchAllMatches() {

        return new ResponseEntity<List<MatchCount>>(matchRecommendationService.getAllMatches(), HttpStatus.OK);

    }

    /**
     *
     * @param request
     * @return
     */
    @GetMapping(path ="/{userId}/matches")
    public ResponseEntity<?> fetchMyMatches(HttpServletRequest request, @PathVariable("userId") String userId) {
//		Claims claims = (Claims) request.getAttribute("claims");
//		String userId = claims.getSubject();
        return new ResponseEntity<List<MatchCount>>(matchRecommendationService.getMyMatches(userId), HttpStatus.OK);

    }


    @GetMapping("/{userId}/matchrecommendations")
    public List<Map<String, Object>> getMatchrecommendations(){

        System.out.println(matchCountRepository.findRecommendations().stream().collect(Collectors.toList()));

        return matchCountRepository.findRecommendations().stream().collect(Collectors.toList());
    }




}

