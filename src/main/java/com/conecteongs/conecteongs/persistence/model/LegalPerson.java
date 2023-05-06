package com.conecteongs.conecteongs.persistence.model;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("legal")
public class LegalPerson extends Donor{
	
	private String cnpj;
	
	private String commercialName;

	public LegalPerson(Long id, User user, List<Donation> donations, List<RecurrentDonation> recurrentDonations, String cnpj, String commercialName) {
		super(id, user, donations, recurrentDonations);
		this.cnpj = cnpj;
		this.commercialName = commercialName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCommercialName() {
		return commercialName;
	}

	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}
}
