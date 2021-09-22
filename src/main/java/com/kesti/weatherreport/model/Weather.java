package com.kesti.weatherreport.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Weather {
	private int id;
	private String main;
	private String description;
	private String icon;

}
