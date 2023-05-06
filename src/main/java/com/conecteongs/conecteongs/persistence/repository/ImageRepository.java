package com.conecteongs.conecteongs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conecteongs.conecteongs.persistence.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

}
