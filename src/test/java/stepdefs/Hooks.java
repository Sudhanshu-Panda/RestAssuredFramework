package stepdefs;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@deltePlace")
	public void getPlaceID() throws IOException
	{
		StepDefinations stepdef = new StepDefinations();
		if(StepDefinations.placeId==null)
		{
			stepdef.add_place_payload_with("pizza","pizza hut","American");
			stepdef.user_calls_something_with_something_https_request("AddPlaceAPI","Post");
			stepdef.verify_the_place_added_in_maps_belongs_to_using("pizza","GetPlaceAPI");
		}
	}
}
