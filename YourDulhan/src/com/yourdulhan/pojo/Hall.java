package com.yourdulhan.pojo;

public class Hall {
	
	
	private String hallName;
	private String ownerFirstName;
	private String ownerLastName;
	private String mobileNumber;
	private int addressId;
	private Address address;
	public String getHallName() {
		return hallName;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	public String getOwnerFirstName() {
		return ownerFirstName;
	}
	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}
	public String getOwnerLastName() {
		return ownerLastName;
	}
	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Hall [hallName=" + hallName + ", ownerFirstName="
				+ ownerFirstName + ", ownerLastName=" + ownerLastName
				+ ", mobileNumber=" + mobileNumber + ", addressId=" + addressId
				+ ", address=" + address + "]";
	}
	
	
	

}
