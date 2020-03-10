# Java Spring E-commerce

E-commerce REST API based on Spring Boot, Hibernate ORM with H2, Swagger API docs, JWT.

## REST API Endpoints

All inputs and outputs use JSON format.

**To open Swagger (interactive) API documentation, navigate your browser to [YOUR-URL]/swagger-ui.html**


```
/api/signin
  POST / - Signin using login: fulano and password:123456

/api/products
  GET / - List of products
  POST / - Add product
  GET /{id} - View product
  PUT /{id} - Update product

/api/tickets
  GET / - List of groups
  POST / - Add ticket
  GET /{code} - View ticket
  DELETE /{id} - Delete ticket

/api/users
  GET / - List of orders
  POST / - Add User
  PUT /{id} - Update User
  DELETE /{id} - Delete User
  POST /upload/{id} - Upload User image

/api/cart
  GET - Get cart for current user
  POST /add - Add CartItem to cart with ID {id}
  DELETE /{id} - Remove product with ID {id}
  POTS /checkout - Create order from cart

```
