package com.ms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ACTOR")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor {

	@Id
	@Column(name = "actorId")
	private int actor_id;
	@Column(name = "actor_name")
	private String actorName;

}
