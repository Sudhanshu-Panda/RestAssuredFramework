package resorces;

//enum is a special class in java which has collection of constants and methods
public enum APIResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	UpdatePlaceAPI("/maps/api/place/update/json");
	private String resource;
	
	APIResources(String resource) 
	{
		this.resource=resource;
	}
	
	public String getResourceValue()
	{
		return resource;
	}

}
