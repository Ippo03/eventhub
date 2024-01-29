package com.example.eventhub.helper;

import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.Interest;

import java.util.HashSet;
import java.util.Set;

public class InterestTestHelper {
    public static Interest initCustomInterest(Genre interestName) {
        return new Interest(interestName);
    }

    public static Set<Interest> initSetOfTwoInterests(Genre interestName1, Genre interestName2) {
        Interest interest1 = initCustomInterest(interestName1);
        Interest interest2 = initCustomInterest(interestName2);
        Set<Interest> interests = new HashSet<>();
        interests.add(interest1);
        interests.add(interest2);
        return interests;
    }
}