# QA Automation Framework (2-Layered Architecture)

## 📌 Overview

This is a 2-Layered Hybrid Test Automation Framework designed to support both:

- Core Automation (TestNG-based functional tests)
- BDD Automation (Cucumber + TestNG)

The framework is built with scalability, parallel execution, thread safety, and reporting isolation in mind.

---

## 🧱 Architecture (2 Layers)

### 1️⃣ Core Automation Layer
- Pure TestNG-based automation
- Used for:
  - Smoke tests
  - Regression tests
  - Functional UI tests
- Faster execution with minimal overhead

### 2️⃣ BDD Automation Layer
- Cucumber + TestNG
- Used for:
  - Business-readable scenarios
  - Stakeholder collaboration
- Shares the same core utilities and infrastructure

Both layers reuse the same drivers, utilities, listeners, validations, and reporting setup.

---

## 🗂️ Project Structure

 → TestContext & ScenarioContext
 → ThreadLocal managers
-> Page Object Model
→ Base test setup & teardown
→ Core TestNG test cases
→ Cucumber step definitions
→ TestNG listeners
→ Retry logic
→ Business & UI validations
→ Selenium reusable actions

---

## 🔐 Thread Safety & Parallel Execution

- Uses ThreadLocal-based TestContext
- Supports parallel execution using:
  - parallel="methods"
  - configurable thread-count
- Each test runs with:
  - its own WebDriver
  - its own Page Objects
  - its own TestContext

Safe for parallel execution with no shared state.

---

## 🚀 Test Execution

### Core TestNG Tests
mvn test -Pcore

### Cucumber BDD Tests
mvn test -Pcucumber

---

## 📊 Reporting (Allure)

### Allure Results Directories
- Core:
  target/allure-results/core
- Cucumber:
  target/allure-results/cucumber

### Generate Reports
allure generate target/allure-results/core -o target/allure-report/core --clean
allure generate target/allure-results/cucumber -o target/allure-report/cucumber --clean

Reports are isolated per layer and never override each other.

---

## 🔁 Retry Strategy

- Implemented using TestNG IRetryAnalyzer
- Retry rules:
  - Retry only Selenium / infra-related failures
  - Skip retry for business and UI validation failures
- Failure origin is identified using stack trace analysis

Prevents masking real application defects.

---

## ✅ Reusable Validations

- Centralized under util.validations
- Uses TestNG assertions
- Clear failure messages visible in:
  - Console logs
  - Allure reports

Ensures clean test methods and consistent validation logic.

---

## 🧩 Selenium Utilities

Reusable Selenium actions include:
- Smart explicit waits
- Retry click mechanism
- JavaScript fallback actions
- Page load synchronization
- Element highlighting

Located under webUtil.SeleniumActions to reduce flaky failures.

---

## 🧪 Listener Support

- Test execution tracking
- Screenshot capture on failure
- Execution flow logging
- Works correctly with parallel execution

Configured via testng.xml.

---

## ⚙️ CI/CD Friendly

- Jenkins compatible
- Allure reports generated in post-build steps
- Proper handling of build exit codes
- Clean separation of execution and reporting stages

---

## 🎯 Key Highlights

✔ Two-layered architecture (Core + BDD)  
✔ Thread-safe parallel execution  
✔ Isolated Allure reports  
✔ Smart retry mechanism  
✔ Page Object Model  
✔ Centralized validations  
✔ Industry-aligned framework design  

---

## 🧠 Layer Selection Guide

| Use Case              | Recommended Layer |
|----------------------|-------------------|
Smoke / Regression     | Core TestNG       |
Business Scenarios     | Cucumber BDD      |
Fast feedback          | Core              |
Stakeholder readable   | BDD               |

---

## 📌 Conclusion

This framework provides a balanced approach to speed, maintainability, and reliability, making it suitable for real-time enterprise automation needs.
