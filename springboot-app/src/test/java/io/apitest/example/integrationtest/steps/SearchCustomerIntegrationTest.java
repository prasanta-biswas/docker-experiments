package io.apitest.example.integrationtest.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.apitest.example.integrationtest.CustomerAppBaseIntegrationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by prasantabiswas on 29/06/18.
 */
public class SearchCustomerIntegrationTest extends CustomerAppBaseIntegrationTest{

    private static final Logger logger = LoggerFactory.getLogger(SearchCustomerIntegrationTest.class);

    @When("^The client send GET request with API (.+)$")
    public void sendGETRequest(String URL) throws Exception {
        logger.info("Execute GET request");
        resultActions = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON));
    }

    @And("^The client should receive the list of available customer records$")
    public void verifyGetAllCustomers() throws Exception {
        resultActions.andExpect(jsonPath("$",hasSize(8))).andDo(print());
    }

    @And("^The client should receive customer records$")
    public void verifyGetCustomerById() throws Exception {
        resultActions.andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.address").exists())
                .andExpect(jsonPath("$.onboarded").exists())
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.viewId").exists())
                .andExpect(jsonPath("$.workflowId").exists())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("User 2"))
                .andExpect(jsonPath("$.address").value("Bangalore, Karnataka"))
                .andExpect(jsonPath("$.onboarded").value(false))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andExpect(jsonPath("$.viewId").value(2))
                .andExpect(jsonPath("$.workflowId").value(2))
                .andDo(print());
    }

}
