package com.paypal.bfs.test.bookingserv.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.paypal.bfs.test.bookingserv.model.Address;


@Entity
@Table(name= "bookings")
public class Bookings{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
    private Integer bookingId;
    
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String checkinDatetime;
    private String checkoutDatetime;
    private Double totalprice;
    private Double deposit;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
   // @OneToOne(fetch = FetchType.LAZY, optional = false)
   // @JoinColumn(name = "address_id", nullable = false)
    private Address address;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getCheckinDatetime() {
		return checkinDatetime;
	}
	public void setCheckinDatetime(String checkinDatetime) {
		this.checkinDatetime = checkinDatetime;
	}
	public String getCheckoutDatetime() {
		return checkoutDatetime;
	}
	public void setCheckoutDatetime(String checkoutDatetime) {
		this.checkoutDatetime = checkoutDatetime;
	}
	public Double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}
	public Double getDeposit() {
		return deposit;
	}
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

    

	
}
