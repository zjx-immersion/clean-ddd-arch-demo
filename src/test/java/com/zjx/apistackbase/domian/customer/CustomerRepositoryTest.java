package com.zjx.apistackbase.domian.customer;

import com.zjx.apistackbase.domian.customer.model.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @AfterEach
    public void tearDown() throws Exception {
        entityManager.clear();
    }

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(entityManager).isNotNull();
        assertThat(customerRepository).isNotNull();
    }

    @Test
    public void findByLastNameContaining() {
        //giveb
        entityManager.persist(new Customer("jason", "Zhong"));
        entityManager.persist(new Customer("Jianxin", "Zhong"));
        entityManager.persist(new Customer("jason", "Zhang"));

        //when
        List<Customer> customers = customerRepository
                .findByLastNameContaining("Zhong", PageRequest.of(0, 5)).getContent();

        //then
        assertThat(customers.size()).isEqualTo(2);
        assertThat(customers.get(0).getLastName()).isEqualTo("Zhong");
        assertThat(customers.get(1).getLastName()).isEqualTo("Zhong");
    }

    @Test
    public void findAll() {
        //given
        entityManager.persist(new Customer("jason", "Zhong-11"));
        entityManager.persist(new Customer("Jijia", "Zhang"));

        //when
        List<Customer> customers = customerRepository.findAll(PageRequest.of(0, 5)).getContent();

        //then
        assertThat(customers.size()).isEqualTo(2);
        assertThat(customers.get(0).getFirstName()).isEqualTo("jason");
        assertThat(customers.get(0).getLastName()).isEqualTo("Zhong-11");

    }


    @Test
    public void deleteCustomerByEntity() {
        //given
        entityManager.persist(new Customer("jason", "Zhong-11"));
        Customer customer = entityManager.persistAndFlush(new Customer("jason-22", "Zhong-22"));

        //when
        customerRepository.delete(customer);

        //then
        assertThat(customerRepository.findAll().size()).isEqualTo(1);
    }


    @Test
    public void deleteCustomerById() {
        //given
        entityManager.clear();
        entityManager.persist(new Customer("jason", "Zhong-11"));

        //when
        System.out.println(customerRepository.findAll().size());
        Long id = Long.valueOf(entityManager.persistAndGetId(new Customer("jason-22", "Zhong-22")).toString());
        int isDeleted = customerRepository.deleteCustomerById(id);

        //then
        assertThat(isDeleted).isEqualTo(1);
        assertThat(customerRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void changeLastNameById() {

        //given
        entityManager.persist(new Customer("jason", "Zhong-11"));

        //when
        Long id = Long.valueOf(entityManager.persistAndGetId(new Customer("Jijia", "Zhang")).toString());
        int isUpdated = customerRepository.changeLastNameById(id, "Liu");

        //then
        assertThat(isUpdated).isEqualTo(1);
        Assertions.assertThat(customerRepository.findById(id).get().getLastName()).isEqualTo("Liu");


    }

    @Test
    public void failed_change_last_name_b_not_exist_id_when_call_change() {

        //given
        //when
        int isUpdated = customerRepository.changeLastNameById(100L, "Liu");

        //then
        assertThat(isUpdated).isEqualTo(0);

    }


}