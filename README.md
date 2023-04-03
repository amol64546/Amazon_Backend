# BadaBazaar E-commerce Project

This is a backend e-commerce project made using Java Spring Boot based on Spring MVC Architecture. This Spring Boot application allows customers to order products, add to cart, checkout cart and filter products by category features. It has 15 APIs.

# Table of Contents
- Technologies Used
- Features
- Schema
- Setup Instructions
- API Endpoints
- Learnings

# Schema
![bazar](https://user-images.githubusercontent.com/106758417/229332513-71336889-2b69-427b-922a-73dbb2601ff8.png)

# Technologies Used
- Java Spring Boot
- Spring MVC Architecture
- JPA/Hibernate
- MySQL database
- Postman for API testing

# Features
- Customer can browse products by category
- Customer can add products to their cart
- Customer can checkout cart and place order
- Real-time email notifications for order confirmation and other relevant information


# Setup Instructions
To run the project on your local machine, follow these steps:

1. Clone the repository to your local machine using the following command:
$ git clone https://github.com/your-username/BadaBazaar.git

2. Install the required dependencies using Maven:
$ mvn install

3. Start the application using the following command:
$ mvn spring-boot:run

4. Navigate to http://localhost:8080 on your web browser to access the application.


# API Endpoints
- GET /api/products - Retrieves all products
- GET /api/products/{id} - Retrieves a single product by ID
- POST /api/cart - Adds a product to cart
- GET /api/cart - Retrieves all products in cart
- DELETE /api/cart/{id} - Removes a product from cart
- POST /api/orders - Places an order
- GET /api/orders - Retrieves all orders
- GET /api/orders/{id} - Retrieves a single order by ID
- GET /api/orders/{id}/items - Retrieves all items in an order
- GET /api/categories - Retrieves all categories
- GET /api/categories/{id} - Retrieves a single category by ID
- POST /api/categories - Creates a new category
- PUT /api/categories/{id} - Updates a category by ID
- DELETE /api/categories/{id} - Deletes a category by ID


# Learnings
- During the development of this project, I gained knowledge and experience in the following areas:
- Spring Boot and Spring MVC Architecture
- JPA/Hibernate for efficient data management
- MySQL database connection
- Creating REST API endpoints and testing them using Postman
- Integrating mailing feature to send real-time email notifications to users
- Feel free to use this project as a reference or starting point for your own e-commerce project!

