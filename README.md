[README.md](https://github.com/user-attachments/files/33000902/README.md)
# Car-Marketplace
CarMarketplace is a JavaFX-based vehicle marketplace application featuring customer and dealership accounts, vehicle listing management, multi-criteria search, sorting, and persistent file storage. It demonstrates OOP, custom hash tables, dynamic lists, arrays, and Merge Sort through a practical marketplace system.
# AutoMarket -- Online Car Dealership & Vehicle Marketplace

AutoMarket is a JavaFX-based vehicle marketplace application designed
for buying, selling, browsing, and managing vehicle listings. The
project combines **Object-Oriented Programming (OOP)**, **Data
Structures & Algorithms**, **file handling**, and a graphical user
interface into a practical car marketplace system.

## Features

-   User account registration and login
-   Individual Customer and Dealership account types
-   Add, update, and delete vehicle listings
-   Browse available vehicles
-   Search vehicles by:
    -   Make
    -   Model
    -   Price range
    -   Manufacturing year
-   Sort vehicles by price or year
-   Ascending and descending sorting
-   Detailed vehicle information
-   Seller association for each listing
-   Input validation for vehicle information
-   Persistent user and vehicle data using text files
-   JavaFX graphical user interface
-   Role-based dashboard and listing management

## Technologies Used

-   **Java**
-   **JavaFX**
-   **Object-Oriented Programming**
-   **Data Structures & Algorithms**
-   **File Handling**

## Data Structures & Algorithms

The project implements custom data structures and algorithms rather than
relying entirely on Java's built-in collections.

### Hash Tables

Custom hash tables are used to store and efficiently locate users and
vehicle listings using unique identifiers.

-   `UserHashTable` -- stores users using User ID
-   `VehicleHashTable` -- stores vehicles using Listing ID
-   Collision handling is implemented using linked nodes
-   Average lookup complexity: **O(1)**
-   Worst-case lookup complexity: **O(n)**

### Dynamic Vehicle List

`VehicleList` maintains an ordered collection of vehicles using a
dynamic array.

-   Initial capacity: 10
-   Capacity doubles when the array becomes full
-   Supports adding, retrieving, removing, and managing vehicles
-   Provides flexible storage as the number of listings grows

### Merge Sort

Merge Sort is used to sort vehicle listings by:

-   Price
-   Manufacturing year

Sorting complexity: **O(n log n)**

### Searching

Vehicle searches use array traversal and can combine multiple filters
such as make, model, price range, and year.

Search complexity: **O(n)**

## Object-Oriented Programming

The application demonstrates several core OOP concepts:

-   **Encapsulation** -- data and behavior are organized inside classes
-   **Inheritance** -- `Customer` and `Dealership` extend the `User`
    class
-   **Polymorphism** -- user types provide different implementations
    where required
-   **Abstraction** -- responsibilities are separated across dedicated
    classes

## Main Components

The project separates responsibilities across different classes,
including:

  Component              Responsibility
  ---------------------- ------------------------------------
  `User`                 Base user information and behavior
  `Customer`             Individual customer account
  `Dealership`           Dealership account
  `Vehicle`              Vehicle listing information
  `MarketplaceManager`   Main marketplace/application logic
  `UserHashTable`        User lookup and storage
  `VehicleHashTable`     Vehicle lookup and storage
  `VehicleList`          Dynamic vehicle collection
  `MergeSort`            Sorting vehicle listings
  `FileManager`          Saving and loading persistent data
  JavaFX page classes    Graphical user interface

## Data Persistence

The application uses text files to preserve data between program runs.

-   `users.txt` -- stores user information
-   `vehicles.txt` -- stores vehicle listing information

When the application starts, previously saved records can be loaded back
into the custom data structures.

## Application Workflow

``` text
Start Application
       ↓
Login / Create Account
       ↓
Select Account Type
       ↓
Dashboard
   ↙        ↘
Browse     Manage Listings
Vehicles
   ↓           ↓
Search       Add / Update / Delete
   ↓
Sort & View Details
```

## Performance

  Operation                 Data Structure / Algorithm   Complexity
  ------------------------- ---------------------------- ------------------------
  Find user by ID           Hash Table                   O(1) average
  Add user                  Hash Table                   O(1) average
  Remove user               Hash Table                   O(1) average
  Find vehicle by ID        Hash Table                   O(1) average
  Add vehicle               Hash Table + VehicleList     O(1) average/amortized
  Search vehicles           Array traversal              O(n)
  Sort by price             Merge Sort                   O(n log n)
  Sort by year              Merge Sort                   O(n log n)
  Remove from VehicleList   Array shifting               O(n)
  Load data from file       File traversal               O(n)

## Getting Started

### Requirements

Before running the project, make sure you have:

-   **JDK** installed
-   **JavaFX SDK** configured in your Java IDE
-   An IDE such as **IntelliJ IDEA, Eclipse, or NetBeans**

### Running the Project

1.  Clone or download this repository.
2.  Open the project in your Java IDE.
3.  Configure the JavaFX SDK/library for the project.
4.  Make sure the JavaFX modules required by the application are
    available.
5.  Run the project's main JavaFX class.
6.  The application will open with the AutoMarket interface.

> **Note:** The project uses JavaFX, so JavaFX must be correctly
> configured before running the application.

## Future Improvements

Possible future improvements include:

-   Binary search for sorted vehicle data
-   Vehicle categories and additional filters
-   Purchase or booking functionality
-   Vehicle image support
-   Database-based storage instead of text files
-   Stronger password security
-   Filtering by mileage, fuel type, and transmission
-   Favorites/saved listings
-   User reviews and ratings
-   More advanced authentication and authorization

## Project Purpose

This project was developed as a practical demonstration of how **Data
Structures & Algorithms, OOP, file handling, and JavaFX GUI
development** can be combined to create a functional real-world
application.

## License

This project is intended primarily for educational and academic
purposes.
