@create_customer
Feature: Create customer API Test

  Customer record can be created using
  the API provided. The request should be
  a JSON request with all valid parameters.
  On valid request it will return 200 as HTTP
  status code with new Customer details in JSON
  object. On invalid request it will return HTTP
  status code depending on the validation type
  example 404, 405 etc.

  @positive
  Scenario: Create a customer with valid details
    Given The app database has dummy customer data
    And The client has customer data User 5,Kolkata,false,INACTIVE,1,1
    When The client send POST using API /customers
    Then The client should receive 201 as HTTP status code
    And Customer database should be updated

  @positive
  Scenario: Create a customer with blank onboard status
    Given The app database has dummy customer data
    And The client has customer data User 6,Bangalore,ACTIVE,2,1 but no onboard status
    When The client send POST using API /customers
    Then The client should receive 201 as HTTP status code
    And Customer database should be updated

  @positive
  Scenario: Create a customer with blank status
    Given The app database has dummy customer data
    And The client has customer data User 7,Mumbai,true,3,2 but no status
    When The client send POST using API /customers
    Then The client should receive 201 as HTTP status code
    And Customer database should be updated

  @positive
  Scenario: Create a customer with special characters
    Given The app database has dummy customer data
    And The client has customer data `~!@#$%^&*()_-+=|\\}]{[\"':;?/>.<,,`~!@#$%^&*()_-+=|\\}]{[\"':;?/>.<,,true,ACTIVE,2,2
    When The client send POST using API /customers
    Then The client should receive 201 as HTTP status code
    And Customer database should be updated

  @negative
  Scenario: Check validation on customer creation without mandatory name
    Given The app database has dummy customer data
    And The client has customer data Pune,false,INACTIVE,1,1 without name
    When The client send POST using API /customers
    Then The client should receive 400 as HTTP status code
    And Client should get error message Request validation failed
    And Customer database should not be updated

  @negative
  Scenario: Check validation on customer creation without mandatory address
    Given The app database has dummy customer data
    And The client has customer data User 10,true,ACTIVE,3,4 without address
    When The client send POST using API /customers
    Then The client should receive 400 as HTTP status code
    And Client should get error message Request validation failed
    And Customer database should not be updated

  @negative
  Scenario: Check validation on customer creation with blank view id
    Given The app database has dummy customer data
    And The client has customer data User 4,Chennai,true,BLOCKED,2 but no view id
    When The client send POST using API /customers
    Then The client should receive 404 as HTTP status code
    And Customer database should not be updated

  @negative
  Scenario: Check validation on customer creation with blank workflow id
    Given The app database has dummy customer data
    And The client has customer data User 5,Delhi,false,ACTIVE,4 but no workflow id
    When The client send POST using API /customers
    Then The client should receive 404 as HTTP status code
    And Customer database should not be updated

  @negative
  Scenario: Check validation on customer creation with invalid view
    Given The app database has dummy customer data
    And Client has customer data User 11,Jaypur,true,INACTIVE,-7,4
    When The client send POST using API /customers
    Then The client should receive 404 as HTTP status code
    And Client should get error message Specified view does not exist
    And Customer database should not be updated

  @negative
  Scenario: Check validation on customer creation with invalid workflow
    Given The app database has dummy customer data
    And Client has customer data User 12,Goa,true,ACTIVE,3,-9
    When The client send POST using API /customers
    Then The client should receive 404 as HTTP status code
    And Client should get error message Specified workflow does not exist
    And Customer database should not be updated

    @negative
    Scenario: Check validation on customer creation with invalid request format containing id
      Given A client has customer data 1, User 13,Ahmedabad,true,ACTIVE,2,1
      When The client send POST using API /customers
      Then The client should receive 400 as HTTP status code
      And Client should get error message The request could not be understood by the server due to malformed syntax
      And Customer database should not be updated

  @negative
  Scenario: Check validation on customer creation with invalid request URL
    Given The client has customer data User 14,Noida,true,ACTIVE,2,1
    When The client send POST using API /customers/abc
    Then The client should receive 405 as HTTP status code
    And Client should get error message Method Not Supported
    And Customer database should not be updated

  @negative
  Scenario: Check validation on customer creation with invalid request URL
    Given The client has customer data User 13,Gurgaon,true,ACTIVE,2,1
    When The client send POST using API /custom
    Then The client should receive 404 as HTTP status code
    And Customer database should not be updated

  @negative
  Scenario: Check validation on customer creation with invalid status
    Given The client has customer data User 14,Gangtok,true,ACTIVE1,2,1
    When The client send POST using API /customers
    Then The client should receive 400 as HTTP status code
    And Client should get error message The request could not be understood by the server due to malformed syntax
    And Customer database should not be updated

  @negative
  Scenario: Check validation on customer creation with invalid onboard status
    Given A client has customer data User 15,Dargeeling,abc,ACTIVE,2,1 having invalid onboard status
    When The client send POST using API /customers
    Then The client should receive 400 as HTTP status code
    And Client should get error message The request could not be understood by the server due to malformed syntax
    And Customer database should not be updated