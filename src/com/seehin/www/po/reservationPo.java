package com.seehin.www.po;

public class reservationPo {

	String hotel=null;
	String cstm=null;
	String room=null;
	String pay=null;
	String time=null;
	
	
	public reservationPo(String hotel, String cstm, String room, String pay, String time) {
		super();
		this.hotel = hotel;
		this.cstm = cstm;
		this.room = room;
		this.pay = pay;
		this.time = time;
	}
	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	public String getCstm() {
		return cstm;
	}
	public void setCstm(String cstm) {
		this.cstm = cstm;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
	