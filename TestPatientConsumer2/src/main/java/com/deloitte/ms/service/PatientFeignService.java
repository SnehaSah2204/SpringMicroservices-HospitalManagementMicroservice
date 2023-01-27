package com.deloitte.ms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.deloitte.ms.entity.Doctors;

@FeignClient(value = "DOCTOR-MS")
public interface PatientFeignService {

	@GetMapping("/doctors/getDoctorById/{drId}")
	public Doctors getDrById(@PathVariable int drId);
}
