package com.conecteongs.conecteongs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conecteongs.conecteongs.persistence.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long>{

}
