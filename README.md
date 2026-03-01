# 📘 API Automation Framework

**Rest Assured | TestNG | Allure | Maven | GitHub Actions**

---

## 📌 Overview

This project is a **scalable and reusable API automation framework** built using **Java + Rest Assured**, with **TestNG** as the test runner and **Allure** for rich reporting.
It supports **token-based authentication**, follows **clean architecture**, and is fully integrated with **GitHub Actions CI/CD**.

The framework is designed to:

* Be easy to extend for new APIs
* Support CI/CD execution
* Provide readable and traceable test reports
* Follow industry-standard automation practices

---

## 🧰 Tech Stack

| Tool / Library | Purpose                       |
| -------------- | ----------------------------- |
| Java 17        | Programming language          |
| Rest Assured   | API automation                |
| TestNG         | Test execution & assertions   |
| Maven          | Build & dependency management |
| Allure         | Reporting                     |
| GitHub Actions | CI/CD pipeline                |

---

## 🌐 Application Under Test

**Restful Booker API**

```
https://restful-booker.herokuapp.com
```

---

## 📁 Project Structure

```
├── src
│   ├── main
│   │   └── java
│   │       ├── auth
│   │       │   └── TokenManager.java        # Token generation & caching
│   │       ├── config
│   │       │   └── ConfigManager.java       # Environment & config handling
│   │       ├── core
│   │       │   └── BaseRequest.java         # Common request specification
│   │       ├── models
│   │       │   ├── request                 # Request POJOs
│   │       │   └── response                # Response POJOs
│   │       └── service
│   │           ├── AuthService.java        # Auth APIs
│   │           └── BookingService.java     # Booking APIs
│   └── test
│       └── java
│           └── tests
│               └── BookingTest.java        # Test cases
│
├── .github
│   └── workflows
│       └── api-tests.yml                   # GitHub Actions pipeline
│
├── pom.xml
├── .gitignore
└── README.md
```

---

## 🔑 Configuration & Environment Setup

### Required Environment Variables

The framework uses environment variables for sensitive data.

| Variable | Description    |
| -------- | -------------- |
| BASE_URL | API base URL   |
| USERNAME | Admin username |
| PASSWORD | Admin password |

---

### Local Setup (Mac / Linux)

```bash
export BASE_URL=https://restful-booker.herokuapp.com
export USERNAME=admin
export PASSWORD=password123
```

---

### Local Setup (Windows – PowerShell)

```powershell
setx BASE_URL "https://restful-booker.herokuapp.com"
setx USERNAME "admin"
setx PASSWORD "password123"
```

---

### GitHub Actions Secrets

Configure the same variables in GitHub:

```
Settings → Secrets and variables → Actions → New repository secret
```

---

## ▶️ How to Run Tests Locally

### 1️⃣ Clone Repository

```bash
git clone https://github.com/<your-username>/<repo-name>.git
cd <repo-name>
```

---

### 2️⃣ Run Tests

```bash
mvn clean test
```

---

## 📊 Allure Reporting

### Generate Allure Report

```bash
mvn allure:report
```

### Open Report in Browser

```bash
mvn allure:serve
```

📍 Report location:

```
target/site/allure-maven-plugin/index.html
```

---

## 🤖 CI/CD – GitHub Actions

The project includes a fully automated CI pipeline.

### Triggered On:

* Push to `main`
* Pull Request to `main`

### Pipeline Steps:

1. Checkout repository
2. Setup JDK 17
3. Install dependencies
4. Run API tests
5. Generate Allure report
6. Upload test results as artifacts

📄 Pipeline file:

```
.github/workflows/api-tests.yml
```

---

## 🧪 Test Scenarios Covered

### ✔ Positive Scenarios

* Fetch all booking IDs
* Get booking by ID
* Validate booking details
* Create booking
* Update booking
* Delete booking

### ❌ Negative Scenarios

* Invalid credentials
* Unauthorized access
* Invalid booking ID
* Invalid request payload

---

## 🏗 Framework Design Highlights

* **Service Layer Pattern** (AuthService, BookingService)
* **POJO-based serialization & deserialization**
* **Reusable base request specification**
* **Centralized token management**
* **Clean separation of concerns**
* **Allure annotations for reporting**
* **CI/CD ready**

---

## 🧠 Design Decisions (Interview Gold)

* **Why Service Layer?**
  Improves reusability and keeps tests clean.

* **Why POJOs?**
  Strong typing, easier validation, maintainability.

* **Why Allure?**
  Rich reports with request/response visibility.

* **Why GitHub Actions?**
  Lightweight, native CI/CD with minimal setup.

---

## 🧪 Sample Test (Snippet)

```java
@Test
public void checkDepositPaidIsSelectedOrNot() {

    List<BookingResponse> bookings =
            new BookingService().getBookings();

    int bookingId = bookings.get(0).getBookingid();

    BookingResponse bookingDetails =
            new BookingService().getBookingById(bookingId);

    assertThat(bookingDetails.getBooking().isDepositpaid(), is(true));
}
```

---

## 🧹 .gitignore Highlights

* Build artifacts (`target/`, `build/`)
* IDE files (IntelliJ, Eclipse, VS Code)
* OS files (`.DS_Store`)
* Runtime Allure output (`/allure-results`)

---

## 👤 Author

**Sunil Kumar Gouda**
Senior SDET | API & UI Automation

**Skills:**
Java • Playwright • Selenium • Rest Assured • CI/CD • Allure

