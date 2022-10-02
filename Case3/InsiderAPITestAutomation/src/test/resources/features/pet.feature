Feature: pet endpoints test cases

  @positive
  Scenario: send post request for upload an image to pet and assert status code & response
    Given send post request for upload an image to pet
    Then assert status code for success
    And assert response for upload an image to pet

  @negative
  Scenario: send post request for upload an image to pet without image and assert status code
    Given send post request for upload an image to pet without image
    Then assert status code for unsupported media type

  @negative
  Scenario: send post request for upload an image to not created id and assert status code & response
    Given send post request for upload an image to not created id
    Then assert status code for success

  @positive
  Scenario: send post request for adding new pet to store and assert status code & response
    Given send post request for adding new pet
    Then assert status code for success
    And assert response for adding new pet

  @negative
  Scenario: send post request for adding new pet to store with wrong id value and assert status code
    Given send post request for adding new pet with wrong id value
    Then assert status code for server error

  @negative
  Scenario: send post request for adding new pet to store with wrong name value and assert status code
    Given send post request for adding new pet with wrong name value
    Then assert status code for success

  @negative
  Scenario: send post request for adding new pet to store with wrong status value and assert status code
    Given send post request for adding new pet with wrong status value
    Then assert status code for success

  @negative
  Scenario: send post request for adding new pet to store with non-alphanumeric chars and assert status code
    Given send post request for adding new pet with non-alphanumeric chars
    Then assert status code for bad request

  @positive
  Scenario: send put request for update an existing pet and assert status code & response
    Given send put request to existing pet
    Then assert status code for success
    And assert response for update an existing pet

  @negative
  Scenario: send put request for update an existing pet with wrong id value and assert status code
    Given send put request to existing pet with wrong id value
    Then assert status code for server error

  @negative
  Scenario: send put request for update an existing pet with wrong name value and assert status code
    Given send put request to existing pet with wrong name value
    Then assert status code for success

  @negative
  Scenario: send put request for update an existing pet with wrong status value and assert status code
    Given send put request to existing pet with wrong status value
    Then assert status code for success

  @negative
  Scenario: send put request for update an existing pet with non-alphanumeric chars and assert status code
    Given send put request to existing pet with non-alphanumeric chars
    Then assert status code for success

  @positive
  Scenario: send get request with status available for find pets by status and assert status code & response
    Given send get request with status available
    Then assert status code for success
    And assert response for find pet by status available

  @positive
  Scenario: send get request with status pending for find pets by status and assert status code & response
    Given send get request with status pending
    Then assert status code for success
    And assert response for find pet by status pending

  @positive
  Scenario: send get request with status sold for find pets by status and assert status code & response
    Given send get request with status sold
    Then assert status code for success
    And assert response for find pet by status sold

  @negative
  Scenario: send get request with wrong status for find pets by status and assert status code
    Given send get request with wrong status
    Then assert status code for success

  @positive
  Scenario: send get request with petId for find pet by Id and assert status code & response
    Given send get request with petId
    Then assert status code for success
    And assert response for find pet by Id

  @positive
  Scenario: send post request with name & status for update a pet and assert status code & response
    Given send post request with form data
    Then assert status code for success

  @negative
  Scenario: send post request with wrong name & status for update a pet and assert status code
    Given send post request with form data wrong name & status
    Then assert status code for success

  @positive
  Scenario: send delete request for delete a pet and assert status code & response
    Given send delete request with petId
    Then assert status code for success
    And assert response for delete a pet