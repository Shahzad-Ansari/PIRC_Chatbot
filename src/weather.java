
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;


public class weather {

    public static void main(String[] args) 
    		throws IOException, MalformedURLException, JSONException {

    	
        // declaring object of "OpenWeatherMap" class
        OpenWeatherMap owm = new OpenWeatherMap("");

       owm.setApiKey("70969020786eb251c0f5eeaff21bfdf1");
        
        // getting current weather data for the "London" city
        CurrentWeather cwd = owm.currentWeatherByCityName("DALLAS"+ "");
        

        
        // checking data retrieval was successful or not
        if (cwd.isValid()) {

            // checking if city name is available
            if (cwd.hasCityName()) {
                //printing city name from the retrieved data
                System.out.println("City: " + cwd.getCityName());
            }

            // checking if max. temp. and min. temp. is available
            if (cwd.getMainInstance().hasMaxTemperature() && cwd.getMainInstance().hasMinTemperature()) {
                // printing the max./min. temperature
                System.out.println("Temperature: " + cwd.getMainInstance().getMaxTemperature()
                            + "/" + cwd.getMainInstance().getMinTemperature() + "\'F");
            }
        }
    }
}