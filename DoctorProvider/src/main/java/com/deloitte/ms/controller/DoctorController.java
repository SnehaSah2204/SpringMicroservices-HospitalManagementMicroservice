package com.deloitte.ms.controller;

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

import com.deloitte.ms.entity.Doctors;
import com.deloitte.ms.service.DoctorService;

@RestController
@RequestMapping("doctors")
public class DoctorController {

	@Autowired
	DoctorService service;

	/* map to get all doctors */
	@GetMapping("/getAllDoctors")
	public ResponseEntity<List<Doctors>> getAllDoctors() {
		return new ResponseEntity<List<Doctors>>(service.getAllDoctors(), HttpStatus.OK);
	}

	/* map to insert an doctor */
	@PostMapping("/insertDoctor")
	public ResponseEntity<Doctors> insertDoctorInfo1(@RequestBody Doctors doctor) {
		return new ResponseEntity<Doctors>(service.insertDr(doctor), HttpStatus.OK);
	}
	
	/* map to get doctor by id */
	@GetMapping("/getDoctorById/{drId}")
	public ResponseEntity<Doctors> getDrById(@PathVariable int drId) {
		Doctors doctor = service.getDrById(drId);
		return new ResponseEntity<Doctors>(doctor, HttpStatus.OK);
	}

	/* map to delete a doctors */
	@DeleteMapping("/deleteDoctor/{drId}")
	public ResponseEntity<?> deleteById(@PathVariable int drId) {
		ResponseEntity<?> responseEntity = null;
		if (service.getDrById(drId) == null) {
			responseEntity = new ResponseEntity<String>("No Doctor With that Id", HttpStatus.NOT_FOUND);
		} else {
			service.deleteDr(drId);
			responseEntity = new ResponseEntity<List<Doctors>>(service.getAllDoctors(), HttpStatus.OK);
		}
		return responseEntity;
	}

	
	/* map to update information of one  doctor */
	@PutMapping("/updateDrInfo")
	public ResponseEntity<Doctors> updatedAccounts(@RequestBody Doctors doctor) {
		return new ResponseEntity<Doctors>(service.updateDoctors(doctor), HttpStatus.OK);
	}

}
