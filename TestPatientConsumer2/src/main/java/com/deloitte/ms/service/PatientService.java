package com.deloitte.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import com.deloitte.ms.model.Patient;
import com.deloitte.ms.repository.PatientRepository;

@Service

public class PatientService {

	@Autowired
	PatientRepository repository;

	/* method to get all Patients */

	public List<Patient> getAllPatients() {
		return repository.findAll();
	}

	/* method to insert one Patient */
	public Patient insertPatientInfo(Patient patient) {
		return repository.save(patient);
	}

	/* method to get one Patient by id */
	public Patient getPatientById(int pId) {
		return repository.findById(pId).get();
	}

	/* method to delete one Patients by id */
	public void deletePatient(int pId) {
		repository.deleteById(pId);
	}

	/* method to update Patients by passing info */
	public Patient updatePatients(Patient patient) {
		Patient newInfoPatient = repository.findById(patient.getPId()).get();
		newInfoPatient.setPAge(patient.getPAge());
		newInfoPatient.setPCity(patient.getPCity());
		return repository.save(newInfoPatient);
	}

	/* method to get all patients along with their respective doctor information */
	public List<Patient> getAllPatientByDrId(int drId) {
		return repository.findDoctorByID(drId);
	}
}
