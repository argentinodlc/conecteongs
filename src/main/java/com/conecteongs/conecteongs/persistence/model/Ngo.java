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

@Entity
@Table(name = "tb_ngo")
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

	public Ngo(Long id, User user, @NotBlank String description, List<Area> areas, List<Campaign> campaigns,
			List<Gallery> galleries, List<Donation> donations) {
		this.id = id;
		this.user = user;
		this.description = description;
		this.areas = areas;
		this.campaigns = campaigns;
		this.galleries = galleries;
		this.donations = donations;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public List<Campaign> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public List<Gallery> getGalleries() {
		return galleries;
	}

	public void setGalleries(List<Gallery> galleries) {
		this.galleries = galleries;
	}

}
