package com.monivapp2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monivapp2.entity.Vote;
import com.monivapp2.repository.VoteRepository;

@Component
public class VoteService {

	@Autowired
	private VoteRepository voteRepository;
	
	public List<Vote> getAllVotes() {

		return voteRepository.findAll();
	}
	
	public Vote addVote(Vote theVote) {
		
		return voteRepository.save(theVote);
	}

	public Vote addVoteByMovieId(int movieId) {
		
		Vote theVote = new Vote(movieId);
		return addVote(theVote);
	}
	
	public int countVotes() {
		
		return getAllVotes().size();
	}
}