package com.example.eventhub.util;

import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.Initializer;
import com.example.eventhub.domain.CategoryName;
import com.example.eventhub.domain.DiscountType;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.helper.AddressTestHelper;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.util.Util;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class for the {@link Util} class.
 */
public class UtilTest {

    /**
     * Test case for {@link Util#checkAlphabeticSmallLength(String)}.
     * Checks if the input string contains alphabetic characters and has a small length.
     */
    @Test
    public void checkAlphabeticSmallLength() {
        Assertions.assertTrue(!Util.checkAlphabeticSmallLength("alphabetic123"));
        Assertions.assertTrue(!Util.checkAlphabeticSmallLength("ab"));
        Assertions.assertTrue(!Util.checkAlphabeticSmallLength("alphabeticalphabeticalphabetic"));
        Assertions.assertTrue(Util.checkAlphabeticSmallLength("alphabetic"));
    }

    /**
     * Test case for {@link Util#checkNonNegativeInteger(String)}.
     * Checks if the input string represents a non-negative integer.
     */
    @Test
    public void checkNonNegativeInteger() {
        Assertions.assertTrue(!Util.checkNonNegativeInteger("-1"));
        Assertions.assertTrue(Util.checkNonNegativeInteger("0"));
    }

    /**
     * Test case for {@link Util#checkPositiveInteger(String)}.
     * Checks if the input string represents a positive integer.
     */
    @Test
    public void checkPositiveInteger() {
        Assertions.assertTrue(!Util.checkPositiveInteger("0"));
        Assertions.assertTrue(Util.checkPositiveInteger("1"));
    }

    /**
     * Test case for {@link Util#checkPercentage(String)}.
     * Checks if the input string represents a valid percentage.
     */
    @Test
    public void checkPercentage() {
        Assertions.assertTrue(!Util.checkPercentage("-1"));
        Assertions.assertTrue(!Util.checkPercentage("101"));
        Assertions.assertTrue(Util.checkPercentage("50"));
    }

    /**
     * Test case for {@link Util#checkPasswordWithInvalidLength(String)}.
     * Checks if the password has a valid length.
     */
    @Test
    public void checkPasswordWithInvalidLength() {
        Assertions.assertTrue(!Util.checkPassword("1234567"));
    }

    /**
     * Test case for {@link Util#checkEmail(String)}.
     * Checks if the input string is a valid email address.
     */
    @Test
    public void checkEmail() {
        Assertions.assertTrue(!Util.checkEmail("@example.com"));
        Assertions.assertTrue(!Util.checkEmail("example.com"));
        Assertions.assertTrue(Util.checkEmail("example@example.com"));
    }

    /**
     * Test case for {@link Util#checkPassword(String)}.
     * Checks if the password is valid.
     */
    @Test
    public void checkPassword() {
        Assertions.assertTrue(Util.checkPassword("12345678"));
    }

    /**
     * Test case for {@link Util#checkAddress(Address)} and related methods.
     * Checks if the address and its format are valid.
     */
    @Test
    public void checkAddress() {
        Assertions.assertTrue(Util.checkAddress(AddressTestHelper.initAddress1()));
        Assertions.assertTrue(!Util.checkAddressFormat("17, Athens 12345"));
        Assertions.assertTrue(!Util.checkAddressFormat("Ermou, Athens 12345"));
        Assertions.assertTrue(!Util.checkAddressFormat("Ermou 17, 12345"));
        Assertions.assertTrue(!Util.checkAddressFormat("Ermou 17, Athens"));
        Assertions.assertTrue(!Util.checkAddressFormat("Ermou 17, Athens 1234"));
        Assertions.assertTrue(!Util.checkAddressFormat("Ermou17,Athens12345"));
        Assertions.assertTrue(!Util.checkAddressFormat("Ermou 17 Athens 12345"));
        Assertions.assertTrue(Util.checkAddressFormat("Ermou 17, Athens 12345"));
    }

    /**
     * Test case for {@link Util#checkDateFormat(String)}.
     * Checks if the input string represents a valid date format.
     */
    @Test
    public void checkDateFormat() {
        Assertions.assertTrue(!Util.checkDateFormat("1-10-2020"));
        Assertions.assertTrue(!Util.checkDateFormat("01-1-2020"));
        Assertions.assertTrue(!Util.checkDateFormat("01-10-20"));
        Assertions.assertTrue(!Util.checkDateFormat("10/10/2020"));
        Assertions.assertTrue(!Util.checkDateFormat("32-10-2020"));
        Assertions.assertTrue(!Util.checkDateFormat("10-13-2020"));
        Assertions.assertTrue(!Util.checkDateFormat("10-10-20202"));
        Assertions.assertTrue(Util.checkDateFormat("10-10-2020"));
    }

    /**
     * Test case for {@link Util#checkTimeFormat(String)}.
     * Checks if the input string represents a valid time format.
     */
    @Test
    public void checkTimeFormat() {
        Assertions.assertTrue(!Util.checkTimeFormat("1:10"));
        Assertions.assertTrue(!Util.checkTimeFormat("01:1"));
        Assertions.assertTrue(!Util.checkTimeFormat("10-10"));
        Assertions.assertTrue(!Util.checkTimeFormat("25:10"));
        Assertions.assertTrue(!Util.checkTimeFormat("10:60"));
        Assertions.assertTrue(Util.checkTimeFormat("10:10"));
    }

    /**
     * Test case for {@link Util#mapStringToGenre(String)}.
     * Maps string representations of genres to corresponding {@link Genre} enum values.
     */
    @Test
    public void mapStringToGenre() {
        // Test cases for mapping genre strings to Genre enum values
        Assertions.assertEquals(Genre.ART, Util.mapStringToGenre("Art"));
        Assertions.assertEquals(Genre.CINEMA, Util.mapStringToGenre("Cinema"));
        Assertions.assertEquals(Genre.SCIENCE, Util.mapStringToGenre("Science"));
        Assertions.assertEquals(Genre.EDUCATION, Util.mapStringToGenre("Education"));
        Assertions.assertEquals(Genre.POLITICS, Util.mapStringToGenre("Politics"));
        Assertions.assertEquals(Genre.MUSIC, Util.mapStringToGenre("Music"));
        Assertions.assertEquals(Genre.SPORTS, Util.mapStringToGenre("Sports"));
        Assertions.assertEquals(Genre.BUSINESS, Util.mapStringToGenre("Business"));
        Assertions.assertEquals(Genre.FOOD, Util.mapStringToGenre("Food"));
        Assertions.assertEquals(Genre.OTHER, Util.mapStringToGenre("Other"));
        Assertions.assertEquals(null, Util.mapStringToGenre("Invalid"));
    }

    /**
     * Test case for {@link Util#mapStringsToGenres(List)}.
     * Maps a list of string representations of genres to a list of corresponding {@link Genre} enum values.
     */
    @Test
    public void mapStringsToGenres() {
        // Test cases for mapping a list of genre strings to a list of Genre enum values
        ArrayList<String> genres = new ArrayList<>();

        genres.add("Invalid");
        Assertions.assertEquals(0, Util.mapStringsToGenres(genres).size());

        genres.add("Art");
        Assertions.assertEquals(1, Util.mapStringsToGenres(genres).size());

        genres.add("Cinema");
        Assertions.assertEquals(2, Util.mapStringsToGenres(genres).size());
    }

    /**
     * Test case for {@link Util#mapGenreToInt(Genre)}.
     * Maps Genre enum values to corresponding integer values.
     */
    @Test
    public void mapGenreToInt() {
        // Test cases for mapping Genre enum values to integer values
        Assertions.assertEquals(0, Util.mapGenreToInt(Genre.ART));
        Assertions.assertEquals(1, Util.mapGenreToInt(Genre.CINEMA));
        Assertions.assertEquals(2, Util.mapGenreToInt(Genre.FOOD));
        Assertions.assertEquals(3, Util.mapGenreToInt(Genre.MUSIC));
        Assertions.assertEquals(4, Util.mapGenreToInt(Genre.SCIENCE));
        Assertions.assertEquals(5, Util.mapGenreToInt(Genre.SPORTS));
        Assertions.assertEquals(0, Util.mapGenreToInt(Genre.POLITICS));
    }

    /**
     * Test case for {@link Util#mapStringToEventType(String)}.
     * Maps string representations of event types to corresponding {@link EventType} enum values.
     */
    @Test
    public void mapStringToEventType() {
        // Test cases for mapping string representations of event types to EventType enum values
        Assertions.assertEquals(EventType.OPEN, Util.mapStringToEventType("open"));
        Assertions.assertEquals(EventType.CLOSED, Util.mapStringToEventType("closed"));
        Assertions.assertEquals(EventType.FREE, Util.mapStringToEventType("free"));
        Assertions.assertEquals(null, Util.mapStringToEventType("Invalid"));
    }

    /**
     * Test case for {@link Util#mapStringsToEventTypes(List)}.
     * Maps a list of string representations of event types to a list of corresponding {@link EventType} enum values.
     */
    @Test
    public void mapStringsToEventTypes() {
        // Test cases for mapping a list of event type strings to a list of EventType enum values
        ArrayList<String> eventTypes = new ArrayList<>();

        eventTypes.add("Invalid");
        Assertions.assertEquals(0, Util.mapStringsToEventTypes(eventTypes).size());

        eventTypes.add("open");
        Assertions.assertEquals(1, Util.mapStringsToEventTypes(eventTypes).size());

        eventTypes.add("closed");
        Assertions.assertEquals(2, Util.mapStringsToEventTypes(eventTypes).size());
    }

    /**
     * Test case for {@link Util#mapStringToCategoryName(String)}.
     * Maps string representations of category names to corresponding {@link CategoryName} enum values.
     */
    @Test
    public void mapStringToCategoryName() {
        // Test cases for mapping string representations of category names to CategoryName enum values
        Assertions.assertEquals(CategoryName.VIP, Util.mapStringToCategoryName("VIP"));
        Assertions.assertEquals(CategoryName.VIP_PLUS, Util.mapStringToCategoryName("VIP_PLUS"));
        Assertions.assertEquals(CategoryName.STANDING, Util.mapStringToCategoryName("Standing"));
        Assertions.assertEquals(CategoryName.FRONT, Util.mapStringToCategoryName("Front"));
        Assertions.assertEquals(CategoryName.SIDE, Util.mapStringToCategoryName("Side"));
        Assertions.assertEquals(CategoryName.GENERAL, Util.mapStringToCategoryName("General"));
        Assertions.assertEquals(CategoryName.GENERAL, Util.mapStringToCategoryName("Invalid"));
    }

    /**
     * Test case for {@link Util#mapStringToDiscountType(String)}.
     * Maps string representations of discount types to corresponding {@link DiscountType} enum values.
     */
    @Test
    public void mapStringToDiscountType() {
        // Test cases for mapping string representations of discount types to DiscountType enum values
        Assertions.assertEquals(DiscountType.GENERAL, Util.mapStringToDiscountType("General"));
        Assertions.assertEquals(DiscountType.STUDENT, Util.mapStringToDiscountType("Student"));
        Assertions.assertEquals(DiscountType.SENIOR, Util.mapStringToDiscountType("Senior"));
        Assertions.assertEquals(DiscountType.CHILD, Util.mapStringToDiscountType("Child"));
        Assertions.assertEquals(DiscountType.DISABILITY, Util.mapStringToDiscountType("Disability"));
        Assertions.assertEquals(DiscountType.GENERAL, Util.mapStringToDiscountType("Invalid"));
    }

    /**
     * Test case for {@link Util#appropriateSort(ArrayList, String, EventDAO)}.
     * Tests the appropriate sorting of events based on different criteria.
     */
    @Test
    public void appropriateSort() {
        // Initializing data and active events
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        EventDAO eventDAO = new EventDAOMemory();
        ArrayList<Event> activeEvents = eventDAO.findAllActive();

        // Testing sorting by popularity
        ArrayList<Event> popularityEvents = Util.appropriateSort(activeEvents, "Popularity", eventDAO);
        Assertions.assertEquals(11, popularityEvents.size());

        // Testing sorting by capacity
        ArrayList<Event> capacityEvents = Util.appropriateSort(activeEvents, "Capacity", eventDAO);
        Assertions.assertEquals(11, capacityEvents.size());

        // Testing sorting by date (closer)
        ArrayList<Event> dateCloser = Util.appropriateSort(activeEvents, "Date (Closer)", eventDAO);
        Assertions.assertEquals(11, dateCloser.size());

        // Testing sorting by date (further)
        ArrayList<Event> dateFurther = Util.appropriateSort(activeEvents, "Date (Further)", eventDAO);
        Assertions.assertEquals(11, dateFurther.size());

        // Testing sorting by rating
        ArrayList<Event> rating = Util.appropriateSort(activeEvents, "Rating", eventDAO);
        Assertions.assertEquals(11, rating.size());

        // Testing sorting with an invalid criteria
        ArrayList<Event> invalid = Util.appropriateSort(activeEvents, "Invalid", eventDAO);
        Assertions.assertEquals(11, invalid.size());
    }

    /**
     * Test case for {@link Util#convertToTitleCase(String)}.
     * Tests the conversion of strings to title case.
     */
    @Test
    public void convertToTitleCase() {
        Assertions.assertEquals("", Util.convertToTitleCase(""));
        Assertions.assertEquals("Test", Util.convertToTitleCase("test"));
        Assertions.assertEquals("Test", Util.convertToTitleCase("TEST"));
        Assertions.assertEquals("Test", Util.convertToTitleCase("tEsT"));
    }

    /**
     * Test case for {@link Util#stringToAddress(String)}.
     * Tests the conversion of a string to an Address object.
     */
    @Test
    public void stringToAddress() {
        Assertions.assertEquals(AddressTestHelper.initAddress1(), Util.stringToAddress("Nikhs 10, Athens 12345"));
    }

    /**
     * Test case for {@link Util#stringToDateTime(String, String)}.
     * Tests the conversion of strings to a DateTime object.
     */
    @Test
    public void stringToDateTime() {
        Assertions.assertEquals("2020-10-10T10:10", Util.stringToDateTime("10-10-2020", "10:10").toString());
    }

    /**
     * Test case for {@link Util#noFilterUsed(List)}.
     * Tests if no filters are applied based on the given criteria list.
     */
    @Test
    public void noFilterUsed() {
        // Creating a list of criteria with an empty title and date range
        List<Map<String, Object>> criteriaList = new ArrayList<>();
        Map<String, Object> criteriaMapTitleEmpty = new HashMap<>();
        criteriaMapTitleEmpty.put("value", "");
        criteriaList.add(criteriaMapTitleEmpty);

        HashMap<String, Object> criteriaMapDate = new HashMap<>();
        ArrayList<String> dateList = new ArrayList<>();
        dateList.add("12-10-2024");
        dateList.add("24-12-2024");
        criteriaMapDate.put("value", dateList);
        criteriaList.add(criteriaMapDate);

        // Adding criteria for genre, event type, and sorting by capacity
        Map<String, Object> criteriaMapGenre = new HashMap<>();
        ArrayList<Genre> genreList = new ArrayList<>();
        genreList.add(Genre.ART);
        genreList.add(Genre.CINEMA);
        genreList.add(Genre.SCIENCE);
        genreList.add(Genre.EDUCATION);
        genreList.add(Genre.POLITICS);
        genreList.add(Genre.MUSIC);
        genreList.add(Genre.SPORTS);
        genreList.add(Genre.BUSINESS);
        genreList.add(Genre.FOOD);
        criteriaMapGenre.put("value", genreList);
        criteriaList.add(criteriaMapGenre);

        Map<String, Object> criteriaMapEventType = new HashMap<>();
        ArrayList<EventType> eventTypeList = new ArrayList<>();
        eventTypeList.add(EventType.OPEN);
        eventTypeList.add(EventType.CLOSED);
        eventTypeList.add(EventType.FREE);
        criteriaMapEventType.put("value", eventTypeList);
        criteriaList.add(criteriaMapEventType);

        Map<String, Object> criteriaMapCapacity = new HashMap<>();
        criteriaMapCapacity.put("value", "Capacity");
        criteriaList.add(criteriaMapCapacity);

        // Testing if no filters are used
        Assertions.assertTrue(Util.noFilterUsed(criteriaList));

        // Modifying criteria by adding a title
        criteriaMapTitleEmpty.put("value", "Test");

        // Testing if filters are used
        Assertions.assertTrue(!Util.noFilterUsed(criteriaList));
    }
}
