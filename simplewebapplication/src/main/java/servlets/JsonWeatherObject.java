/*
{"location":
    {"name":"Dallas","region":"Texas", 
    "country":"United States of America","lat":32.78,"lon":-96.8, "tz_id":"America/Chicago",
    "localtime_epoch":1718755779,"localtime":"2024-06-18 19:09"}
,"current":
    {"last_updated_epoch":1718755200,"last_updated":"2024-06-18 19:00",
    "temp_c":30.0,"temp_f":86.0,"is_day":1,
    "condition":
        {"text":"Partly Cloudy","icon":"//cdn.weatherapi.com/weather/64x64/day/116.png","code":1003},
    "wind_mph":14.3,"wind_kph":23.0,"wind_degree":128,"wind_dir":"SE","pressure_mb":1011.0,
    "pressure_in":29.84,"precip_mm":0.0,"precip_in":0.0,"humidity":56,"cloud":25,"feelslike_c":32.4,
    "feelslike_f":90.3,"windchill_c":30.0,"windchill_f":86.0,"heatindex_c":32.4,"heatindex_f":90.3,
    "dewpoint_c":20.3,"dewpoint_f":68.6,"vis_km":10.0,"vis_miles":6.0,"uv":8.0,"gust_mph":17.6,"gust_kph":28.4}
    }
 */

 package servlets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.Locale;

 import com.google.gson.JsonElement;
 import com.google.gson.JsonObject;
 
 public class JsonWeatherObject implements java.io.Serializable{
     String name;
     String region;
     String country;
     double latitude;
     double longitude;
     String tz_id;
     Date localtime;
 
     double temp_c;
     double temp_f;
     String generalConditions;
     String imageURL;
     double wind_mph;
     double wind_kph;
     String wind_dir;
     double pressure_mb;
     double pressure_in;
     double precip_mm;
     double precip_in;
     double humidity;
     double feelslike_c;
     double feelslike_f;
     double windchill_c;
     double windchill_f;
     double heatindex_c;
     double heatindex_f;
     
     public JsonWeatherObject(){
        name = "";
        region = "";
        country = "";
        latitude = 0;
        longitude = 0;
        tz_id = "";
        localtime = new Date();
 
        temp_c = 0;
        temp_f = 0;
        generalConditions = "";
        imageURL = "";
        wind_mph = 0;
        wind_kph = 0;
        wind_dir = "";
        pressure_mb = 0;
        pressure_in = 0;
        precip_mm = 0;
        precip_in = 0;
        humidity = 0;
        feelslike_c = 0;
        feelslike_f = 0;
        windchill_c = 0;
        windchill_f = 0;
        heatindex_c = 0;
        heatindex_f = 0;
     }
     public JsonWeatherObject(JsonElement jsonElement){
         assert (jsonElement.isJsonObject());
         JsonObject jsonObject = jsonElement.getAsJsonObject();
         JsonObject jsonLocation  = jsonObject.getAsJsonObject("location");
         JsonObject jsonCurrent = jsonObject.getAsJsonObject("current");
         setLocation(jsonLocation);
         setCurrentWeather(jsonCurrent);
     }
     public void setWeatherObject(JsonElement jsonElement){
         assert (jsonElement.isJsonObject());
         JsonObject jsonObject = jsonElement.getAsJsonObject();
         JsonObject jsonLocation  = jsonObject.getAsJsonObject("location");
         JsonObject jsonCurrent = jsonObject.getAsJsonObject("current");
         setLocation(jsonLocation);
         setCurrentWeather(jsonCurrent);
     }
     private void setLocation(JsonObject location){
         try {
             name = location.get("name").getAsString();
             region = location.get("region").getAsString();
             country = location.get("country").getAsString();
             latitude = location.get("lat").getAsDouble();
             longitude = location.get("lon").getAsDouble();
             tz_id = location.get("tz_id").getAsString();
             localtime = convertDate(location.get("localtime").getAsString());
         } catch (Exception e) {
             System.err.println("Something wrong when getting location data");
         }
     }
     private void setCurrentWeather(JsonObject current){
         try{
             temp_c = current.get("temp_c").getAsDouble();
             temp_f = current.get("temp_f").getAsDouble();
             wind_mph = current.get("wind_mph").getAsDouble();
             wind_kph = current.get("wind_kph").getAsDouble();
             wind_dir = current.get("wind_dir").getAsString();
             pressure_mb = current.get("pressure_mb").getAsDouble();
             pressure_in = current.get("pressure_in").getAsDouble();
             precip_mm = current.get("precip_mm").getAsDouble();
             precip_in = current.get("precip_in").getAsDouble();
             humidity = current.get("humidity").getAsDouble();
             feelslike_c = current.get("feelslike_c").getAsDouble();
             feelslike_f = current.get("feelslike_f").getAsDouble();
             windchill_c = current.get("windchill_c").getAsDouble();
             windchill_f = current.get("windchill_f").getAsDouble();
             heatindex_c = current.get("heatindex_c").getAsDouble();
             heatindex_f = current.get("heatindex_f").getAsDouble();
             JsonObject conditions = current.getAsJsonObject("condition");
             setWeatherConditions(conditions);
         } catch (Exception e){
             System.err.println("Something wrong with weather data");
         }
     }
     private void setWeatherConditions(JsonObject conditions){
        try {
            generalConditions = conditions.get("text").getAsString();
            imageURL = conditions.get("icon").getAsString();
            
        } catch (Exception e) {
            System.err.println("Something went wrong with weather data");
        }
     }
     public Date convertDate(String date){
         try{
             //"2024-06-18 19:09"
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
             Date dateObject = formatter.parse(date);
             return dateObject;
         } catch (ParseException e){
             System.err.println("something wrong with parsing date" + e);
         }
         return new Date();
     }
     public String getLocationName(){
         return name;
     }
 
     public String getName() {
         return name;
     }
 
     public String getRegion() {
         return region;
     }
 
     public String getCountry() {
         return country;
     }
 
     public double getLatitude() {
         return latitude;
     }
 
     public double getLongitude() {
         return longitude;
     }
 
     public String getTz_id() {
         return tz_id;
     }
 
     public Date getLocaltime() {
         return localtime;
     }
 
     public double getTemp_c() {
         return temp_c;
     }
 
     public double getTemp_f() {
         return temp_f;
     }
 
     public String getGeneralConditions() {
         return generalConditions;
     }
 
     public double getWind_mph() {
         return wind_mph;
     }
 
     public double getWind_kph() {
         return wind_kph;
     }
 
     public String getWind_dir() {
         return wind_dir;
     }
 
     public double getPressure_mb() {
         return pressure_mb;
     }
 
     public double getPressure_in() {
         return pressure_in;
     }
 
     public double getPrecip_mm() {
         return precip_mm;
     }
 
     public double getPrecip_in() {
         return precip_in;
     }
 
     public double getHumidity() {
         return humidity;
     }
 
     public double getFeelslike_c() {
         return feelslike_c;
     }
 
     public double getFeelslike_f() {
         return feelslike_f;
     }
 
     public double getWindchill_c() {
         return windchill_c;
     }
 
     public double getWindchill_f() {
         return windchill_f;
     }
 
     public double getHeatindex_c() {
         return heatindex_c;
     }
 
     public double getHeatindex_f() {
         return heatindex_f;
     }
    public String getImageURL() {
        return imageURL;
    } 
     
 }