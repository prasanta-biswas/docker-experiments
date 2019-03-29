package io.apitest.example.interfaces;

import io.apitest.example.model.Customer;

import java.util.List;

/**
 * Created by prasantabiswas on 27/06/18.
 */
public interface CustomerService {
    public List<Customer> getAllCustomer();
    public Customer getCustomerById(long id);
    public Customer saveCustomer(Customer customer);
    public void removeCustomer(Customer customer);
}
