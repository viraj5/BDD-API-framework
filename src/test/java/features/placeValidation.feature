Feature: Validating Place API's
  @Addplace
  Scenario Outline: Verify if the place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>""<language>""<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the api call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      |name|language|address|
      |Alihouse|English|World trade center|

    @DeletePlace
    Scenario: Verify if delete place functionality is working
      Given DeletePlace payload
      When user calls "deletePlaceAPI" with "POST" http request
      Then the api call got success with status code 200
      And "status" in response body is "OK"