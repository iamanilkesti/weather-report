package com.kesti.weatherreport.service;

import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.kesti.weatherreport.config.WeatherReportConfig;
import com.kesti.weatherreport.model.WeatherReport;

@Service
public class WeatherService {

	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
	
	private static final int TWO_HRS = 120 * 60 * 1000;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WeatherReportConfig config;

	private ConcurrentHashMap<String, WeatherReport> dataCache = new ConcurrentHashMap<>();
	

	public WeatherReport getWeatherByCity(String city) {
		WeatherReport cachedData = dataCache.get(city);
		try {
			if (null == cachedData || isDataExprired(cachedData.getDt())) {
				logger.info("Fetching latest weather for  city - {}", city);
				WeatherReport latestWeatherData = getWeather(city);
				dataCache.put(city, latestWeatherData);
				return latestWeatherData;
			}
			logger.info("Returning cached weather data for city {} ", city);
		} catch (Exception e) {
			logger.error("Error in fetching weather by City!");
		}
		return cachedData;
	}

	private boolean isDataExprired(int weatherTime) {
		long twoHrsAgo = System.currentTimeMillis() - TWO_HRS;
		if (weatherTime > twoHrsAgo) {
		    logger.info("Last weather cached is expired, updated new one!");
		    return true;
		}else {
			logger.info("Last weather cached is not expired! ");
		}
		return false;
	}

	public WeatherReport getWeather(String city) {
		try {
			logger.info("Requesting current weather for {}", city);
			String url = config.getFetchWeatherInfoByCityNameURL().replace("{city}", city);
			logger.info("Requesting city weather URL {}", url);
			ResponseEntity<WeatherReport>  result= restTemplate.getForEntity(url, WeatherReport.class);
			logger.info("WEATHER RESULT: {}", result);
			return result.getBody();
		} catch (Exception e) {
			logger.error("Exception in getting weather for city - {}", city);
		}
		return  null;
		
	}

	public WeatherReport getWeatherByLocation(String lat, String lon) {
		String latlon = lat+","+lon;
		WeatherReport cachedData = dataCache.get(latlon);
		try {
			if (null == cachedData || isDataExprired(cachedData.getDt())) {
				logger.info("Fetching latest weather for  lat,lon - {}", latlon);
				WeatherReport latestWeatherData = getWeatherByCoordinates(lat, lon);
				dataCache.put(latlon, latestWeatherData);
				return latestWeatherData;
			}
			logger.info("Returning cached weather data for coordinates lat,lon {} ", latlon);
			return cachedData;
			
		} catch (Exception e) {
			logger.error("Error in fetching weather by lat lon coordinates!");
		}
		return null;
	}

	private WeatherReport getWeatherByCoordinates(String lat, String lon) {
		try {
			logger.info("Requesting current weather for lat,lon {},{}", lat,lon);
			String url = config.getFetchWeatherInfoByLatLonURL().replace("{lat}", lat).replace("{lon}", lon);
			logger.info("Requesting city weather URL {}", url);
			ResponseEntity<WeatherReport>  result= restTemplate.getForEntity(url, WeatherReport.class);
			logger.info("WEATHER RESULT: {}", result);
			return result.getBody();
		} catch (Exception e) {
			logger.error("Exception in getting weather for coordinates lat, lon  - {},{}", lat, lon);
		}
		return  null;
	}

}
