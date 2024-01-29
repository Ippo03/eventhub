## 2.1.1 Use Case Diagram

![Use Case Diagram](../uml/requirements/use-case-diagram.png)

## 2.1.2 Use Cases
### [UC1. Account Management](1.account-management.md)
### [UC2. Event Search](2.event-search.md)
### [UC3. Event Review](3.event-review.md)
### [UC4. Ticket Booking](4.ticket-booking.md)
### [UC5. Event Management](5.event-management.md)
### [UC6. Ticket Disposal](6.ticket-disposal.md)

## 2.2 Quality Characteristics and Non-Functional Requirements
1) Usability: The application will be easy to use for users, offering a friendly graphical interface and readable design. No technical knowledge is required from the user.

2) Security: The application will ensure the security of users' personal data and provide authentication mechanisms. Additionally, it will inform users how their data is used and who has access to it.

3) Performance: The application will operate efficiently without delays, offering fast access to information and functions. The system should support up to 100 concurrent users. The database response to each request should not exceed 3 seconds.

4) Integrity: The application will be designed to withstand unexpected conditions, such as sudden power outages, network interruptions, or unwanted data inputs from users. Each action maintains the system in a consistent state, whether it completes or not at all.

5) Testability: The application will include testing procedures to ensure that it functions correctly. Additionally, multiple JUnit tests will be used to check the proper operation of code components.

6) Maintainability: The application will have well-documented code for easy readability and maintenance. Furthermore, the code will be organized and follow best practices.

## 2.3.1 Domain Model

![Domain Model](../uml/requirements/domain-model.png)
