# EventHub
EventHub is an Android application designed to facilitate the discovery and organization of events. Through EventHub, users can organize, manage, and search for events of any kind, whether it's music festivals, theatrical performances, sports events, cultural events, or scientific conferences. Our application offers a flexible platform that serves all the needs of both organizers and fans of open events.

## Technical Overview

### Application Architecture:

The application adopts the View, Presenter, and View Model architecture for clear separation of concerns and maintainability:

- **View:** User interface components that directly interact with users, handling input and output.

- **Presenter:** Mediates between the View and business logic, processing user input and updating the View Model.

- **View Model:** Manages UI-related data and state, abstracting UI logic and ensuring a reactive interface.

### Testing:

The domain logic and presenters undergo rigorous testing using JUnit. Testing of the presenter is conducted with Stubs to ensure reliability and robustness. Comprehensive test coverage includes various scenarios and edge cases, ensuring the correctness of implemented functionality.

### Software Requirements Analysis:

The application features a thorough analysis of software requirements:

- **Use Case Model:** Describes user-system interactions and functional requirements.
- **Activity Diagrams:** Visualize system activity flow, illustrating user interactions and system responses.
- **Sequence Diagrams:** Detail interactions between system objects, providing insight into system behavior.
- **Domain Model:** Represents conceptual classes and relationships within the problem domain.
- **Class Diagram:** Illustrates classes and relationships in domain logic, aiding in understanding system architecture and design.

Reference :
- [Further analysis of the requirements in english](/docs/markdown/english/Report.md).
- [Further analysis of the requirements in greek](/docs/markdown/greek/Report-greek.md).

## Features

- **User Authentication:** Register and log in functionality.

- **Account Management:** Edit user account details.

- **Event Creation:** Create events with different categories and discounts.

- **Event Details Editing:** Edit event details.

- **Event Searching:** Search for events with various filters.

- **Event Reviewing:** Review events and provide feedback.

- **Ticket Booking:** Functionality for customers to book tickets for an event.

## Installation Steps:

### 1. Clone the Repository:

```markdown
Clone the repository using Git:

https://github.com/Ippo03/eventhub.git
```
### 2. Open Android Studio and Import the Project:
Launch Android Studio, select "Open an existing Android Studio project" and choose the project directory.

### 3. Connect or Create an Emulator:
Connect a physical Android device or create a virtual device using AVD Manager.

### 4. Run the app
Click the green "play" button in the Android Studio toolbar.
- Select the target device and click "OK" to build and deploy the app.

### 5. You are all set up. Enjoy!

