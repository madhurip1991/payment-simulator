# Payment Simulator Service

This project provides a **Payment Simulator API** based on OpenAPI 3.1.0 specification. It allows you to simulate payment requests.

---

## **Base URL**

```
http://localhost:8081
```

---

## **How to Run the Application**

### 1. **Clone the repository**

```bash
git clone <your-repo-url>
cd <your-repo-folder>
```

### 2. **Build the project**

If it’s a Spring Boot Java application:

```bash
./mvnw clean install
```

or

```bash
mvn clean install
```

### 3. **Run the application**

```bash
./mvnw spring-boot:run
```

or

```bash
java -jar target/<your-jar-name>.jar
```

The service will start on **`http://localhost:8081`**.

---

## **API Endpoints**

### 1. **Simulate Payment**

```
POST /api/payments/simulate
```

**Description:** Simulates a payment for a booking.

**Request Body:**

```json
{
  "bookingId": "uuid-of-booking",
  "amountPaid": 1000.50
}
```

**Response:** `200 OK`

Example response:

```
"Payment simulated successfully"
```

---

## **Example curl Commands**

### 1. **Simulate a Payment**

```bash
curl -X POST "http://localhost:8081/api/payments/simulate" \
-H "Content-Type: application/json" \
-d '{
  "bookingId": "123e4567-e89b-12d3-a456-426614174000",
  "amountPaid": 1500.75
}'
```

---

### 2. **Test via Webhook (if webhook URL is needed)**

You can send a POST request to the service to simulate webhook events:

```bash
curl -X POST "http://localhost:8081/api/payments/simulate" \
-H "Content-Type: application/json" \
-d '{
  "bookingId": "123e4567-e89b-12d3-a456-426614174000",
  "amountPaid": 2000
}'
```

> The service will respond with a message confirming the payment simulation.

---

## **Notes**

* Ensure the server is running before making requests.
* `bookingId` must be a valid UUID string.
* `amountPaid` should be a numeric value (double).


