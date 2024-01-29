package com.example.eventhub.contacts;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.util.BasicEqualTester;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Test class for the {@link Address} class.
 */
public class AddressTest {

    /**
     * Tests the copy constructor of the {@link Address} class.
     */
    @Test
    public void copyAddress() {
        Address address = new Address("Patision", 76, "Athens", "11111", "Greece");
        Address address2 = new Address(address);
        Assertions.assertEquals(address, address2);
    }

    /**
     * Tests the case where all fields of the {@link Address} instance are null.
     */
    @Test
    public void AddressAllNull() {
        Address address = new Address(null, null, null, null, null);
        int hashCode = address.hashCode();
        Assertions.assertEquals(0, hashCode);
    }

    /**
     * Tests the {@code equals} and {@code hashCode} methods of the {@link Address} class.
     */
    @Test
    public void testEqualsAndHashCode() {
        BasicEqualTester<Address> equalsTester = new BasicEqualTester<Address>();
        Address address = new Address();
        equalsTester.setObjectUnderTest(address);

        equalsTester.otherObjectIsNull();

        equalsTester.otherObjectIsOfDifferentType(new Object());

        Address address2 = new Address();
        equalsTester.bothObjectsHaveNoState(address2);

        address.setStreet("Patision");
        equalsTester.otherObjectsHasNoState(address2);

        equalsTester.sameReferences(address);

        address2.setStreet("Patision");
        equalsTester.bothObjectsHaveSameState(address2);

        address.setNumber(76);
        equalsTester.objectsHaveDifferentState(address2);

        address2.setNumber(87);
        equalsTester.objectsHaveDifferentState(address2);

        address2.setNumber(76);
        equalsTester.bothObjectsHaveSameState(address2);

        address.setCity("Athens");
        equalsTester.objectsHaveDifferentState(address2);

        address2.setCity("Lamia");
        equalsTester.objectsHaveDifferentState(address2);

        address2.setCity("Athens");
        equalsTester.bothObjectsHaveSameState(address2);

        address2.setCountry("France");
        equalsTester.objectsHaveDifferentState(address2);

        address2.setCountry("Greece");
        equalsTester.bothObjectsHaveSameState(address2);

        address.setZipCode("111");
        equalsTester.objectsHaveDifferentState(address2);

        address2.setZipCode("222");
        equalsTester.objectsHaveDifferentState(address2);

        address2.setZipCode("111");
        equalsTester.bothObjectsHaveSameState(address2);
    }
}