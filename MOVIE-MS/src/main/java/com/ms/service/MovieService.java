package com.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.entity.Movie;
import com.ms.repository.MovieRepository;

@Service
public class MovieService {
	@Autowired
	MovieRepository repository;

	public Movie addMovie(Movie movie) {
		return repository.save(movie);
	}

	public List<Movie> getMovie() {
		return repository.findAll();
	}

	public Movie getSingleMovie(int movie_id) {
		return repository.findById(movie_id).get();

	}

	public Movie getUpdatedMovie(Movie movie) {
		Movie movieData = repository.findById(movie.getMovie_id()).get();
		movieData.setMovie_name(movie.getMovie_name());
		movieData.setMovie_type(movie.getMovie_type());

		return repository.save(movieData);

	}

	public void deleteMovie(int movie_id) {
		repository.deleteById(movie_id);

	}

	public List<Movie> getAllMovieByActor(int actor_id) {
		return repository.findAllMovieByActorId(actor_id);
	}

}
