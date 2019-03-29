package io.apitest.example.repository;

import io.apitest.example.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by prasantabiswas on 27/06/18.
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
