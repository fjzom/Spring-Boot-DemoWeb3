package com.example.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "customers")
@Document
public class Customer {
	@Id
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String customerId;
	@Size(min = 3, max = 8)
	private String name;
	@Column(name = "customer_at")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date customerAt;
	private String foto;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
	private Photo photo;
	@OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Bill> bills;
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date createAt;
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
 
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCustomerAt() {
		return customerAt;
	}
	public void setCustomerAt(Date customerAt) {
		this.customerAt = customerAt;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

	public void addBill(Bill bill) {
		this.bills.add(bill);
	}
	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	
	
}
