package com.deloitte.ms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deloitte.ms.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	@Query(name = "FindDr", value = "Select a from Patient a where a.drId =:drId")
	public List<Patient> findDoctorByID(int drId);
}
