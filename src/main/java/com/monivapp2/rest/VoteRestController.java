	package com.monivapp2.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.monivapp2.entity.Vote;
import com.monivapp2.service.VoteService;

@RestController
public class VoteRestController {
	
	@Autowired
	public VoteService voteService;
	
	@GetMapping(
			value = "/rest/votes",
			produces = "application/json")
	public List<Vote> restVotesGet() {
		
		return voteService.getAllVotes();
	}
	
	@GetMapping(
			value = "/rest/limits",
			produces = "application/json")
	public int restLimitsGet() {
		
		return voteService.countVotes();
	}
	
	@PostMapping(
			value = "/rest/movies/action/vote",
			consumes = "application/json",
			produces = "application/json")
	public Vote restMoviesActionVotePost(@RequestBody Vote theVote) {
		
		return voteService.addVote(theVote);
	}
	
	@GetMapping(
			value = "/rest/movies/action/vote/{movieId}",
			produces = "application/json")
	public Vote restMoviesVoteGetMovieId(@PathVariable int movieId) {

		return voteService.addVoteByMovieId(movieId);
	}
}