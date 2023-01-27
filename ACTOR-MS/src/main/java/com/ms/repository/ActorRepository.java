package com.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.entity.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer>{

}
