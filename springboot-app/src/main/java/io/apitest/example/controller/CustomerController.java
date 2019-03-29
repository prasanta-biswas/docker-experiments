package io.apitest.example.controller;

import io.apitest.example.enums.CustomerStatus;
import io.apitest.example.exception.CustomerNotFoundException;
import io.apitest.example.exception.ViewNotFoundException;
import io.apitest.example.exception.WorkflowNotFoundException;
import io.apitest.example.interfaces.CustomerService;
import io.apitest.example.interfaces.ViewService;
import io.apitest.example.interfaces.WorkflowService;
import io.apitest.example.model.Customer;
import io.apitest.example.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by prasantabiswas on 27/06/18.
 */

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    ViewService viewService;

    @Autowired
    WorkflowService workflowService;

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer payload) throws Exception {
        logger.info("Payload to save " + payload);

        if(payload.getId() > 0 || payload.getId() < 0)
            throw new Exception();

        if(viewService.getViewById(payload.getViewId()) == null)
            throw new ViewNotFoundException("Specified view does not exist");

        if(workflowService.getWorkflowById(payload.getWorkflowId()) == null)
            throw new WorkflowNotFoundException("Specified workflow does not exist");

        if(payload.getStatus() == null)
            payload.setStatus(CustomerStatus.INACTIVE);
        return new ResponseEntity<Customer>(customerService.saveCustomer(payload), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) throws CustomerNotFoundException {
        logger.info("Customer id to return " + id);
        Customer customer = customerService.getCustomerById(id);
        if (customer == null || customer.getId() <= 0){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        return new ResponseEntity<Customer>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> removeCustomerById(@PathVariable("id") long id) throws CustomerNotFoundException {
        logger.info("Customer id to remove " + id);
        Customer customer = customerService.getCustomerById(id);
        if (customer == null || customer.getId() <= 0){
            throw new CustomerNotFoundException("Customer to delete doesn't exist");
        }
        customerService.removeCustomer(customer);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Customer has been deleted"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.PATCH)
    public ResponseEntity<Response>  updateCustomer(@Valid @RequestBody Customer payload) throws CustomerNotFoundException {
        logger.info("Payload to update " + payload);
        Customer customer = customerService.getCustomerById(payload.getId());
        if (customer == null || customer.getId() <= 0){
            throw new CustomerNotFoundException("Customer to update doesn't exist");
        }
        if(viewService.getViewById(payload.getViewId()) == null)
            throw new ViewNotFoundException("Specified view does not exist");

        if(workflowService.getWorkflowById(payload.getWorkflowId()) == null)
            throw new WorkflowNotFoundException("Specified workflow does not exist");

        if(payload.getStatus() == null)
            payload.setStatus(CustomerStatus.INACTIVE);
        customerService.saveCustomer(payload);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),"Customer has been updated"), HttpStatus.OK);
    }

    @RequestMapping(value="/customers", method=RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers(){
        logger.info("Returning all the Customers");
        return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(), HttpStatus.OK);
    }

}
