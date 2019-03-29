package io.apitest.example.integrationtest.steps;

import com.google.gson.Gson;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.apitest.example.enums.CustomerStatus;
import io.apitest.example.integrationtest.CustomerAppBaseIntegrationTest;
import io.apitest.example.model.Customer;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by prasantabiswas on 29/06/18.
 */
public class CreateCustomerIntegrationTest extends CustomerAppBaseIntegrationTest {

    private static int dbState = 0;
    private static String requestObject;
    private static final Logger logger = LoggerFactory.getLogger(CreateCustomerIntegrationTest.class);
    private static final Gson gson = new Gson();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Given("^The app database has dummy customer data$")
    public void verifyAppData() {
        logger.info("Verifying dummy data presence");
        dbState = customerService.getAllCustomer().size();
        Assert.assertTrue("No dummy data present",dbState > 0);
    }

    @And("^The client has customer data (.+),(.+),(.+),(.+),(.+),(.+)$")
    public void createRequestObject(String name, String address, boolean onboarded, String status, long viewId, long workflowId) {
        requestObject = "{" +
                    "\"name\":"+"\""+name+"\""+"," +
                    "\"address\":"+"\""+address+"\""+"," +
                    "\"onboarded\":"+onboarded+"," +
                    "\"status\":"+"\""+status+"\""+"," +
                    "\"viewId\":"+viewId+", " +
                    "\"workflowId\":"+workflowId+" " +
                "}";
        Customer customer = gson.fromJson(requestObject,Customer.class);
        logger.info("Request object created:"+ requestObject);
        Assert.assertTrue("Invalid request parameter",customer != null);
    }

    @When("^The client send POST using API (.+)$")
    public void sendPOSTRequest(String URL) throws Exception {
        logger.info("Execute POST request");
        resultActions = mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestObject)
                .accept(MediaType.APPLICATION_JSON));
        Assert.assertTrue(true);
    }

    @Then("^The client should receive (.+) as HTTP status code$")
    public void verifyHTTPStatusCode(int status) throws Exception {
        resultActions.andExpect(status().is(status));
    }

    @And("^Customer database should be updated$")
    public void verifyCustomerUpdatedDatabase() throws Exception {
        logger.info("Check customer database update");
        resultActions.andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.address").exists())
                .andExpect(jsonPath("$.onboarded").exists())
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.viewId").exists())
                .andExpect(jsonPath("$.workflowId").exists())
                .andDo(print());
        int newDbState = customerService.getAllCustomer().size();
        Assert.assertTrue("Creation customer verification failed", newDbState > dbState);
    }

    @And("^The client has customer data (.+),(.+),(.+),(.+),(.+) but no onboard status$")
    public void createRequestObject(String name, String address, CustomerStatus status, long viewId, long workflowId) {
        requestObject = "{" +
                "\"name\":"+"\""+name+"\""+"," +
                "\"address\":"+"\""+address+"\""+"," +
                "\"status\":"+"\""+status+"\""+"," +
                "\"viewId\":"+viewId+", " +
                "\"workflowId\":"+workflowId+" " +
                "}";
        Customer customer = gson.fromJson(requestObject,Customer.class);
        logger.info("Request object created:"+ requestObject);
        Assert.assertTrue("Invalid request parameter",customer != null);
    }

    @And("^The client has customer data (.+),(.+),(.+),(.+),(.+) but no status$")
    public void createRequestObject(String name, String address, boolean onboarded, long viewId, long workflowId) {
        requestObject = "{" +
                "\"name\":"+"\""+name+"\""+"," +
                "\"address\":"+"\""+address+"\""+"," +
                "\"onboarded\":"+onboarded+"," +
                "\"viewId\":"+viewId+", " +
                "\"workflowId\":"+workflowId+" " +
                "}";
        Customer customer = gson.fromJson(requestObject,Customer.class);
        logger.info("Request object created:"+ requestObject);
        Assert.assertTrue("Invalid request parameter",customer != null);
    }

    @And("^The client has customer data (.+),(.+),(.+),(.+),(.+) but no view id$")
    public void createRequestWithNoView(String name, String address, boolean onboarded, CustomerStatus status, long workflowId) {
        requestObject = "{" +
                "\"name\":"+"\""+name+"\""+"," +
                "\"address\":"+"\""+address+"\""+"," +
                "\"onboarded\":"+onboarded+"," +
                "\"status\":"+status.ordinal()+"," +
                "\"workflowId\":"+workflowId+" " +
                "}";
        Customer customer = gson.fromJson(requestObject,Customer.class);
        logger.info("Request object created:"+ requestObject);
        Assert.assertTrue("Invalid request parameter",customer != null);
    }

    @And("^The client has customer data (.+),(.+),(.+),(.+),(.+) but no workflow id$")
    public void createRequestWithNoWorkflow(String name, String address, boolean onboarded, CustomerStatus status, long viewId) {
        requestObject = "{" +
                "\"name\":"+"\""+name+"\""+"," +
                "\"address\":"+"\""+address+"\""+"," +
                "\"onboarded\":"+onboarded+"," +
                "\"status\":"+"\""+status+"\""+"," +
                "\"viewId\":"+viewId+" " +
                "}";
        Customer customer = gson.fromJson(requestObject,Customer.class);
        logger.info("Request object created:"+ requestObject);
        Assert.assertTrue("Invalid request parameter",customer != null);
    }

    @And("^Customer database should not be updated$")
    public void verifyCustomerDatabase() throws Exception {
        logger.info("Check customer database update");
        resultActions.andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.name").doesNotExist())
                .andExpect(jsonPath("$.address").doesNotExist())
                .andExpect(jsonPath("$.onboarded").doesNotExist())
                .andExpect(jsonPath("$.status").doesNotExist())
                .andExpect(jsonPath("$.viewId").doesNotExist())
                .andExpect(jsonPath("$.workflowId").doesNotExist())
                .andDo(print());
        int newDbState = customerService.getAllCustomer().size();
        Assert.assertTrue("Creation customer verification failed", newDbState == dbState);
    }

    @And("^The client has customer data (.+),(.+),(.+),(.+),(.+) without name$")
    public void createRequestWithoutName(String address, boolean onboarded, CustomerStatus status, long viewId, long workflowId) {
        requestObject = "{" +
                "\"address\":"+"\""+address+"\""+"," +
                "\"onboarded\":"+onboarded+"," +
                "\"status\":"+"\""+status+"\""+"," +
                "\"status\":"+viewId+"," +
                "\"workflowId\":"+workflowId+" " +
                "}";
        Customer customer = gson.fromJson(requestObject,Customer.class);
        logger.info("Request object created:"+ requestObject);
        Assert.assertTrue("Invalid request parameter",customer != null);
    }

    @And("^The client has customer data (.+),(.+),(.+),(.+),(.+) without address$")
    public void createRequestWithoutAddress(String name, boolean onboarded, CustomerStatus status, long viewId, long workflowId) {
        requestObject = "{" +
                "\"name\":"+"\""+name+"\""+"," +
                "\"onboarded\":"+onboarded+"," +
                "\"status\":"+"\""+status+"\""+"," +
                "\"viewId\":"+viewId+"," +
                "\"workflowId\":"+workflowId+" " +
                "}";
        Customer customer = gson.fromJson(requestObject,Customer.class);
        logger.info("Request object created:"+ requestObject);
        Assert.assertTrue("Invalid request parameter",customer != null);
    }

    @And("^Client has customer data (.+),(.+),(.+),(.+),(.+),(.+)$")
    public void createRequestWithInvalidView(String name, String address, boolean onboarded, String status, long viewId, long workflowId) {
        requestObject = "{" +
                "\"name\":"+"\""+name+"\""+"," +
                "\"address\":"+"\""+address+"\""+"," +
                "\"onboarded\":"+onboarded+"," +
                "\"status\":"+"\""+status+"\""+"," +
                "\"viewId\":"+viewId+"," +
                "\"workflowId\":"+workflowId+" " +
                "}";
        Customer customer = gson.fromJson(requestObject,Customer.class);
        logger.info("Request object created:"+ requestObject);
        Assert.assertTrue("Invalid request parameter",customer != null);
    }

    @And("^Client should get error message (.+)$")
    public void verifyExceptionMessage(String exceptionMessage) throws Exception {
        resultActions.andExpect(jsonPath("$.message").value(exceptionMessage))
                .andDo(print());
    }

    @Given("^A client has customer data (.+),(.+),(.+),(.+),(.+),(.+),(.+)$")
    public void createRequestWithId(long id, String name, String address, boolean onboarded, CustomerStatus status, long viewId, long workflowId) {
        requestObject = "{" +
                "\"id\":"+id+"," +
                "\"name\":"+"\""+name+"\""+"," +
                "\"address\":"+"\""+address+"\""+"," +
                "\"onboarded\":"+onboarded+"," +
                "\"status\":"+"\""+status+"\""+"," +
                "\"viewId\":"+viewId+"," +
                "\"workflowId\":"+workflowId+" " +
                "}";
        Customer customer = gson.fromJson(requestObject,Customer.class);
        logger.info("Request object created:"+ requestObject);
        Assert.assertTrue("Invalid request parameter",customer != null);
    }

    @Given("^A client has customer data (.+),(.+),(.+),(.+),(.+),(.+) having invalid onboard status$")
    public void createRequestWithInvalidOnboardStatusFormat(String name, String address, String onboarded, CustomerStatus status, long viewId, long workflowId) {
        requestObject = "{" +
                "\"name\":"+"\""+name+"\""+"," +
                "\"address\":"+"\""+address+"\""+"," +
                "\"onboarded\":"+"\""+onboarded+"\""+"," +
                "\"status\":"+"\""+status+"\""+"," +
                "\"viewId\":"+viewId+"," +
                "\"workflowId\":"+workflowId+" " +
                "}";
        Customer customer = gson.fromJson(requestObject,Customer.class);
        logger.info("Request object created:"+ requestObject);
        Assert.assertTrue("Invalid request parameter",customer != null);
    }
}
