# BadaBazaar E-commerce Project

This is a backend e-commerce project made using Java Spring Boot based on Spring MVC Architecture. This Spring Boot application allows customers to order products, add to cart, checkout cart and filter products by category features. It has 15 APIs.

# Table of Contents
- Technologies Used
- Features
- Schema
- API Endpoints
- Setup Instructions

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

# Schema
![bazar](https://user-images.githubusercontent.com/106758417/229332513-71336889-2b69-427b-922a-73dbb2601ff8.png)

# API Endpoints
- POST /seller/add - Creates a new seller
- POST /product/add - Creates a new product
- POST /customer/add - Creates a new customer
- POST /card/add - Creates a new card
- POST /cart/add - Creates a new card
- POST /cart/checkout - places order of all items in cart
- POST /order/place - places order of product directly


- GET /product/get/category/{category} - Retrieves all products by category
- GET /item/view/{productId} - Retrieves a product by ID
- GET /cart/view/{cartId} - Retrieves all products in cart
- GET /customer/get/{customerId} - Retrieves customer by ID
- GET /customer/get/email - Retrieves customer by email
- GET /seller/get/{sellerId} - Retrieves seller by ID
- GET /seller/get/pan - Retrieves seller by Pan No
- GET /customer/get/all - Retrieves all customers
- GET /seller/get/all - Retrieves all sellers
- GET /card/view/all - Retrieves all cards by customer ID

- PUT /customer/update/mob - Updates a customer mobile no
- PUT /customer/update/email = Updates a customer email ID

- DELETE /customer/delete/{customerId} - Deletes a customer by ID
- DELETE /card/delete - Deletes a card by customer ID

# Setup Instructions
To run the project on your local machine, follow these steps:

1. Clone the repository to your local machine using the following command:
```
git clone https://github.com/your-username/BadaBazaar.git 
```

2. Install the required dependencies using Maven:
```
mvn install
```

3. Start the application using the following command:
``` 
mvn spring-boot:run 
```

4. Navigate to http://localhost:8080 on your web browser to access the application.





