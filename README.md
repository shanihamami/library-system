# Library Microservices System

A basic library management system that allows users to add books, borrow books, and view the list of available books.

## Overview

This project follows a **microservices architecture** with two main services:
- **Book Service**: Allows adding new books and viewing the list of books.
- **Borrow Service**: Allows users to borrow books if available, and view the list of available books.
- **Microservices Design**: Communicating via Feign Client.

### Future Enhancements
The following improvements were planned to enhance functionality and optimize the system:
- **Notification Service**: A separate service to send notifications for overdue books and alert users about return dates.
- **Establish JDBC Connections**: Add support for both **SQL** and **MongoDB**.
- **Borrow Service Enhancements**:
  - Show when a borrowed book will be available again.
  - Allow dynamic handling of the due date, configurable for different products.
- **Book Service Enhancements**:
  - Duplicate check before adding books, with improved logging and custom exception handling.
  - Automatically classify books under "Other" if their genre is not listed.
  
## Usage

### Book Service
- **Add a Book**: `POST /api/books`
- **View All Books**: `GET /api/books`

### Borrow Service
- **Borrow a Book**: `POST /api/borrow`
- **View Available Books**: `GET /api/borrow/available-books`


