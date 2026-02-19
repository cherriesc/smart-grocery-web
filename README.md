# Grocery Price Comparison

Full-stack project for comparing grocery prices across UK stores.
Backend: Spring Boot + PostgreSQL.

---

## Tech-Stack

- Backend: Java 21, Spring Boot
- Database: PostgreSQL
- Containerization: Docker, Docker Compose
- Build Tool: Maven
- API: REST

---

## Project Structure

smart-grocery-web/
│
├── client/                 
├── grocery-backend/        
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
│
├── docker-compose.yml      
├── .gitignore
└── README.md


---

## Requirements

Tools need to be installed:
- Docker Desktop
- Git

---

## How to start the project locally

### 1. Clone repository:
git clone https://github.com/cherriesc/smart-grocery-web.git

### 2. Go to project folder:
cd smart-grocery-web

### 3. Start containers:
docker-compose up --build

### Backend will be accessible:
http://localhost:8080

## Backend API

GET /products  
GET /stores  
POST /basket/compare  

Example request:

POST /basket/compare

{
  "items": [
    { "productId": 1, "quantity": 2 },
    { "productId": 2, "quantity": 1 }
  ]
}

## Database

Database name: grocery
PostgreSQL runs in Docker container

## Environment Variable

---
## 📄 License

This project is intended for learning and development purposes.
