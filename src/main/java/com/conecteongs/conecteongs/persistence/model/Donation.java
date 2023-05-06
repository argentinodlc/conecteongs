package com.conecteongs.conecteongs.persistence.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_donation")
public class Donation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ngo_id") 
    private Ngo ngo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id")
    private Donor donor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goal_id")
	private Goal goal;
    
    @ManyToOne(fetch = FetchType.LAZY)
   	@JoinColumn(name = "recurrent_donation_id")
   	private RecurrentDonation recurrentDonation;
    
    @Column(nullable = false)
    private BigDecimal value;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date donationDate;

	public Donation(Long id, Ngo ngo, Donor donor, PaymentType paymentType, Goal goal,
			RecurrentDonation recurrentDonation, BigDecimal value, Date donationDate) {
		this.id = id;
		this.ngo = ngo;
		this.donor = donor;
		this.paymentType = paymentType;
		this.goal = goal;
		this.recurrentDonation = recurrentDonation;
		this.value = value;
		this.donationDate = donationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ngo getNgo() {
		return ngo;
	}

	public void setNgo(Ngo ngo) {
		this.ngo = ngo;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public RecurrentDonation getRecurrentDonation() {
		return recurrentDonation;
	}

	public void setRecurrentDonation(RecurrentDonation recurrentDonation) {
		this.recurrentDonation = recurrentDonation;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Date getDonationDate() {
		return donationDate;
	}

	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}

}
