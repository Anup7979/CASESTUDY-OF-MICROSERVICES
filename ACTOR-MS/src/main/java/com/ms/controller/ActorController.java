package com.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ms.entity.Actor;
import com.ms.service.ActorService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/actor")
public class ActorController {
	@Autowired
	ActorService service;

	@PostMapping("/add")
	public ResponseEntity<Actor> addActor(@RequestBody Actor actor) {
		return new ResponseEntity<Actor>(service.addActor(actor), HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Actor>> getActors() {
		return new ResponseEntity<List<Actor>>(service.getActor(), HttpStatus.OK);
	}

	@GetMapping("/get/{actor_id}")
	public ResponseEntity<?> getSingleActorById(@PathVariable int actor_id) {
		Actor actor = service.getSingleActor(actor_id);
		ResponseEntity<?> responseEntity = null;
		if (actor == null) {
			responseEntity = new ResponseEntity<String>("No Actor found with the given id " + actor_id,
					HttpStatus.SERVICE_UNAVAILABLE);
		} else {
			responseEntity = new ResponseEntity<Actor>(service.getSingleActor(actor_id), HttpStatus.OK);
		}
		return responseEntity;
	}

	@PutMapping("/updateActor")
	public ResponseEntity<Actor> updatedActor(@RequestBody Actor actor) {
		Actor actorList = service.getUpdatedActor(actor);
		return new ResponseEntity<Actor>(actorList, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{actor_id}")
	public ResponseEntity<String> deleteActor(@PathVariable int actor_id) {
		service.deleteActor(actor_id);
		return new ResponseEntity<String>("Deleted Succesfully", HttpStatus.OK);

	}

}
