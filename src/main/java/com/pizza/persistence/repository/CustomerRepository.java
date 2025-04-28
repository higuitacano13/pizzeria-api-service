package com.pizza.persistence.repository;

import com.pizza.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, String> {

    @Query(value = "Select c From CustomerEntity c WHERE c.phoneNumber = :phone")
    CustomerEntity findByPhone(@Param("phone") String phone);

}
