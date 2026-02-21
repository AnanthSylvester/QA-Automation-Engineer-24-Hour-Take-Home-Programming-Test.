
# SauceDemo BDD Automation Framework

## Overview

This project implements a scalable BDD automation framework to test:

https://www.saucedemo.com/

Built using:
- Java 17
- Selenium 4
- Cucumber 7
- Maven
- Page Object Model (POM)

The framework follows clean separation of concerns and industry-standard automation architecture.

---

## Project Structure

/features      → Gherkin feature files  
/steps         → Step definitions (no locators inside step files)  
/pages         → Page Objects containing locators and actions  
/support       → DriverFactory, Hooks, WaitUtils, LoggerUtils, DBUtils  
/config        → Environment configuration (.env.example, config.properties)  
/testdata      → JSON-based test fixtures  

---

## Setup Instructions

### Prerequisites
- Java 17+
- Maven 3.8+
- Chrome browser installed

### Install Dependencies
mvn clean install

### Run Tests
mvn test

---

## Configuration

Edit:

src/test/resources/config.properties

Example:

browser=chrome  
headless=false  
base_url=https://www.saucedemo.com/

To run in headless mode:

headless=true

---

## Implemented BDD Scenarios

### 1️⃣ Happy Path – End-to-End Purchase

Validates:

- Successful login (Products page visible)
- Add item to cart
- Cart badge updates
- Checkout flow
- Order confirmation page visible

Flow:
Login → Add Item → Cart → Checkout → Order Complete

---

### 2️⃣ Negative Scenario – Invalid Login

Validates:

- Authentication error message displayed
- User remains on login page

---

## Locator Strategy for Adding “Sauce Labs Backpack”

### Preferred (Robust Strategy – Implemented in Code)

By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")

Why this is robust:

- Uses stable `data-test` attribute
- Not index-based
- Not dependent on DOM structure
- Resistant to layout changes
- Official testing attribute provided by the application

---

### Fallback Strategy (Less Ideal)

By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//button")

Why less ideal:

- Depends on DOM hierarchy
- Breaks if layout changes
- More brittle than data-test locator
- Harder to maintain

---

## Database Integration Design

SauceDemo does not provide DB access.  
To demonstrate integration capability, a DB utility is included.

### DB Utility Features

- Config-driven DB connection
- Query execution method
- Proper exception handling
- Clean connection teardown
- Reusable utility design

---

### DB Validation Demonstration (Pseudocode)

String orderId = checkoutPage.getOrderId();

DBUtils.connect();

ResultSet rs = DBUtils.executeQuery(
    "SELECT * FROM orders WHERE order_id='" + orderId + "'"
);

Assert.assertTrue(rs.next());

DBUtils.close();

---

### Sample SQL Query

SELECT * FROM orders WHERE order_id='12345';

---

### Isolation Strategy

- Generate unique order IDs per test
- Avoid shared state between scenarios
- Independent test execution

---

### Cleanup Strategy

DELETE FROM orders WHERE order_id='12345';

---

### Handling Eventual Consistency (Retry Logic Example)

int retries = 5;

while(retries > 0) {
    ResultSet rs = DBUtils.executeQuery(query);
    if(rs.next()) break;
    Thread.sleep(2000);
    retries--;
}

---

# Engineering Notes

## 1️⃣ Why did you choose this framework structure?

This framework follows a clean, layered BDD architecture aligned with industry best practices.

- /features keeps business logic readable and stakeholder-friendly.
- /steps contains only behavior logic and calls Page methods.
- /pages centralizes locators and UI actions using POM.
- /support isolates cross-cutting utilities.
- /config enables environment-based execution.
- /testdata separates logic from test data.

This structure improves maintainability, scalability, readability, and team collaboration.

---

## 2️⃣ How does your wait strategy prevent flakiness?

- Uses explicit waits (WebDriverWait + ExpectedConditions)
- No hard sleeps
- Waits for visibility and clickability before actions
- Waits for URL change after login
- Uses PageLoadStrategy.EAGER to avoid unnecessary blocking
- Avoids mixing implicit and explicit waits

This prevents race conditions and reduces flaky CI failures.

---

## 3️⃣ How does your locator strategy improve stability?

- Uses stable data-test attributes
- Avoids index-based locators
- Avoids brittle DOM-dependent XPath
- Centralized in Page Objects

This ensures maintainability and resilience against UI layout changes.

---

## 4️⃣ How would you scale this to 50+ scenarios?

- Reusable Page methods
- Parameterized step definitions
- Tag-based execution (@smoke, @regression)
- Parallel execution support
- Modular test data
- CI integration

The architecture already supports horizontal scaling.

---

## 5️⃣ How would you execute this in CI/CD?

Using Maven:

mvn clean test

In CI:
- Install Java 17
- Install Maven
- Run in headless mode
- Archive screenshots and reports
- Fail pipeline on test failure

Optional enhancements:
- Parallel execution
- Docker-based execution
- Scheduled regression runs

---

## 6️⃣ Two improvements with more time

1. Parallel execution configuration
2. Advanced reporting (Allure / Extent)
3. Retry mechanism for flaky tests
4. Dockerized Selenium Grid setup

---

## AI Usage Disclosure

AI tools (ChatGPT) were used for boilerplate generation, documentation structuring, and minor refactoring assistance.  
All code was reviewed, modified, and debugged manually.

---

## Assumptions

- SauceDemo is publicly accessible
- DB utility is for demonstration purposes
- Chrome browser available locally

---

## Walkthrough Video

A 5–8 minute walkthrough video is included explaining:

- Framework architecture
- BDD organization
- Locator strategy decisions
- Wait strategy implementation
- DB utility design
- Test execution demo

---

## Submission Includes

- GitHub repository / ZIP file
- README documentation
- Walkthrough video
- CI pipeline configuration



# Setup & Execution Guide

## 1️⃣ Setup Instructions

### Prerequisites

Before running the framework, ensure the following are installed:

- Java 17 or higher
- Maven 3.8+
- Google Chrome browser
- Git (optional, for cloning repository)

### Clone Repository

```bash
git clone <your-repo-url>
cd SauceDemo
```

---

## 2️⃣ Install Dependencies

This project uses Maven for dependency management.

Run the following command from the project root:

```bash
mvn clean install
```

This will:

- Download all required dependencies
- Compile the project
- Run tests
- Generate reports inside the `target/` folder

---

## 3️⃣ How to Run Tests

### Run All Tests

```bash
mvn test
```

### Run Specific Tag (Example: Smoke Tests)

```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

### Run in Headless Mode (CI/CD)

```bash
mvn clean test -Dheadless=true
```

---

## 4️⃣ How to Change Browser / Headless Mode

Configuration is controlled through:

```
src/test/resources/config.properties
```

### Example Configuration

```properties
browser=chrome
headless=false
base_url=https://www.saucedemo.com/
```

### Change Browser

Currently supported:
- chrome

To change:

```properties
browser=chrome
```

(Framework can be extended to support Firefox/Edge easily.)

### Enable Headless Mode

```properties
headless=true
```

Or override from command line:

```bash
mvn clean test -Dheadless=true
```

---

## 5️⃣ Assumptions Made

- SauceDemo is publicly accessible.
- Chrome browser is installed locally.
- Database integration is for design demonstration only (SauceDemo does not provide DB access).
- Tests are designed to be independent and stateless.
- Framework is executed locally or via CI in headless mode.
- `data-test` attributes are stable and intended for automation use.

---

## Output & Reports

After execution:

- Screenshots (on failure) → `target/screenshots/`
- Maven reports → `target/`
- Console logs show scenario execution status

---

## CI Execution

The framework can be executed in CI/CD using:

```bash
mvn clean test -Dheadless=true
```

GitHub Actions pipeline is configured under:

```
.github/workflows/ci.yml
```

It automatically:
- Installs Java 17
- Runs tests
- Uploads screenshots
- Archives test reports
