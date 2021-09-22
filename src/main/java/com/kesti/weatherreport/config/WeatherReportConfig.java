package com.kesti.weatherreport.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherReportConfig {
	
	@Value("${weatherreport.config.fetchWeatherInfoByCityNameURL}")
	private String fetchWeatherInfoByCityNameURL;
	
	@Value("${weatherreport.config.fetchWeatherInfoByLatLonURL}")
	private String fetchWeatherInfoByLatLonURL;
	
	@Value("${weatherreport.config.appId}")
	private String appId;

	public String getFetchWeatherInfoByCityNameURL() {
		return fetchWeatherInfoByCityNameURL;
	}

	public void setFetchWeatherInfoByCityNameURL(String fetchWeatherInfoByCityNameURL) {
		this.fetchWeatherInfoByCityNameURL = fetchWeatherInfoByCityNameURL;
	}

	public String getFetchWeatherInfoByLatLonURL() {
		return fetchWeatherInfoByLatLonURL;
	}

	public void setFetchWeatherInfoByLatLonURL(String fetchWeatherInfoByLatLonURL) {
		this.fetchWeatherInfoByLatLonURL = fetchWeatherInfoByLatLonURL;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	

}
