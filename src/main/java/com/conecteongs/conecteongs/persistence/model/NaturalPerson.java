package com.conecteongs.conecteongs.persistence.model;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("natural")
public class NaturalPerson extends Donor {

    private String cpf;
	
	private String rg;

	public NaturalPerson(Long id, User user, List<Donation> donations, List<RecurrentDonation> recurrentDonations, String cpf, String rg) {
		super(id, user, donations, recurrentDonations);
		this.cpf = cpf;
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

}
