package com.example.ducks.controller;

import java.util.List;

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

import com.example.ducks.entity.Duck;
import com.example.ducks.service.DuckService;

@RestController
@RequestMapping("/duck")
public class DuckController {
	
	private DuckService service;
	
	private DuckController(DuckService service) {
		this.service = service;
	}

	//create
	@PostMapping("/create")
	public ResponseEntity<Duck> createDuck(@RequestBody Duck duck){
		return new ResponseEntity<Duck>(this.service.create(duck), HttpStatus.CREATED);
	}
	
	//Read All
	@GetMapping("/readAll")
	//The initial mistake I made was returning a single Duck object rather than the list,which is why we need list<>
	public ResponseEntity<List<Duck>> readAllDucks(){
		return new ResponseEntity<List<Duck>>(this.service.readAll(), HttpStatus.OK);
	}
	//ReadById
	@GetMapping("/readById/{id}")
	public ResponseEntity<Duck> readById(@PathVariable long id){
		return new ResponseEntity<Duck>(this.service.readById(id), HttpStatus.OK); 
	}
	
	//Update
	@PutMapping("/update/{id}")
	public ResponseEntity<Duck> updateDuck(@PathVariable long id, @RequestBody Duck duck){
		return  new ResponseEntity<Duck>(this.service.update(id, duck), HttpStatus.ACCEPTED);
	}
	
	//Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteDuck(@PathVariable long id){
		return (this.service.delete(id)) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT) :
			new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
	}
}
