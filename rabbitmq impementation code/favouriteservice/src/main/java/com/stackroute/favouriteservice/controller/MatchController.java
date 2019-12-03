package com.stackroute.favouriteservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.service.MatchService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/matchservice")
public class MatchController {


	private ResponseEntity responseEntity;
	private MatchService matchService;

	/**
	 * 
	 * @param matchService
	 */
	@Autowired
	public MatchController(final MatchService matchService) {
		this.matchService = matchService;
	}

	/**
	 * API to save match
	 * @param match
	 * @return
	 */
	@PostMapping (path = "/{userId}/match")
	@ExceptionHandler(MatchAlreadyExistsException.class)
	public ResponseEntity<?> saveNewMatch(@RequestBody Match match, @PathVariable("userId") String userId) throws MatchAlreadyExistsException {


		try {
			match.setUserId(userId);
			matchService.saveMatch(match);
			responseEntity = new ResponseEntity<Match>(match, HttpStatus.CREATED);
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
	public ResponseEntity<?> updateMatch(@PathVariable final Integer id, @RequestBody Match match) {

		try {
			matchService.updateMatch(match);
			responseEntity = new ResponseEntity<Match>(match, HttpStatus.OK);
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
			matchService.deleteMatchById(id);
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
			Match match= matchService.getMatchById(id);
			responseEntity = new ResponseEntity<Match>(match, HttpStatus.OK);
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

		return new ResponseEntity<List<Match>>(matchService.getAllMatches(), HttpStatus.OK);

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
		return new ResponseEntity<List<Match>>(matchService.getMyMatches(userId), HttpStatus.OK);

	}




}
