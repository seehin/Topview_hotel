package com.seehin.www.po;

public class newHotelPo {

	String name=null;
	String evaluate=null;
	int like=0;
	int unlike=0;
	
	
	public newHotelPo(String name, String evaluate, int like, int unlike) {
		super();
		this.name = name;
		this.evaluate = evaluate;
		this.like = like;
		this.unlike = unlike;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getUnlike() {
		return unlike;
	}
	public void setUnlike(int unlike) {
		this.unlike = unlike;
	}
	
	
}
