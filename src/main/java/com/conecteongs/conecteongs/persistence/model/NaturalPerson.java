package com.conecteongs.conecteongs.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("natural")
@Getter
@Setter
@SuperBuilder
public class NaturalPerson extends Donor {

	@Column
    private String cpf;
	
	@Column
	private String rg;

	public NaturalPerson(DonorBuilder<?, ?> b, String cpf, String rg) {
		super(b);
		this.cpf = cpf;
		this.rg = rg;
	}
	
}