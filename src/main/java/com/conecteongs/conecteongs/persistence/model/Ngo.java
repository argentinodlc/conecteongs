package com.conecteongs.conecteongs.persistence.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_ngo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ngo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private User user;

    @OneToMany(mappedBy = "ngo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Area> areas;

    @OneToMany(mappedBy = "ngo", fetch = FetchType.LAZY)
    private List<Campaign> campaigns;

    @OneToMany(mappedBy = "ngo", fetch = FetchType.LAZY)
    private List<Gallery> galleries;
    
    @OneToMany(mappedBy = "ngo", fetch = FetchType.LAZY)
    private List<Donation> donations;
    
    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

}