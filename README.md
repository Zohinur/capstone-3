# 🎮 Video Game Store

## Overview

The **Video Game Store** is a full-stack web application that allows users to browse a catalog of video games, view product details, add games to a shopping cart, and complete the checkout process. The application uses a relational database to store product, category, and user information while providing a responsive frontend for an enjoyable shopping experience.

This project demonstrates full-stack development using **Java Spring Boot**, **SQL databases**, REST APIs, and a frontend interface.

---

## Features

### Customer Features

* Browse all available video games
* View games by category
* View detailed information for each game
* Add games to a shopping cart
* Update or remove items from the cart
* Checkout purchased items
* User authentication and authorization

### Admin Features

* Add new game categories
* Update existing categories
* Delete categories
* Manage products in the database
* Secure admin-only endpoints using Spring Security

---

## Technologies Used

### Backend

* Java
* Spring Boot
* Spring MVC
* Spring Security
* REST API
* Maven

### Database

* MySQL
* SQL

### Frontend

* HTML
* CSS
* JavaScript

---

## Project Structure

```
src
 ├── controllers
 ├── models
 ├── repositories
 ├── services
 ├── security
 ├── config
 └── resources
```

---

## REST API Endpoints

### Categories

| Method | Endpoint                            | Description                     |
| ------ | ----------------------------------- | ------------------------------- |
| GET    | `/categories`                       | Retrieve all categories         |
| GET    | `/categories/{id}`                  | Retrieve a category by ID       |
| GET    | `/categories/{categoryId}/products` | Retrieve products in a category |
| POST   | `/categories`                       | Create a new category (Admin)   |
| PUT    | `/categories/{id}`                  | Update a category (Admin)       |
| DELETE | `/categories/{id}`                  | Delete a category (Admin)       |

Additional endpoints are available for products, shopping cart, users, and checkout.

---

## Database

The application uses a relational SQL database to store:

* Users
* Products
* Categories
* Shopping Cart
* Orders

Spring Data handles communication between the application and the database.

---

## Security

The application uses **Spring Security** to protect sensitive endpoints.

* User authentication
* Role-based authorization
* Admin-only CRUD operations
* Secure REST API endpoints

---

## Installation

### Clone the repository

```bash
git clone https://github.com/Zohinur/capstone-3
```

### Navigate into the project

```bash
cd video-game-store
```

### Configure the database

Update your `application.properties` file with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/videogamestore
spring.datasource.username=root
spring.datasource.password=yearup26
```

### Run the application

```bash
mvn spring-boot:run
```

The backend will start at:

```
http://localhost:8080
```


## Future Improvements

* Product search functionality
* Product reviews and ratings
* Wishlist
* Payment gateway integration
* Order history
* Responsive mobile design
* Product images stored in cloud storage

## Favorite Lines of code 

```
public interface ShoppingCartRepository extends JpaRepository<CartItem, Integer>
{
List<CartItem> findByUserId(int userId);

    CartItem findByUserIdAndProductId(int userId, int productId);

    void deleteByUserId(int userId);
}
```

## Learning Outcomes

Through this project I gained experience with:

* Building REST APIs using Spring Boot
* Creating CRUD operations
* Connecting Java applications to SQL databases
* Implementing authentication and authorization with Spring Security
* Designing a layered application architecture
* Working with frontend and backend integration
* Managing shopping cart functionality
* Using Git and GitHub for version control

