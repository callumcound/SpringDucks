package com.example.ducks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ducks.entity.Duck;
import com.example.ducks.repo.DuckRepo;

@Service
public class DuckService implements ServiceMethods<Duck>{
	//this doesn't create a new object, but a new variable of the type DuckRepo
	private DuckRepo repo;
	
	//DefaultConstructor
	public DuckService(DuckRepo repo) {
		this.repo = repo;
	}
	//.save saves to the table
	@Override
	public Duck create(Duck duck) {
		return this.repo.save(duck);
	}

	@Override
	public List<Duck> readAll() {
		return this.repo.findAll();
	}

	@Override
	public Duck readById(long id) {
		Optional<Duck> getDuck = this.repo.findById(id);
		if(getDuck.isPresent()) {
			return getDuck.get();
		}
		return null;
	}
	//Updating values updates all fields BUT NOT ID!!! don't set the id.
	@Override
	public Duck update(long id, Duck duck) {
		Optional<Duck> existingDuck = this.repo.findById(id);
		if(existingDuck.isPresent()) {
			Duck exists = existingDuck.get();
			exists.setAge(duck.getAge());
			exists.setGender(duck.getGender());
			exists.setHabitat(duck.getHabitat());
			exists.setName(duck.getName());
			//underneath, the line saves the exists duck object
			return this.repo.saveAndFlush(exists);
			}
		return null;
	}

	@Override
	public boolean delete(long id) {
		this.repo.deleteById(id);
		//below checks that the id doesn't exist anymore, verification step.
		return !this.repo.existsById(id);
	}
	

}
