package com.conecteongs.conecteongs.persistence.model;

import java.util.Date;
import java.util.List;

import com.conecteongs.conecteongs.utils.enums.Recurrence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_recurrent_donation")
public class RecurrentDonation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToMany(mappedBy = "recurrentDonation", fetch = FetchType.LAZY)
	private List<Donation> donations;
	
	@ManyToOne
	@JoinColumn(name = "donor_id")
	private Donor donor;

	@Column
	private Recurrence recurrence;
	
	@Column(name = "start_date", nullable = false)
    private Date startDate;
    
    @Column(name = "end_date", nullable = true)
    private Date endDate;

	public RecurrentDonation(List<Donation> donations, Recurrence recurrence, Date startDate, Date endDate) {
		this.donations = donations;
		this.recurrence = recurrence;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	public Recurrence getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(Recurrence recurrence) {
		this.recurrence = recurrence;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
