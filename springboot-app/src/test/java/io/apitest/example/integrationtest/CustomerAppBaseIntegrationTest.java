package io.apitest.example.integrationtest;

import io.apitest.example.CustomerApp;
import io.apitest.example.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ContextConfiguration(classes = CustomerApp.class)
@WebAppConfiguration
public class CustomerAppBaseIntegrationTest {

    protected static MockMvc mockMvc;
    protected static ResultActions resultActions;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    protected CustomerService customerService;

}
