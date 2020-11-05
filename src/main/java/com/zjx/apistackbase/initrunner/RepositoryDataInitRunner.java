package com.zjx.apistackbase.initrunner;

import com.zjx.apistackbase.domian.customer.CustomerRepository;
import com.zjx.apistackbase.domian.customer.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RepositoryDataInitRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryDataInitRunner.class);

    @Autowired
    private CustomerRepository repository;


    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        insertCustomers();

        repository.findAll().forEach((customer) -> {
            logger.info("{}", customer);
        });
    }

    protected void insertCustomers() {
        repository.save(new Customer("Jianxin", "Zhong"));
        repository.save(new Customer("DianDian", "Zhong"));
        repository.save(new Customer("Tomas", "Vigas"));
    }
}