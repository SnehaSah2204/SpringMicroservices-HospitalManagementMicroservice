package com.deloitte.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.ms.entity.Doctors;

@Repository
public interface DoctorRepository extends JpaRepository<Doctors, Integer> {

}
