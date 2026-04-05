# Finance Data Processing and Access Control Backend

## 🚀 Overview

This project is a backend system for a **Finance Dashboard** that allows users to manage financial records and view summary analytics based on their roles.

It demonstrates:

* REST API design
* Role-based access control
* Data processing & aggregation
* Backend architecture (Controller → Service → Repository)
* Deployment using Docker & Render

---

## 🛠 Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* H2 Database
* Maven
* Docker
* Render (Deployment)

---

## 🌍 Live API

👉 https://finance-dashboard-1-qmp4.onrender.com

---

## 👥 Roles & Permissions

| Role    | Permissions                                          |
| ------- | ---------------------------------------------------- |
| ADMIN   | Full access (create, update, delete users & records) |
| ANALYST | View records + dashboard                             |
| USER    | Limited access (restricted actions)                  |

---

## 📦 API Endpoints

### 🔹 User APIs

#### Create User

POST /users

```json
{
  "firstname": "Vaishnav",
  "lastname": "Bhosale",
  "email": "vaishnavBhosale@email.com",
  "role": "ADMIN",
  "status": "ACTIVE"
}
```

---

### 🔹 Financial Records APIs

#### Create Record (ADMIN only)

POST /records/{userId}

#### Get Records

GET /records?userId=1

#### Filter Records

GET /records?type=EXPENSE
GET /records?category=Food
GET /records?date=2026-04-04

#### Delete Record

DELETE /records/{id}

---

### 🔹 Dashboard APIs

#### Summary

GET /dashboard/summary

Response:

```json
{
  "totalIncome": 5000,
  "totalExpense": 200,
  "balance": 4800
}
```

---

## 🔐 Access Control

Role-based restrictions are implemented at the service layer:

* Only ADMIN can create/delete records
* Non-admin users are restricted from modification
* Proper error messages are returned for unauthorized actions

---

## ⚠️ Error Handling

* Custom exceptions used (e.g., ResourceNotFound)
* Validation errors handled
* Meaningful error messages returned

Example:

```json
{
  "message": "Only ADMIN can create records",
  "status": 403
}
```

---

## 💾 Database

* H2 in-memory/file database used
* JPA used for ORM
* Auto schema generation enabled

---

## 🐳 Deployment

### Run Locally

```bash
mvn clean install
java -jar target/*.jar
```

### Docker

```bash
docker build -t finance-dashboard .
docker run -p 8080:8080 finance-dashboard
```

---

## 🎯 Assumptions

* Authentication is simplified (no JWT)
* Role is passed via user data
* H2 used for simplicity

---

## ✨ Future Improvements

* JWT Authentication
* Pagination
* Swagger API docs
* Category-wise analytics
* Frontend dashboard (React)

---

## 👨‍💻 Author

Vaishnav
