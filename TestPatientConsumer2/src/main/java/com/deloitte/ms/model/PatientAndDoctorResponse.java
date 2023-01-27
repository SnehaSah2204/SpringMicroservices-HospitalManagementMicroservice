package com.deloitte.ms.model;

import java.util.List;
import com.deloitte.ms.entity.Doctors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientAndDoctorResponse {

	private List<Patient> patients;
	private Doctors doctors;

}
