
# Payment Service API

This project is a REST API developed using Spring Boot for managing payments, with MySQL database integration and RabbitMQ messaging support.

## 🚀 Technologies Used

- Java 17
- Spring Boot
- MySQL
- RabbitMQ
- Docker + Docker Compose
- Swagger
- Postman

## 📊 Component Flow Diagram
Below is the architecture flow of the system components involved:

> Access Component Diagram: [component_flow_diagram.drawio](https://drive.google.com/file/d/1oSGJa3HbSuCubAPv9aPk_lkuhZ55ZKv-/view?usp=sharing)

## 🧪 Available Endpoints

The API exposes the following endpoints, documented via Swagger:

> Access Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

- **POST** `/api/v1/payments`: Create a new payment
- **GET** `/api/v1/payments/{id}`: Retrieve a payment by ID
- **PUT** `/api/v1/payments/{id}/status?status=VALUE`: Update the status of a payment

## 📌 API Usage

All endpoints are prefixed with `/api/v1/payments`.

### 🔹 Create Payment

- **URL:** `POST /api/v1/payments`
- **Headers:** `Content-Type: application/json`
- **Request Body:**
```json
{
  "concept": "Initial Payment",
  "amount": 1,
  "payer": "Carlos",
  "receiver": "Bank",
  "total": 299.99
}
```
- **Response:** `201 Created`
```json
{
  "id": 1,
  "concept": "Initial Payment",
  "amount": 1,
  "payer": "Carlos",
  "receiver": "Bank",
  "total": 299.99,
  "status": "PENDING"
}
```

### 🔹 Get Payment by ID

- **URL:** `GET /api/v1/payments/{id}`
- **Response:** `200 OK`
```json
{
  "id": 1,
  "concept": "Initial Payment",
  "amount": 1,
  "payer": "Carlos",
  "receiver": "Bank",
  "total": 299.99,
  "status": "PENDING"
}
```

### 🔹 Update Payment Status

- **URL:** `PUT /api/v1/payments/{id}/status?status=VALUE`
- **Query Params:** `status` (e.g., COMPLETED, REJECTED)
- **Response:** `200 OK`
```json
{
  "id": 1,
  "status": "COMPLETED"
}
```

### 🔸 Error Response Format

```json
{
  "message": "Resource not found",
  "code": 404
}
```
## ▶️ How to Run the Project

### Requirements

- Docker
- Docker Compose

### Single Step to Run

From the project root directory, execute:

```bash
docker-compose up --build
```
## 🧪 Running Tests

The project includes unit and integration tests using JUnit 5 and Spring Boot Test.

To run the tests locally:

```bash
./mvnw test
```

This will build and start the backend application, MySQL, and RabbitMQ services.  
The database is automatically initialized using the `./database/init_data_base.sql` script.

## 🐬 MySQL Connection Details

- **Host:** `mysql`
- **Port:** `3306`
- **Database:** `paymentsdb`
- **User:** `root`
- **Password:** `root`

## 📨 RabbitMQ

- **Host:** `rabbitmq`
- **Port:** `5672`
- **User:** `guest`
- **Password:** `guest`
- Admin Interface: [http://localhost:15672](http://localhost:15672)

## 🧾 Scripts and Collections

- SQL initialization script is located at `./database/init_data_base.sql`
- Postman collection can be found at `./docs/pagos.postman_collection.json`

You can import the Postman collection to test the API.

---

📫 For questions, check out the available endpoints via Swagger or run tests directly in Postman.
