package com.monivapp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monivapp2.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
	
}