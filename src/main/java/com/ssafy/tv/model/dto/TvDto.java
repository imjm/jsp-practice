package com.ssafy.tv.model.dto;

public class TvDto {

	private int pn;
	private String name;
	private String brand;
	private int price;
	
	public TvDto() {
		super();
	}

	public TvDto(int pn, String name, String brand, int price) {
		super();
		this.pn = pn;
		this.name = name;
		this.brand = brand;
		this.price = price;
	}

	public int getPn() {
		return pn;
	}

	public void setPn(int pn) {
		this.pn = pn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
}
