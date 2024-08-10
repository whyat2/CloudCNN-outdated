package servlets;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "WeatherSearch", urlPatterns = "/search") //urlPatterns = {"/x/* */", "/y", "/x/y"} allows multiple
public class WeatherSearch extends HttpServlet{
    GetDataFromWeather weatherAPI;
    JsonWeatherObject weatherSearch;
    @Override
    public void init() throws ServletException{
        super.init();
        weatherAPI = new GetDataFromWeather();
        weatherSearch = new JsonWeatherObject();
        try {
            weatherSearch.setWeatherObject(weatherAPI.makeCall());
        } catch (Exception e) {
            System.err.println("Something is wrong with objects");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String city = request.getParameter("city");
        if(city != null){
            weatherAPI.setCity(city);
            weatherSearch.setWeatherObject(weatherAPI.makeCall());
            request.setAttribute("weather", weatherSearch);
            request.setAttribute("temp_f", weatherSearch.getTemp_f() + "");
            request.setAttribute("temp_c", weatherSearch.getTemp_c() + "");
            request.setAttribute("wind_mph", weatherSearch.getWind_mph() + "");
            request.setAttribute("wind_kph", weatherSearch.getWind_kph() + "");
            request.setAttribute("wind_dir", weatherSearch.getWind_dir() + "");
            request.setAttribute("pressure_in", weatherSearch.getPressure_in() + "");
            request.setAttribute("pressure_mb", weatherSearch.getPressure_mb() + "");
            request.setAttribute("humidity", weatherSearch.getHumidity() + "");
            request.setAttribute("precip_in", weatherSearch.getPrecip_in() + "");
            request.setAttribute("precip_mm", weatherSearch.getPrecip_mm() + "");
            request.setAttribute("windchill_f", weatherSearch.getWindchill_f() + "");
            request.setAttribute("windchill_c", weatherSearch.getWindchill_c() + "");
            request.setAttribute("feelslike_f", weatherSearch.getFeelslike_f() + "");
            request.setAttribute("feelslike_c", weatherSearch.getFeelslike_c() + "");
            request.setAttribute("heatindex_f", weatherSearch.getHeatindex_f() + "");
            request.setAttribute("heatindex_c", weatherSearch.getHeatindex_c() + "");
        }
        getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("weather", weatherSearch);
        request.setAttribute("temp_f", weatherSearch.getTemp_f() + "");
        request.setAttribute("temp_c", weatherSearch.getTemp_c() + "");
        request.setAttribute("wind_mph", weatherSearch.getWind_mph() + "");
        request.setAttribute("wind_kph", weatherSearch.getWind_kph() + "");
        request.setAttribute("wind_dir", weatherSearch.getWind_dir() + "");
        request.setAttribute("pressure_in", weatherSearch.getPressure_in() + "");
        request.setAttribute("pressure_mb", weatherSearch.getPressure_mb() + "");
        request.setAttribute("humidity", weatherSearch.getHumidity() + "");
        request.setAttribute("precip_in", weatherSearch.getPrecip_in() + "");
        request.setAttribute("precip_mm", weatherSearch.getPrecip_mm() + "");
        request.setAttribute("windchill_f", weatherSearch.getWindchill_f() + "");
        request.setAttribute("windchill_c", weatherSearch.getWindchill_c() + "");
        request.setAttribute("feelslike_f", weatherSearch.getFeelslike_f() + "");
        request.setAttribute("feelslike_c", weatherSearch.getFeelslike_c() + "");
        request.setAttribute("heatindex_f", weatherSearch.getHeatindex_f() + "");
        request.setAttribute("heatindex_c", weatherSearch.getHeatindex_c() + "");
        getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);

    }
}
