@search_customer
Feature: Search customer

  Customer details can be fetched using the
  API provided. We can get either all customer
  list with details or a specific customer details.
  The latter case needs the customer id to be specified
  with the API.

  @positive
  Scenario: Fetch all existing customer
    Given The app database has dummy customer data
    When The client send GET request with API /customers
    Then The client should receive 200 as HTTP status code
    And The client should receive the list of available customer records

  @positive
  Scenario: Search customer by ID
    Given The app database has dummy customer data
    When The client send GET request with API /customers/2
    Then The client should receive 200 as HTTP status code
    And The client should receive customer records

  @negative
  Scenario: Check validation on search customer with invalid API path
    Given The app database has dummy customer data
    When The client send GET request with API /customers/f
    Then The client should receive 400 as HTTP status code
    And Client should get error message The request could not be understood by the server due to malformed syntax

  @negative
  Scenario: Check validation on search customer with invalid API path
    Given The app database has dummy customer data
    When The client send GET request with API /abc
    Then The client should receive 404 as HTTP status code

  @negative
  Scenario: Check validation on search customer with invalid customer id
    Given The app database has dummy customer data
    When The client send GET request with API /customers/0
    Then The client should receive 404 as HTTP status code
    And Client should get error message Customer doesn't exist