# Payment Simulator – Simple README

This is a Spring Boot application that exposes a single API to simulate a payment.  
Below are the steps to run the application and the example curl command to test the API.

---

## 🚀 How to Run the Application

### **1. Prerequisites**
- Java 17 or above  
- Maven 3.8+  

---

### **2. Build the Project**

```bash
mvn clean install
```

---

### **3. Run the Application**

```bash
mvn spring-boot:run
```

The application will start at:

```
http://localhost:8080
```

---

# 🧪 API Documentation (from OpenAPI)

## ✅ **POST /api/payments/simulate**  
Simulates a payment and returns a response string.

### **Request Body (JSON)**

```json
{
  "bookingId": "e7b92c4e-4d8f-4c4f-99d9-41b2c542fb70",
  "amountPaid": 500.0
}
```

### **Fields**
| Field       | Type    | Required | Description |
|-------------|---------|----------|-------------|
| `bookingId` | string (UUID) | Yes | Booking identifier |
| `amountPaid` | number | Yes | Amount paid by user |

---

# 📌 Example cURL Command

### **Simulate Payment**

```bash
curl -X POST "http://localhost:8080/api/payments/simulate" \
-H "Content-Type: application/json" \
-d '{
  "bookingId": "e7b92c4e-4d8f-4c4f-99d9-41b2c542fb70",
  "amountPaid": 500.0
}'
```

---

# 🎯 Response (200 OK)

```
"Payment simulated successfully"
```

---

## ✔ Notes
- This README was auto-generated from your OpenAPI.
