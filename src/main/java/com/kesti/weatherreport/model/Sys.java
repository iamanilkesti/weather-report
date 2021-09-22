package com.kesti.weatherreport.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Sys {
	private int type;
	private int id;
	private String country;
	private int sunrise;
	private int sunset;
}
