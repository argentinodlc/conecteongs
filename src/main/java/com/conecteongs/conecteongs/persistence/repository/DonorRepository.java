package com.conecteongs.conecteongs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conecteongs.conecteongs.persistence.model.Donor;

public interface DonorRepository extends JpaRepository<Donor, Long>{

}
