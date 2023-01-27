package com.ms.service;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ms.model.Actor;

@FeignClient(value = "ACTOR-MS")
public interface FeignService {
	@GetMapping("/actor/get/{actor_id}")
	public Actor getActorById(@PathVariable int actor_id);

}
