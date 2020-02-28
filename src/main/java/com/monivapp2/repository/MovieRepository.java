package com.monivapp2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monivapp2.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	List<Movie> findAllByOrderByVotesDesc();
}