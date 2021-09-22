package com.kesti.weatherreport.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@NoArgsConstructor
public class WeatherReport{
 public Coord coord;
 public List<Weather> weather;
 public String base;
 public Main main;
 public int visibility;
 public Wind wind;
 public Clouds clouds;
 public int dt;
 public Sys sys;
 public int timezone;
 public int id;
 public String name;
 public int cod;
}

