package com.deloitte.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
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
import com.deloitte.ms.model.FailiureResponse;
import com.deloitte.ms.model.Patient;
import com.deloitte.ms.model.PatientAndDoctorResponse;
import com.deloitte.ms.service.PatientFeignService;
import com.deloitte.ms.service.PatientService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/patients")
@RibbonClient(name = "DOCTOR-MS")
public class PatientController {

	@Autowired
	PatientService service;

	@Autowired
	PatientFeignService feignService;

	
	@GetMapping("/getAllPatients")
	public ResponseEntity<List<Patient>> getAllPatients() {
		return new ResponseEntity<List<Patient>>(service.getAllPatients(), HttpStatus.OK);
	}

	
	@PostMapping("/insertPatient")
	public ResponseEntity<Patient> insertPatientInfo(@RequestBody Patient patient) {
		return new ResponseEntity<Patient>(service.insertPatientInfo(patient), HttpStatus.OK);
	}
	
	

	@GetMapping("getPatientById/{pId}")
	public ResponseEntity<Patient> getPatientById(@PathVariable int pId) {
		Patient patient = service.getPatientById(pId);
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}
	
	

	@DeleteMapping("/deletePatient/{pId}")
	public ResponseEntity<?> deleteById(@PathVariable int pId) {
		ResponseEntity<?> responseEntity = null;
		if (service.getPatientById(pId) == null) {
			responseEntity = new ResponseEntity<String>("No Patient With that Id", HttpStatus.NOT_FOUND);
		} else {
			service.deletePatient(pId);
			responseEntity = new ResponseEntity<List<Patient>>(service.getAllPatients(), HttpStatus.OK);
		}
		return responseEntity;
	}

	
	@PutMapping("/updatePatientInfo")
	public ResponseEntity<Patient> updatedPatients(@RequestBody Patient patient) {
		return new ResponseEntity<Patient>(service.updatePatients(patient), HttpStatus.OK);
	}

	
	
	@GetMapping("/getPatientsByDrId/{drId}")
	@CircuitBreaker(name = "PATIENT-MS", fallbackMethod = "callFallBack")
	public ResponseEntity<?> getAllPatientByDoctorId(@PathVariable int drId) {
		ResponseEntity<?> responseEntity = null;
		Doctors doctor = feignService.getDrById(drId);
		if (doctor == null) {
			responseEntity = new ResponseEntity<String>("No doctor found with this id" + drId, HttpStatus.NOT_FOUND);
		} else {
			List<Patient> patientlist = service.getAllPatientByDrId(drId);
			PatientAndDoctorResponse response = new PatientAndDoctorResponse();
			response.setDoctors(doctor);
			response.setPatients(patientlist);
			responseEntity = new ResponseEntity<PatientAndDoctorResponse>(response, HttpStatus.OK);
		}
		return responseEntity;
	}

	
	
	/* fallback method definition */
	public ResponseEntity<?> callFallBack(Exception exception) {
		FailiureResponse failiure = new FailiureResponse();
		failiure.setPatientList(service.getAllPatients());
		failiure.setMessage(
				"Oops! The Doctor server is in maintenance. Please try after some time :) Here is also a list of all the current patients ");
		return new ResponseEntity<FailiureResponse>(failiure, HttpStatus.SERVICE_UNAVAILABLE);
	}

}
