package com.deloitte.ms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.deloitte.ms.entity.Doctors;
import com.deloitte.ms.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository repository;

	/* method to get all Doctors */

	public List<Doctors> getAllDoctors() {
		return repository.findAll();
	}

	/* method to insert one Doctor */
	public Doctors insertDr(Doctors doctor) {
		return repository.save(doctor);
	}

	/* method to get one Doctor by id */
	public Doctors getDrById(int drId) {
		return repository.findById(drId).get();
	}

	/* method to delete one Doctor by id */
	public void deleteDr(int drId) {
		repository.deleteById(drId);
	}

	/* method to update Doctor by passing info */
	public Doctors updateDoctors(Doctors doctor) {
		Doctors newInfoDoctor = repository.findById(doctor.getDrId()).get();
		newInfoDoctor.setDrName(doctor.getDrName());
		newInfoDoctor.setSpeciality(doctor.getSpeciality());
		return repository.save(newInfoDoctor);
	}
}
