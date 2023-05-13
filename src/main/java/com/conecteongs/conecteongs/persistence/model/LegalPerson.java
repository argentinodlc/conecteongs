package com.conecteongs.conecteongs.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("legal")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class LegalPerson extends Donor{
	
	@Column
	private String cnpj;
	
	@Column
	private String commercialName;

	public LegalPerson(DonorBuilder<?, ?> b, String cnpj, String commercialName) {
		super(b);
		this.cnpj = cnpj;
		this.commercialName = commercialName;
	}

	
	
}
