package com.example.eventhub.util;

import android.annotation.SuppressLint;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.domain.CategoryName;
import com.example.eventhub.domain.DiscountType;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.Interest;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.domain.TicketCategory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Utility class containing various validation and mapping methods for the Event Hub application.
 * This class provides methods for validating domain objects and performing view-related validation.
 * It also includes mapping methods for converting between different data types.
 */
public class Util {

    // DOMAIN VALIDATION METHODS TESTED IN DOMAIN
    public static final String ALPHABETIC_REGEX = "^[A-Za-z]+$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String NUMERIC_REGEX = "^[0-9]+$";

    /**
     * Validates that the given value is non-null and non-empty.
     *
     * @param value     The value to validate.
     * @param fieldName The name of the field being validated.
     * @throws IllegalArgumentException If the value is null or empty.
     */
    public static void validateNonNullAndNonEmpty(String value, String fieldName) {
        if (value == null) throw new IllegalArgumentException(fieldName + " cannot be null");
        if (value.isEmpty()) throw new IllegalArgumentException(fieldName + " cannot be empty");
    }

    /**
     * Validates a string against a regular expression.
     *
     * @param value         The string to validate.
     * @param regex         The regular expression pattern.
     * @param errorMessage  The error message to throw if validation fails.
     * @throws IllegalArgumentException If the string does not match the pattern.
     */
    public static void validateRegex(String value, String regex, String errorMessage) {
        if (!value.matches(regex)) throw new IllegalArgumentException(errorMessage);
    }

    /**
     * Validates that the given string represents a non-negative integer.
     *
     * @param value     The string to validate.
     * @param fieldName The name of the field being validated.
     * @throws IllegalArgumentException If the string is not a non-negative integer.
     */
    public static void validateNonNegativeInteger(String value, String fieldName) {
        validateRegex(value, NUMERIC_REGEX, fieldName + " must contain only numbers");
        int intValue = Integer.parseInt(value);
        if (intValue < 0) throw new IllegalArgumentException(fieldName + " cannot be negative");
    }

    /**
     * Validates an integer within a specified range.
     *
     * @param value     The integer to validate.
     * @param fieldName The name of the field being validated.
     * @param min       The minimum allowed value.
     * @param max       The maximum allowed value.
     * @throws IllegalArgumentException If the integer is outside the specified range.
     */
    public static void validateIntegerInRange(Integer value, String fieldName, int min, int max) {
        validateRegex(String.valueOf(value), NUMERIC_REGEX, fieldName + " must contain only numbers");
        if (value < min) throw new IllegalArgumentException(fieldName + " cannot be less than " + min);
        if (value > max) throw new IllegalArgumentException(fieldName + " cannot be greater than " + max);
    }

    /**
     * Validates a double within a specified range.
     *
     * @param value     The double to validate.
     * @param fieldName The name of the field being validated.
     * @param min       The minimum allowed value.
     * @param max       The maximum allowed value.
     * @throws IllegalArgumentException If the double is outside the specified range.
     */
    public static void validateDoubleInRange(Double value, String fieldName, double min, double max) {
        if (value < min) throw new IllegalArgumentException(fieldName + " cannot be less than " + min);
        if (value > max) throw new IllegalArgumentException(fieldName + " cannot be greater than " + max);
    }

    /**
     * Validates that an object is not null.
     *
     * @param object    The object to validate.
     * @param fieldName The name of the field being validated.
     * @throws IllegalArgumentException If the object is null.
     */
    public static <T> void validateNotNull(T object, String fieldName) {
        if (object == null) throw new IllegalArgumentException(fieldName + " cannot be null");
    }

    /**
     * Validates the presence of an object in a list.
     *
     * @param object    The object to validate.
     * @param list      The list in which to check for the object.
     * @param fieldName The name of the field being validated.
     * @throws IllegalArgumentException If the object is not in the list.
     */
    public static <T> void validateRemovingFromList(T object, ArrayList<T> list, String fieldName) {
        validateNotNull(object, fieldName);

        if (!list.contains(object)) throw new IllegalArgumentException(fieldName + " does not exist in the list");
    }

    /**
     * Validates the absence of an object in a set.
     *
     * @param object    The object to validate.
     * @param set       The set in which to check for the absence of the object.
     * @param fieldName The name of the field being validated.
     * @throws IllegalArgumentException If the object already exists in the set.
     */
    public static <T> void validateAddingToSet(T object, Set<T> set, String fieldName) {
        validateNotNull(object, fieldName);

        if (set.contains(object)) throw new IllegalArgumentException(fieldName + " already exists in the set");
    }

    /**
     * Validates the absence of an object in a set.
     *
     * @param object    The object to validate.
     * @param set       The set in which to check for the absence of the object.
     * @param fieldName The name of the field being validated.
     * @throws IllegalArgumentException If the object does not exist in the set.
     */
    public static <T> void validateRemovingFromSet(T object, Set<T> set, String fieldName) {
        validateNotNull(object, fieldName);

        if (!set.contains(object)) throw new IllegalArgumentException(fieldName + " does not exist in the set");
    }

    // VIEW VALIDATION METHODS TESTED IN UTILTEST
    /**
     * Checks if the input string contains only alphabetic characters
     * and has a length between 4 and 25 (inclusive).
     *
     * @param info The input string to be validated.
     * @return True if the string is valid, false otherwise.
     */
    public static boolean checkAlphabeticSmallLength(String info) {
        return info.matches(ALPHABETIC_REGEX) && info.length() >= 4 && info.length() <= 25;
    }

    /**
     * Checks if the input string represents a non-negative integer.
     *
     * @param num The input string to be validated.
     * @return True if the string is a non-negative integer, false otherwise.
     */
    public static boolean checkNonNegativeInteger(String num) {
        return num.matches(NUMERIC_REGEX) && Integer.parseInt(num) >= 0;
    }

    /**
     * Checks if the input string represents a positive integer.
     *
     * @param num The input string to be validated.
     * @return True if the string is a positive integer, false otherwise.
     */
    public static boolean checkPositiveInteger(String num) {
        return num.matches(NUMERIC_REGEX) && Integer.parseInt(num) > 0;
    }

    /**
     * Checks if the input string represents a percentage (non-negative integer
     * less than or equal to 100).
     *
     * @param percentage The input string to be validated.
     * @return True if the string is a valid percentage, false otherwise.
     */
    public static boolean checkPercentage(String percentage) {
        return checkNonNegativeInteger(percentage) && Integer.parseInt(percentage) <= 100;
    }

    /**
     * Checks if the input string is a valid email address.
     *
     * @param email The input string to be validated.
     * @return True if the string is a valid email address, false otherwise.
     */
    public static boolean checkEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    /**
     * Checks if the input string meets the password length requirement (at least 8 characters).
     *
     * @param pass The input string to be validated.
     * @return True if the string meets the password length requirement, false otherwise.
     */
    public static boolean checkPassword(String pass) {
        return pass.length() >= 8;
    }

    /**
     * Checks if the components of the input Address object are valid.
     *
     * @param address The Address object to be validated.
     * @return True if the Address is valid, false otherwise.
     */
    public static boolean checkAddress(Address address) {
        return checkAlphabeticSmallLength(address.getStreet()) &&
                checkAlphabeticSmallLength(address.getCity()) &&
                checkNonNegativeInteger(String.valueOf(address.getNumber())) &&
                checkNonNegativeInteger(address.getZipCode());
    }

    /**
     * Checks if the input string adheres to a specific address format pattern.
     *
     * @param address The input string to be validated.
     * @return True if the string adheres to the specified address format, false otherwise.
     */
    public static boolean checkAddressFormat(String address) {
        String addressFormatPattern = "^[^,]+ \\d+, [^\\d]+ \\d{5}$";
        Pattern pattern = Pattern.compile(addressFormatPattern);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    /**
     * Checks if the input string is a valid date in the format "dd-MM-yyyy."
     *
     * @param date The input string to be validated.
     * @return True if the string is a valid date, false otherwise.
     */
    public static boolean checkDateFormat(String date) {
        // dd-MM-yyyy only accepted
        String dateFormatPattern = "\\d{2}-\\d{2}-\\d{4}";

        if (date.length() != 10) {
            return false;
        }

        Pattern pattern = Pattern.compile(dateFormatPattern);
        Matcher matcher = pattern.matcher(date);

        if (!matcher.matches()) {
            return false;
        }

        // Check day, month, and year ranges
        String[] parts = date.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        return isValidDayMonthYear(day, month, year);
    }

    private static boolean isValidDayMonthYear(int day, int month, int year) {
        // Assuming a simple validation here, you may need to adjust based on your requirements
        return (day >= 1 && day <= 31) &&
                (month >= 1 && month <= 12) &&
                (year >= 1000 && year <= 9999);
    }

    /**
     * Checks if the input date string is valid and represents a date in the future.
     *
     * @param date The input date string to be validated.
     * @return True if the date is valid and in the future, false otherwise.
     */
    @SuppressLint("NewApi")
    public static boolean isDateValid(String date) {
        String[] dateParts = date.split("-");
        int year = Integer.parseInt(dateParts[2]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[0]);
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime eventDate = LocalDateTime.of(year, month, day, 0, 0);
        return eventDate.isAfter(today);
    }

    /**
     * Checks if the input string is a valid time in the format "hh:mm."
     *
     * @param time The input string to be validated.
     * @return True if the string is a valid time, false otherwise.
     */
    public static boolean checkTimeFormat(String time) {
        // hh:mm only accepted
        String timeFormatPattern = "\\d{2}:\\d{2}";

        if (time.length() != 5) {
            return false;
        }

        Pattern pattern = Pattern.compile(timeFormatPattern);
        Matcher matcher = pattern.matcher(time);

        if (!matcher.matches()) {
            return false;
        }

        // Check hours and minutes range
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59;
    }

    // MAPPING METHODS TESTED IN UTILTEST
    /**
     * Maps a list of genre strings to Genre enums.
     *
     * @param genres The list of genre strings to be mapped.
     * @return ArrayList of Genre enums.
     */
    @SuppressLint("NewApi")
    public static ArrayList<Genre> mapStringsToGenres(ArrayList<String> genres) {
        return genres.stream()
                .map(Util::mapStringToGenre)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Maps a genre string to the corresponding Genre enum.
     *
     * @param genre The genre string to be mapped.
     * @return The mapped Genre enum, or null if not found.
     */
    public static Genre mapStringToGenre(String genre) {
        switch (genre) {
            case "Music":
                return Genre.MUSIC;
            case "Sports":
                return Genre.SPORTS;
            case "Art":
                return Genre.ART;
            case "Business":
                return Genre.BUSINESS;
            case "Science":
                return Genre.SCIENCE;
            case "Education":
                return Genre.EDUCATION;
            case "Politics":
                return Genre.POLITICS;
            case "Cinema":
                return Genre.CINEMA;
            case "Food":
                return Genre.FOOD;
            case "Other":
                return Genre.OTHER;
            default:
                return null;
        }
    }

    /**
     * Maps a Genre enum to an integer representation.
     *
     * @param genre The Genre enum to be mapped.
     * @return The integer representation of the Genre.
     */
    public static int mapGenreToInt(Genre genre) {
        switch (genre) {
            case ART:
                return 0;
            case CINEMA:
                return 1;
            case FOOD:
                return 2;
            case MUSIC:
                return 3;
            case SCIENCE:
                return 4;
            case SPORTS:
                return 5;
            default:
                return 0;
        }
    }

    /**
     * Maps a list of event type strings to EventType enums.
     *
     * @param types The list of event type strings to be mapped.
     * @return ArrayList of EventType enums.
     */
    @SuppressLint("NewApi")
    public static ArrayList<EventType> mapStringsToEventTypes(ArrayList<String> types) {
        return types.stream()
                .map(Util::mapStringToEventType)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Maps an event type string to the corresponding EventType enum.
     *
     * @param type The event type string to be mapped.
     * @return The mapped EventType enum, or null if not found.
     */
    public static EventType mapStringToEventType(String type) {
        switch (type.toLowerCase()) {
            case "open":
                return EventType.OPEN;
            case "closed":
                return EventType.CLOSED;
            case "free":
                return EventType.FREE;
            default:
                return null;
        }
    }

    /**
     * Maps a category name string to the corresponding CategoryName enum.
     *
     * @param categoryName The category name string to be mapped.
     * @return The mapped CategoryName enum, or CategoryName.GENERAL if not found.
     */
    public static CategoryName mapStringToCategoryName(String categoryName) {
        switch (categoryName) {
            case "VIP":
                return CategoryName.VIP;
            case "VIP_PLUS":
                return CategoryName.VIP_PLUS;
            case "Front":
                return CategoryName.FRONT;
            case "Side":
                return CategoryName.SIDE;
            case "Standing":
                return CategoryName.STANDING;
            default:
                return CategoryName.GENERAL;
        }
    }

    /**
     * Maps a discount type string to the corresponding DiscountType enum.
     *
     * @param discountType The discount type string to be mapped.
     * @return The mapped DiscountType enum, or DiscountType.GENERAL if not found.
     */
    public static DiscountType mapStringToDiscountType(String discountType) {
        switch (discountType) {
            case "Child":
                return DiscountType.CHILD;
            case "Senior":
                return DiscountType.SENIOR;
            case "Student":
                return DiscountType.STUDENT;
            case "Disability":
                return DiscountType.DISABILITY;
            default:
                return DiscountType.GENERAL;
        }
    }

    /**
     * Maps a set of interests to a list of corresponding Genre enums.
     *
     * @param interests The set of interests to be mapped.
     * @return ArrayList of Genre enums.
     */
    @SuppressLint("NewApi")
    public static ArrayList<Genre> mapInterestsToGenres(Set<Interest> interests) {
        return interests.stream()
                .map(Interest::getInterestName)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Sorts a list of events based on the specified sorting criterion.
     *
     * @param events    The list of events to be sorted.
     * @param sorting   The sorting criterion.
     * @param eventDAO  The EventDAO instance for sorting operations.
     * @return The sorted list of events.
     */
    public static ArrayList<Event> appropriateSort(ArrayList<Event> events, String sorting, EventDAO eventDAO) {
        switch (sorting) {
            case "Popularity":
                return eventDAO.sortEventsByTicketsSold(events);
            case "Capacity":
                return eventDAO.sortEventsByCapacity(events);
            case "Date (Closer)":
                return eventDAO.sortEventsByCloserDate(events);
            case "Date (Further)":
                return eventDAO.sortEventsByFurtherDate(events);
            case "Rating":
                return eventDAO.sortEventsByRating(events);
            default:
                return eventDAO.sortEventsByCapacity(events);
        }
    }


    // OTHER METHODS TESTED IN UTILTEST
    /**
     * Converts the input string to title case.
     *
     * @param input The input string to be converted.
     * @return The string in title case.
     */
    public static String convertToTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        char firstChar = Character.toUpperCase(input.charAt(0));

        String restOfString = input.substring(1).toLowerCase();

        return firstChar + restOfString;
    }

    /**
     * Converts a full address string to an Address object.
     *
     * @param fullAddress The full address string to be converted.
     * @return The Address object representing the address.
     */
    public static Address stringToAddress(String fullAddress) {
        String[] address = fullAddress.split(",");
        String street = address[0].split(" ")[0];
        String number = address[0].split(" ")[1];
        String city = address[1].split(" ")[1];
        String zip = address[1].split(" ")[2];
        String country = "Greece";
        return new Address(street, Integer.parseInt(number), city, zip, country);
    }

    /**
     * Converts date and time strings to a LocalDateTime object.
     *
     * @param date The date string in the format dd-MM-yyyy.
     * @param time The time string in the format hh:mm.
     * @return The LocalDateTime object representing the date and time.
     */
    @SuppressLint("NewApi")
    public static LocalDateTime stringToDateTime(String date, String time) {
        String[] dateParts = date.split("-");
        String[] timeParts = time.split(":");
        int year = Integer.parseInt(dateParts[2]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[0]);
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    /**
     * Checks if no filter is used based on the provided criteria list.
     *
     * @param criteriaList The list of criteria for filtering.
     * @return True if no filter is used, false otherwise.
     */
    public static boolean noFilterUsed(List<Map<String, Object>> criteriaList) {
        String eventName = (String) criteriaList.get(0).get("value");
        ArrayList<String> eventDateRange = (ArrayList<String>) criteriaList.get(1).get("value");
        ArrayList<Genre> eventGenres = (ArrayList<Genre>) criteriaList.get(2).get("value");
        ArrayList<EventType> eventTypes = (ArrayList<EventType>) criteriaList.get(3).get("value");
        String eventSorting = (String) criteriaList.get(4).get("value");

        return eventName.isEmpty() && eventGenres.size() == 9 && eventTypes.size() == 3
                && eventDateRange.get(0) != null && eventDateRange.get(1) != null
                && eventSorting.equals("Capacity");
    }

    /**
     * Initializes the ticket category count map for the available tickets of every category.
     * @param event
     * @param ticketList
     */
    public static void initTicketCategoryCountMap(Event event, ArrayList<Ticket> ticketList) {
        for (Ticket ticket : ticketList) {
            if(event.getTicketCategoryCountMap().containsKey(ticket.getTicketCategory())) {
                event.getTicketCategoryCountMap().put(ticket.getTicketCategory(), event.getTicketCategoryCountMap().get(ticket.getTicketCategory()) - 1);
            }
        }
    }
}
