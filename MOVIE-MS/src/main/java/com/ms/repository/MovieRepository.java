package com.ms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ms.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	@Query(name = "findActorQuery",value = "select a from Movie a where a.actor_id=:actor_id")
	public List<Movie> findAllMovieByActorId(int actor_id);
	

}
