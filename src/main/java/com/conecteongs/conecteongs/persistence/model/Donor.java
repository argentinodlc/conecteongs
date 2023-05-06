package com.conecteongs.conecteongs.persistence.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_donor")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="person_type", discriminatorType = DiscriminatorType.STRING)
public class Donor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private User user;
	
    @OneToMany(mappedBy = "donor", fetch = FetchType.LAZY)
    private List<Donation> donations;
    
    @OneToMany(mappedBy = "donor", fetch = FetchType.LAZY)
    private List<RecurrentDonation> recurrentDonations;

	public Donor(Long id, User user, List<Donation> donations, List<RecurrentDonation> recurrentDonations) {
		this.id = id;
		this.user = user;
		this.donations = donations;
		this.recurrentDonations = recurrentDonations;
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

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	public List<RecurrentDonation> getRecurrentDonations() {
		return recurrentDonations;
	}

	public void setRecurrentDonations(List<RecurrentDonation> recurrentDonations) {
		this.recurrentDonations = recurrentDonations;
	}
}
