package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetDataFromWeather {
    String city = "Dallas";
    String APIKey = ""; //old API Key Went Here to Make App work
    int days = 1;
    boolean airQuality = false;
    boolean alerts = false;

    public GetDataFromWeather() {}
    public GetDataFromWeather(String ncity){
        city = ncity;
    }
    public GetDataFromWeather(String ncity, String nAPIKey, int ndays, boolean nairQuality
        , boolean nalerts){
        city = ncity;
        APIKey = nAPIKey;
        days = ndays;
        airQuality = nairQuality;
        alerts = nalerts;
    }
    @Override public String toString(){
        return city + APIKey + days + airQuality + alerts;
    }
    public JsonElement makeCall(){
        String aqi;
        String alert;
        if(alerts)
            alert = "yes";
        else
            alert = "no";
        if(airQuality)
            aqi = "yes";
        else
            aqi = "no";
        
        String url = "http://api.weatherapi.com/v1/forecast.json?key=" + APIKey + "&q=" + city +
            "&days=" + days + "&aqi=" + aqi + "&alerts=" + alert;
        //String exampleurl = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=London&aqi=no";
        try {
            URI uri = new URI(url);
            URL urlURL = uri.toURL();
            InputStream bytesJson = urlURL.openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(bytesJson));
            String jsonString = rd.readLine();
            return ReadJson(jsonString);
        } catch (MalformedURLException e){
            System.err.println(e + "\nThere was an error with URL");
        } catch (IOException e){
            System.err.println(e + "\nThere was an error with json stream");
        } catch(URISyntaxException e){
            System.err.println(e + "\nInvalid URL");
        } catch(Exception e){
            System.err.println("unknown error has occured");
        }
        return new JsonObject();
    }
    public static JsonElement ReadJson(String jsonString){
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(jsonString);
        return jsonElement;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAPIKey() {
        return APIKey;
    }
    public void setAPIKey(String aPIKey) {
        APIKey = aPIKey;
    }
    public int getDays() {
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }
    public boolean isAirQuality() {
        return airQuality;
    }
    public void setAirQuality(boolean airQuality) {
        this.airQuality = airQuality;
    }
    public boolean isAlerts() {
        return alerts;
    }
    public void setAlerts(boolean alerts) {
        this.alerts = alerts;
    }
    
}


/*
package com.collectweather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


public class APIinteraction {
    public static void ReadJson(String jsonString){
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(jsonString);
        System.out.print(jsonElement.toString());
    }
    public static void main(String[] args) {
        String exampleurl = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=London&aqi=no";
        //String url = exampleurl;
        try {
            InputStream bytesJson = new URL(exampleurl).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(bytesJson));
            String jsonString = rd.readLine();
            ReadJson(jsonString);
        } catch (MalformedURLException e){
            System.err.println(e + "\nThere was an error with URL");
        } catch (IOException e){
            System.err.println(e + "\nThere was an error with json stream");
        } catch(Exception e){
            System.err.println(e + "\nUnknown error has occured");
        }
       
        
    }
}
    */
