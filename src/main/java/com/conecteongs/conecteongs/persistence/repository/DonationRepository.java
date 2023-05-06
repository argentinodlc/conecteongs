package com.conecteongs.conecteongs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conecteongs.conecteongs.persistence.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long>{

}
