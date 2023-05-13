package com.conecteongs.conecteongs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conecteongs.conecteongs.persistence.model.Donor;
import com.conecteongs.conecteongs.persistence.repository.DonorRepository;

@Service
public class DonorService {

	@Autowired
	DonorRepository donorRepository;
	
	public void save(Donor donor) {
		donorRepository.save(donor);
	}
	
}
