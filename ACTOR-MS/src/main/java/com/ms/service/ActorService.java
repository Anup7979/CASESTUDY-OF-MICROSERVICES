package com.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.entity.Actor;
import com.ms.repository.ActorRepository;

@Service
public class ActorService {
	@Autowired
	ActorRepository repository;

	public Actor addActor(Actor actor) {
		return repository.save(actor);
	}
	
	public List<Actor> getActor(){
		return repository.findAll();
	}
	
	public Actor getSingleActor(int actor_id) {
		return repository.findById(actor_id).orElse(null);
		
	}
	public Actor getUpdatedActor(Actor actor) {
		Actor actorData=repository.findById(actor.getActor_id()).get();
		actorData.setActorName(actor.getActorName());
		
		return repository.save(actorData);
		
	}
	
	public void deleteActor(int actor_id) {
	 repository.deleteById(actor_id);
	
		
		
		
		
	}

}
