package com.seehin.www.po;

public class roomPo {

	String hotel=null;
	String id=null;
	String type=null;
	String area=null;
	String bed=null;
	String price=null;
	String reservation="预定";
	
	
	
	public roomPo(String hotel,String id, String type, String area, String bed, String price) {
		super();
		this.hotel=hotel;
		this.id = id;
		this.type = type;
		this.area = area;
		this.bed = bed;
		this.price = price;
	}



	public String getHotel() {
		return hotel;
	}



	public void setHotel(String hotel) {
		this.hotel = hotel;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getArea() {
		return area;
	}



	public void setArea(String area) {
		this.area = area;
	}



	public String getBed() {
		return bed;
	}



	public void setBed(String bed) {
		this.bed = bed;
	}



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}



	public String getReservation() {
		return reservation;
	}



	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	
}
