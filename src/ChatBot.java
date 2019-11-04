import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import org.jibble.pircbot.*;
import org.json.JSONException;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import java.net.HttpURLConnection;
import java.net.URL;


public class ChatBot  extends PircBot {
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	public ChatBot ( ){
		this.setName("ShahBot");
	}
	
	public void onMessage (String channel , String sender , String login , String hostname , String message)
	{
		// is it the help request ... 
	if (message.equalsIgnoreCase("help")){
			// show help
			sendMessage(channel,sender + " Acceptable commands are: help, rates, time, iss and weather>city name");
			}
	// is it the time request
	else if (message.equalsIgnoreCase("time")){
				// find the time 
				String time = new java.util.Date().toString();
				// display the time
				sendMessage(channel, sender +" : the time is now " + time);
			}
	
	// if the request is for rates for EUR, GBP and CAD ... 
	else if( message.equalsIgnoreCase("rates")){
		
					Rates();
					
	}
	
	// finds the lat and long of the iss
	else if ( message.equalsIgnoreCase("ISS")){
				
				ISS();
				
	}
	
	else {	
					//now check  for weather request
					String cmdText = "";
					String locText = "";
					
					// locate the separator character
					int posSep = message.indexOf('>');
	
					// if the separator is present and it is not the last character ... then ...
					if (posSep > 0 && posSep < message.length()){
						// extract out the command request string
						cmdText = message.substring(0,posSep);
						// extract out the location string
						locText = message.substring(posSep+1,message.length());
						// trim both strings of any extra spaces
						cmdText = cmdText.trim();
						locText = locText.trim();
	
						// is it the weather request 
						if (cmdText.equalsIgnoreCase("weather")){	
							// call the weather object and pass in location 
							try {
								weather(locText);
							} 
							catch (IOException | JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						}	
					}
			}
	}

	
	
    public void weather (String cityname ) throws IOException, MalformedURLException, JSONException{
	
	  // declaring object of "OpenWeatherMap" class
    OpenWeatherMap owm = new OpenWeatherMap("");

   owm.setApiKey("70969020786eb251c0f5eeaff21bfdf1");
    
    CurrentWeather cwd = owm.currentWeatherByCityName(cityname+ "");

	    if (cwd.isValid()) {
	        if (cwd.getMainInstance().hasMaxTemperature() && cwd.getMainInstance().hasMinTemperature()&&cwd.getMainInstance().hasHumidity()&&cwd.getMainInstance().hasPressure()){
		        	float temprature = cwd.getMainInstance().getTemperature();
		        	float Humidity = cwd.getMainInstance().getHumidity() ;
		        	float Pressure = cwd.getMainInstance().getPressure();
		        	String output = "Temperature is : " + temprature + "  The Humidity is : " + Humidity + "  percent  " +   "  The pressure is : " + Pressure + "  hPA  " ;
		            sendMessage("#SHAHTEST", output);
		            
	        }
	        
	        
	    }
		
		
	}
	

    public void Rates ( )
    {
    	try
    	{
			// URL object
			URL objURL = new URL("http://api.fixer.io/latest?base=USD");
			// establish the connection
			HttpURLConnection con = (HttpURLConnection) objURL.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			// open the input buffer reader to read 
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			// read in the response from the URL 
			String inputLine =  in.readLine(); 
			
			inputLine = inputLine.replace("{", "");
			inputLine = inputLine.replace("}", "");
			inputLine = inputLine.replace("\"", "");
			
			// output the response text - its a JSON object
            sendMessage("#SHAHTEST",  inputLine);      
            // close the buffer
            in.close();
    		
    	}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
    }
	
    
  
    
    public void ISS ( )
    {
    	try
    	{
			// URL object
			//URL objURL = new URL("http://api.fixer.io/latest?base=USD");
    		URL objURL = new URL("http://api.open-notify.org/iss-now.json");
			// establish the connection
			HttpURLConnection con = (HttpURLConnection) objURL.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			// open the input buffer reader to read 
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			// read in the response from the URL 
			String inputLine =  in.readLine(); 
			
			inputLine = inputLine.replace("{", "");
			inputLine = inputLine.replace("}", "");
			inputLine = inputLine.replace("\"", "");
			
			// output the response text - its a JSON object
            sendMessage("#SHAHTEST",  inputLine);      
            // close the buffer
            in.close();
    		
    	}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}



