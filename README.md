# My Local Restaurant Finder

Local Restaurant Finder
Project Description: This application will serve as a platform for users to discover local restaurants based on their current location. It will provide details about the restaurants such as name, description, ratings, and reviews.

Features and Functionalities:
User Authentication: Implement user registration and login functionality using Firebase Authentication or any other suitable method.
Restaurant Listing: Display a list of restaurants based on the user's current location. This data is fetched from a web service using Retrofit or any other suitable method.
Restaurant Details: When a user selects a restaurant from the list, detailed information about the restaurant is displayed. This can include name, description, ratings, reviews, and images.
Favorites: Allows users to mark restaurants as favorites. These favorites should be stored in a local database using Room (SQLite).
Offline Support: The app should work offline and display the favorite restaurants even when there is no internet connection.
Configuration Changes: The app handles configuration changes like screen rotation, language change, etc., without losing any data or state.

Technical Requirements:
Local Database: Room or SQLite for local data storage is used.
Web Services: Retrofit or a similar library for network calls is used.
User Authentication: Firebase Authentication or a similar service is used.
MVVM Architecture: Model-View-ViewModel architecture for structuring the app UI.
Data Binding: Data Binding or View Binding is used to bind UI components in layouts to data sources.
Dependency Injection: Dagger2 or Hilt is used for dependency injection.
Unit Testing: Unit tests for the business logic were written.
UI(Integration) Testing: UI tests were written using Espresso.

This aim of this project is to test the Android Engineers' knowledge and skills in key areas of Android development.
