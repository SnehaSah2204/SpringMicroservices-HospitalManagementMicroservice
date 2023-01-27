package com.deloitte.ms.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FailiureResponse {
	private List<Patient> patientList;
	private String message;
}
