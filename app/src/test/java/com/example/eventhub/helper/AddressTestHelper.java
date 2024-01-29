package com.example.eventhub.helper;

import com.example.eventhub.contacts.Address;

public class AddressTestHelper {
    public static Address initAddress1() {
        return new Address("Nikhs", 10, "Athens", "12345", "Greece");
    }

    public static Address initAddress2() {
        return new Address("Voulis", 20, "Athens", "Greece", "12346");
    }

    public static Address initAddress3() {
        return new Address("Patision", 30, "Athens", "Greece", "12356");
    }
}
