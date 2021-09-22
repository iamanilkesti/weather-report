package com.kesti.weatherreport.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kesti.weatherreport.model.WeatherReport;
import com.kesti.weatherreport.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherReportController {
	private static final Logger logger = LoggerFactory.getLogger(WeatherReportController.class);
	
	@Autowired
	private WeatherService weatherService;
	
	/**
	 * @param city
	 * @return wsseatherReport
	 */
	@GetMapping("/now/{city}")
	public WeatherReport getWeatherByCity(@PathVariable String city) {
		logger.info("reached controller!!!");
		return weatherService.getWeatherByCity(city);
	}
	
	/**
	 * @param lat
	 * @param lon
	 * @return weatherReport
	 */
	@GetMapping("/now/{lat}/{lon}")
	public WeatherReport getWeatherByLatLon(@PathVariable String lat, @PathVariable String lon) {
		logger.info("reached controller!!!");
		return weatherService.getWeatherByLocation(lat, lon);
	}
}
