Feature: Validating PLace APIs
@addPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
	Given Add Place payload with "<name>" "<address>" "<language>"
	When user calls "AddPlaceAPI" with "Post" https request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	Then Verify the place added in maps belongs to "<name>" using "GetPlaceAPI"
	
	Examples:
	|name					 |address					 |language|
	|Biryani Centre|27 henning avenue|English |
#	|Kabab centre	 |7 lyon park strt |Hindi 	|

@deltePlace  #tags - helps to run particular scenarios
Scenario: Verify if delete place API is working
Given Delete place pay load
When user calls "DeletePlaceAPI" with "Post" https request
Then the API call got success with status code 200
And "status" in response body is "OK"
