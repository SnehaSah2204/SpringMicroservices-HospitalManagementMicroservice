package com.deloitte.ms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctors {

	private int drId;
	private String drName;
	private String speciality;
}
