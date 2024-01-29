package com.example.eventhub.helper;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.Interest;

import java.util.HashSet;
import java.util.Set;

public class CustomerTestHelper {

    public static Customer initCustomerWithOneInterest() {
        Address address = com.example.eventhub.helper.AddressTestHelper.initAddress1();
        Set<Interest> interests = new HashSet<>();
        interests.add(com.example.eventhub.helper.InterestTestHelper.initCustomInterest(Genre.EDUCATION));

        Customer customer = new Customer(1,"John", "Doe", "email@example.com", 20, "Male", address, "password", interests);
        return customer;
    }

    public static Customer initCustomerWithTwoInterests() {
        Address address = AddressTestHelper.initAddress1();
        Set<Interest> interests = InterestTestHelper.initSetOfTwoInterests(Genre.MUSIC, Genre.EDUCATION);
        Customer customer = new Customer(1,"John", "Doe", "email@example.com", 20, "Male", address, "password", interests);
        return customer;
    }
}