package com.example.ducks.service;

import java.util.List;

public interface ServiceMethods<T> {
	
	//Create
	T create(T duck);
	
	//ReadAll
	List<T> readAll();
	
	//ReadById
	T readById(long id);
	
	//Update
	T update(long id, T duck);
	
	//Delete
	boolean delete(long id);
}
