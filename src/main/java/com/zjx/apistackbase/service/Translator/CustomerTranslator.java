package com.zjx.apistackbase.service.Translator;

import com.zjx.apistackbase.dto.AddressDto;
import com.zjx.apistackbase.dto.CustomerDto;
import com.zjx.apistackbase.domian.address.model.Address;
import com.zjx.apistackbase.domian.customer.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxzhong. on 20/08/2017.
 */
public class CustomerTranslator {
    public static CustomerDto translateCustomerModelToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        return customerDto;
    }

    public static List<AddressDto> translateAddressesModelToDto(List<Address> addresses){
        List<AddressDto> addressDtos = new ArrayList<>();
        for (Address address : addresses) {
            AddressDto addressDto = new AddressDto();
            addressDto.setId(address.getId());
            addressDto.setCity(address.getCity());
            addressDtos.add(addressDto);
        }
        return addressDtos;
    }

    public static AddressDto translateAddressModelToDto(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setCity(address.getCity());
        return addressDto;
    }
}
