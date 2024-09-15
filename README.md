# E-Commerce Application - Spring Boot Backend

This is a simple backend for an e-commerce application, built with Spring Boot and PostgreSQL. It handles product management, user authentication, order processing, and more.

## Features
- **Product Management**: Create, update, delete, and list products.
- **User Authentication**: Register, login, and secure endpoints with JWT.
- **Order Management**: Create and retrieve orders for authenticated users.
- **Database**: Uses PostgreSQL as the database.
- **Mapper** : Uses mapstruct to convert from entity to dto and vice versa
## Prerequisites

Make sure you have the following installed on your system:

- **Java 17**: [Download and install Java](https://adoptopenjdk.net/).
- **Maven**: [Download and install Maven](https://maven.apache.org/download.cgi).
- **PostgreSQL**: [Download and install PostgreSQL](https://www.postgresql.org/download/).
- **Git**: [Download and install Git](https://git-scm.com/downloads).

## Getting Started

### Step 1: Clone the repository

```bash
git clone https://github.com/bishtmanish739/indpro-ecommerce.git
cd indpro-ecommerce
```
### Step 2: 
CREATE DATABASE ecommerce_db;

### Step 3:
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password

### Step 4: 
mvn spring-boot:run


