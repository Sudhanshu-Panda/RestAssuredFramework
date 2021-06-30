package resorces;

import java.util.ArrayList;
import java.util.List;

import pojo.AddLocation;
import pojo.Location;

public class TestDataBuild {

	public AddLocation addPlacePayload(String name, String address, String language)
	{
		AddLocation al = new AddLocation();
		// Give values
				al.setAccuracy(50);
				al.setAddress(address);
				al.setName(name);
				al.setPhone_number("(+91) 983 893 3937");
				al.setWebsite("http://google.com");
				al.setLanguage(language);
				
				//Give list to type
				List<String> types = new ArrayList<String>();
				types.add("shoe park");
				types.add("shop");
				
				al.setTypes(types);
				//this how to pass list
				
				//To pass values to nested jason , create object of another class
				Location loc = new Location();  //Here location is the class for nested json
				
				//Give values to variable inside the class
				loc.setLat(-91.5485);
				loc.setLng(94.215);
				
				//Pass the object to setter of main class or parent class
				al.setLocation(loc);
				
				return al;
	}
	
	
	public String deletePlacePayload(String place_id)
	{
		return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}";
	}
}
