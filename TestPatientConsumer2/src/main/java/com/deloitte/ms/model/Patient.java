package com.deloitte.ms.model;

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
@Table(name = "patients")
public class Patient {

	@Id
	@Column(name = "p_id")
	private int pId;
	@Column(name = "p_name")
	private String pName;
	@Column(name = "p_age")
	private int pAge;
	@Column(name = "p_city")
	private String pCity;
	@Column(name = "dr_id")
	private int drId;
}
