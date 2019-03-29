# api-test
It contains a demo of a Customer Onboard app for testing purpose.
This demo uses `springboot` framework to build and mock a prototype of the Customer App for testing. 

The **Customer Onboarding Application** enable customization and a tenant who buys this 
application can customize the fields to be captured, the validations to be performed, 
number of views, sub Views, and the workflow for approval. The entire configuration is stored as json in Mongo. 
When a user logs in based on this configuration dynamic views are presented so that the Customers can be onboarded.

**Note:** We have used spring jpa repository to mock database instead of Mongo

## Prerequisites
 1. JDK 1.7 or above
 2. Maven 3 or above

## Integration test
Integration tests for the Customer App are automated using `Cucumber` `BDD` based 
test scenarios. So we do not need any other test scenario documnet as tht 
 `Cucumber` feature files(`src/test/resources/features`) contain the live document of the test scenarions which
are in plan english language.

The integration test covers the two APIs
 1. `/customers` used to create a customer
 2. `/customers/{id}` used to get a customer record
 
 It covers all possible positive and negative scenarios with HTTP Status code 
 validations.
 
 #### Run Integration Test
 We can run the integration test using simple maven command. First get inside the project folder and run the following maven command
 
    mvn failsafe:integration-test
 
 It will generate different test report (`JSON`, `HTML` etc) inside build folder as `target/` and `target/reports`
 
 We can also run unit tests along with integration tests as follows
    
    mvn clean test
    
 It will generate an additional test report i.e. junit.xml report inside `/target` folder
 
 ## Run Customer App
 We can do manual testing as well by running the jar. 
  1. First we need to package the application so that we can run the app. Run the following maven command
  
    mvn clean package
    
  2. Now we will have our jar package inside `target` folder. We can run the jar to have our app running
  
    java -jar target/api-test-*.jar     #Replace * with the version of the jar
    
 Now we can access the APIs using cURL or any other REST client. For example if we hit the following API in browser, we wil get the list of available customers
 
    http://localhost:8080/demo/api/customers
    
   Output: 
    
    [
       {
          "id":1,
          "name":"User 1",
          "address":"Kolkata, West Bengal",
          "status":"INACTIVE",
          "viewId":1,
          "workflowId":1,
          "onboarded":false
       },
       {
          "id":2,
          "name":"User 2",
          "address":"Bangalore, Karnataka",
          "status":"ACTIVE",
          "viewId":2,
          "workflowId":2,
          "onboarded":false
       },
       {
          "id":3,
          "name":"User 3",
          "address":"Mumbai, Maharastra",
          "status":"BLOCKED",
          "viewId":3,
          "workflowId":3,
          "onboarded":true
       },
       {
          "id":4,
          "name":"User 4",
          "address":"Chennai, Tamilnadu",
          "status":"ACTIVE",
          "viewId":4,
          "workflowId":4,
          "onboarded":false
       }
    ]