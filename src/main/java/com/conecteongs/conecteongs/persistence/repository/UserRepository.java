package com.conecteongs.conecteongs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conecteongs.conecteongs.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
