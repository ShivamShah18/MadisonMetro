# Madison Metro Project

## Overview
The **Madison Metro** project is designed to simulate and analyze the operations of a public transportation system, focusing on buses, bus stops, and their interrelationships. The program models various aspects of a transit system, including bus routes, bus drivers, and operations such as traversing and filtering stops. This project demonstrates proficiency in object-oriented programming (OOP) concepts and custom iterator patterns in Java.

---

## Features
1. **Bus Class**:
   - Represents individual buses in the transit system.
   - Includes attributes such as bus ID, capacity, and route details.

2. **BusDriver Class**:
   - Models a driver responsible for operating buses.
   - Tracks information such as driver ID and assigned bus.

3. **BusStopTree**:
   - Implements a binary tree to represent the hierarchical structure of bus stops.
   - Allows for efficient searching and traversal of stops.

4. **Iterators**:
   - **BusForwardIterator**: Traverses bus stops in a forward direction.
   - **BusFilteredIterator**: Filters bus stops based on specific criteria (e.g., passenger count or stop type).

5. **Testing Framework**:
   - `BusStopTreeTester`: Provides a suite of test cases to validate the functionality of the `BusStopTree` and its associated iterators.

---

## Project Structure
The repository contains the following files:

| File Name                  | Description                                                                 |
|----------------------------|-----------------------------------------------------------------------------|
| `Bus.java`                 | Defines the `Bus` class and its associated properties and methods.          |
| `Bus.class`                | Compiled version of `Bus.java`.                                             |
| `BusDriver.java`           | Implements the `BusDriver` class for managing bus drivers.                  |
| `BusDriver.class`          | Compiled version of `BusDriver.java`.                                       |
| `BusStopTree.java`         | Binary tree implementation for organizing and managing bus stops.           |
| `BusStopTree.class`        | Compiled version of `BusStopTree.java`.                                     |
| `BusForwardIterator.java`  | Defines the iterator for forward traversal of bus stops.                    |
| `BusForwardIterator.class` | Compiled version of `BusForwardIterator.java`.                              |
| `BusFilteredIterator.java` | Implements the iterator for filtering bus stops based on custom conditions. |
| `BusFilteredIterator.class`| Compiled version of `BusFilteredIterator.java`.                             |
| `BusStopTreeTester.java`   | Contains test cases to validate the `BusStopTree` functionality.            |
| `BusStopTreeTester.class`  | Compiled version of `BusStopTreeTester.java`.                               |

---

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/ShivamShah18/MadisonMetro.git
