package com.conecteongs.conecteongs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conecteongs.conecteongs.persistence.model.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {

}
