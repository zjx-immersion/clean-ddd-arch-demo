package com.zjx.apistackbase.domian.address;

import com.zjx.apistackbase.domian.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jxzhong. on 20/08/2017.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

}
