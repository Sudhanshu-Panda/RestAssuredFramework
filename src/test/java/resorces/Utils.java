package resorces;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("log.txt"));  //file to log reuest
		
		
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))             //to log request
				.addFilter(ResponseLoggingFilter.logResponseTo(log))			//to log response
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		return req;
		}
		return req;
	}
	
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();  //Property class of java
		FileInputStream fis = new FileInputStream("src/test/java/resorces/global.properties");
		prop.load(fis);  //now property now the file location
		return prop.getProperty(key);
		
		
	}
	
	public String getJsonPath(Response response, String value)
	{
		String res = response.asString();
	    JsonPath js= new JsonPath(res);
	    return js.getString(value);
	}
}
