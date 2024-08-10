<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
    <head>
        <!--<meta charset="ISO-8859-10">-->
        <link rel="stylesheet" href="./myStyles.css" type="text/css">
		<title>Search for the weather</title>
    </head>
    <body>
        <div>
            <div class="Navigation">
                <a href="/simplewebapp">
                    <button class="SpanningButton">Home</button>
                </a>
                <a href="/simplewebapp/search">
                    <button class="SpanningButton">SearchWeather</button>
                </a>
                <a href="/simplewebapp/AI">
                    <button class="SpanningButtons">Generate Clouds</button>
                </a>
            </div>
            <div>
                <form class="SearchBar" action="/simplewebapp/search" method="post">
                    <label>Enter the city:</label>
                    <input type="text" id="city" name="city" required/>
                    <input type="submit" value="SearchWeather"/>
                </form>
            </div>
            <div>
                <div class="MainWeatherInfo">
                    <img
                        src="https://wixplosives.github.io/codux-assets-storage/add-panel/image-placeholder.jpg"
                        alt=""
                    />
                    <div>
                        <h2>Temperature (°F)</h2>
                        <h1>${temp_f}</h1>
                        <h2>Temperature (°C)</h2>
                        <h1>${temp_c}</h1>
                    </div>
                </div>
                <div class="WeatherDetails">
                    <div>
                        <h3>Wind Conditions</h3>
                        <p>Wind Speed: ${wind_mph} mph / ${wind_kph} kph</p>
                        <p>Wind Direction: ${wind_dir}</p>
                    </div>
                    <div>
                        <h3>Air conditions</h3>
                        <p>Air Pressure: ${pressure_in} in / ${pressure_mb} mb</p>
                        <p>Humidity: ${humidity}</p>
                    </div>
                    <div>
                        <h3>Precipitation Conditions</h3>
                        <p>Precipitation amount: ${precip_in} in / ${precip_mm} mm</p>
                    </div>
                    <div>
                        <h3>Temperature but different</h3>
                        <p>Windchill: ${windchill_f} (°F) / ${windchill_c} (°C)</p>
                        <p>Feels like: ${feelslike_f} (°F) / ${feelslike_c} (°C)</p>
                        <p>Heat index: ${heatindex_f} (°F) / ${heatindex_c} (°C)</p>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>