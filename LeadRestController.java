package com.marketingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketingapp.entities.Lead;
import com.marketingapp.repository.LeadRepository;

@RestController
@RequestMapping("/api/leads") //whenever we write this annotation we get this url we can call the getmapping method
public class LeadRestController {
//In this class we will develop APIs
	
	@Autowired
	private LeadRepository leadRepo;
	
	@GetMapping                            //getmapping will automatically convert java object to json object
	public List<Lead> getAllLeads(){
		List<Lead> leads = leadRepo.findAll();//it will return java object.
		return leads;
		//GetMapping will get all the record from database as a java object.
	}

@PostMapping
public void saveLead(@RequestBody Lead lead){
	leadRepo.save(lead);
	}
	
@PutMapping
public void updateLead(@RequestBody Lead lead){
	leadRepo.save(lead);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteLead(@PathVariable("id") long id) {
		leadRepo.deleteById(id);
	}

}
