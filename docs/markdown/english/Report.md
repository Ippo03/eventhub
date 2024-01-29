# EventHub
EventHub is an Android application designed to facilitate the discovery and organization of events. Through EventHub, users can organize, manage, and search for events of any kind, whether it's music festivals, theatrical performances, sports events, cultural events, or scientific conferences. Our application offers a flexible platform that serves all the needs of both organizers and fans of open events.

## Software Requirements
* Each user, before using the application and when attempting to create their account, is prompted to choose between registering as a customer or an organizer, each granting respective rights.
  
* The user can:
  1) Account Management
     * register as either a customer or an organizer and acquire the respective rights.
     * log in to their account with their login credentials.
     * log out of their account.
     * edit their account details.
  
   – Note: The "Account Management" use case, specifically "Account Creation," is a prerequisite for all other user use cases except for Event Search and for all organizer use cases. Therefore, the customer is required to register in order to perform functions 2) and 3), and the organizer for all of their functions.

* The customer has the right to:
  1) Account Management
     * register by creating their own account with various details such as (name, age, location, interests).
     * edit their account details.
  
  – Note: The "Account Management" use case is a prerequisite for all other user use cases except for Event Search. Therefore, the customer is required to register to perform functions 3) and 4).

  2) Event Search
     * automatically search to display the initial feed of events for the customer based on their interests if logged in.
     * search for events by applying various filters such as (event type, event category, date).
  
  3) Event Rating 
     * rate their overall experience at a specific event and leave a review if they have attended.
  
  4) Ticket Booking
     * purchase one or more tickets of different categories for the same event.
  
   – Note: The "Ticket Booking" and "Event Rating" use cases have an includes relationship with "Event Search" because the customer must first search in order to purchase tickets or rate an event, meaning each of these scenarios refers back to the search and the flow continues from there.

* The organizer can:
  1) Event Management
     * create events, providing information such as: title, type, status (open, closed, free), and event dates.
     * edit various event details and inform customers of any changes.
  
  2) Ticket Distribution
     * offer tickets of different categories (general, VIP) with corresponding costs.
     * add discount packages (student, senior).
     * edit event categories and discounts, as well as the number of tickets available for an event by decreasing or increasing them.
  
  - Note: The "Event Management" use case has an includes relationship with "Ticket Distribution" as during event creation, one of the fields that the organizer must complete is the available tickets, so the flow moves to ticket distribution.

* The system updates the statistics on the organizers' profiles (rating, reviews) immediately after submission by the user.

## Assumptions
* We assume that payments are made automatically when the customer selects the ticket category they want and presses purchase without any communication with a real bank.

## [Software Requirements Analysis](software-requirements.md)

## [Design and Implementation of Domain Logic](domain-design.md)
