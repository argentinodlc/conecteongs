package com.conecteongs.conecteongs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conecteongs.conecteongs.persistence.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
