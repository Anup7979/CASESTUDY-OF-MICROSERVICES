package com.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.entity.Movie;
import com.ms.model.Actor;
import com.ms.model.MovieActorResponse;
import com.ms.service.FeignService;
import com.ms.service.MovieService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/movie")
@RibbonClient(name = "ACTOR-MS")
public class MovieController {
	@Autowired
	MovieService service;
	@Autowired
	FeignService feignService;

	@PostMapping("/add")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
		return new ResponseEntity<Movie>(service.addMovie(movie), HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Movie>> getMovie() {
		return new ResponseEntity<List<Movie>>(service.getMovie(), HttpStatus.OK);
	}

	@GetMapping("/get/{movie_id}")
	public ResponseEntity<?> getSingleMovieById(@PathVariable int movie_id) {
		Movie movies = service.getSingleMovie(movie_id);
		ResponseEntity<?> responseEntity = null;

		if (movies == null) {
			responseEntity = new ResponseEntity<String>("No Movie found with the given id", HttpStatus.NOT_FOUND);
		} else {
			responseEntity = new ResponseEntity<Movie>(service.getSingleMovie(movie_id), HttpStatus.OK);
		}
		return responseEntity;
	}

	@PutMapping("/updateMovie")
	public ResponseEntity<Movie> updatedMovie(@RequestBody Movie movie) {
		Movie movieList = service.getUpdatedMovie(movie);
		return new ResponseEntity<Movie>(movieList, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{movie_id}")
	public ResponseEntity<String> deleteMovie(@PathVariable int movie_id) {
		service.deleteMovie(movie_id);
		return new ResponseEntity<String>("Deleted Succesfully", HttpStatus.OK);

	}

	@GetMapping("/getmovieandactor/{actor_id}")
	@CircuitBreaker(name = "MOVIE-MS", fallbackMethod = "callFallBack")
	public ResponseEntity<?> getAllMovieByActorId(@PathVariable int actor_id) {
		ResponseEntity<?> responseEntity = null;
		Actor actor = feignService.getActorById(actor_id);
		if (actor == null) {
			responseEntity = new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
		} else {
			List<Movie> movielist = service.getAllMovieByActor(actor_id);

			MovieActorResponse response = new MovieActorResponse();
			response.setActor(actor);
			response.setMovies(movielist);

			responseEntity = new ResponseEntity<MovieActorResponse>(response, HttpStatus.OK);

		}
		return responseEntity;

	}

	public ResponseEntity<?> callFallBack(Exception exception) {
		return new ResponseEntity<String>("Service Is not available", HttpStatus.SERVICE_UNAVAILABLE);

	}

}
