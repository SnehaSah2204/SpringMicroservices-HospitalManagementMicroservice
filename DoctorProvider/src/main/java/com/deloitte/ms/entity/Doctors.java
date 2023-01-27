package com.deloitte.ms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctors {

	@Id
	@Column(name = "dr_id")
	private int drId;
	@Column(name = "dr_name")
	private String drName;
	@Column(name = "speciality")
	private String speciality;

}
