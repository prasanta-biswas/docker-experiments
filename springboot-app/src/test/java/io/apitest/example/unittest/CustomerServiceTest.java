package io.apitest.example.unittest;

import io.apitest.example.enums.CustomerStatus;
import io.apitest.example.model.Customer;
import io.apitest.example.repository.CustomerRepository;
import io.apitest.example.service.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by prasantabiswas on 29/06/18.
 */
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCustomer(){
        List<Customer> customerList = new ArrayList<Customer>();
        customerList.add(new Customer(5,"Customer Name 5","Kolkata",true, CustomerStatus.ACTIVE,1,1));
        customerList.add(new Customer(6,"Customer Name 6","Bangalore",false, CustomerStatus.INACTIVE,2,2));
        customerList.add(new Customer(7,"Customer Name 7","Hyderabad",true, CustomerStatus.BLOCKED,3,3));
        when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> result = customerService.getAllCustomer();
        assertEquals(3, result.size());
    }

    @Test
    public void testGetCustomerById(){
        Customer customer = new Customer(8,"Customer Name 8","Pune",true, CustomerStatus.ACTIVE,3,4);
        when(customerRepository.findOne(1L)).thenReturn(customer);
        Customer result = customerService.getCustomerById(1);
        assertEquals(8, result.getId());
        assertEquals("Customer Name 8", result.getName());
        assertEquals("Pune", result.getAddress());
        assertEquals(true, result.isOnboarded());
        assertEquals(CustomerStatus.ACTIVE, result.getStatus());
        assertEquals(3, result.getViewId());
        assertEquals(4, result.getWorkflowId());
    }

    @Test
    public void saveCustomer(){
        Customer customer = new Customer(9,"Customer Name 9","Ahmedabad",false, CustomerStatus.INACTIVE,2,4);
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer result = customerService.saveCustomer(customer);
        assertEquals(9, result.getId());
        assertEquals("Customer Name 9", result.getName());
        assertEquals("Ahmedabad", result.getAddress());
        assertEquals(false, result.isOnboarded());
        assertEquals(CustomerStatus.INACTIVE, result.getStatus());
        assertEquals(2, result.getViewId());
        assertEquals(4, result.getWorkflowId());
    }

    @Test
    public void removeCustomer(){
        Customer customer = new Customer(9,"Customer Name 9","Ahmedabad",false, CustomerStatus.BLOCKED,2,4);
        customerService.removeCustomer(customer);
        verify(customerRepository, times(1)).delete(customer);
    }
}
