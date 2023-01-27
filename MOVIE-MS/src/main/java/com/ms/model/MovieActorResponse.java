package com.ms.model;

import java.util.List;

import com.ms.entity.Movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieActorResponse {
	
	public List<Movie> movies;
	public Actor actor;

}
