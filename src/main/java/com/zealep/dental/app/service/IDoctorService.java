package com.zealep.dental.app.service;

import java.util.List;

import com.zealep.dental.app.model.entities.Doctor;

public interface IDoctorService {
	
	Doctor findById(Long id);

	List<Doctor> findAll();

	List<Doctor> findAllActives();

	Doctor save(Doctor d);

	Doctor update(Doctor d);

	void deleteById(Long id);

	boolean isExist(Doctor d);

}
