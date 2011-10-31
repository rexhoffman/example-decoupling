package com.ehoffman.example.implementation;

import java.math.BigDecimal;

import com.ehoffman.example.ExampleDTO;

public class ExampleDTOImpl implements ExampleDTO {
    private Integer id;
    private String name;
    private BigDecimal volume;
    private BigDecimal mass;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "EaxmpleDTOImpl [id=" + id + ", mass=" + mass + ", name=" + name
				+ ", volume=" + volume + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public BigDecimal getMass() {
		return mass;
	}
	public void setMass(BigDecimal mass) {
		this.mass = mass;
	}
}
