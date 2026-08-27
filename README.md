# E-Commerce Web Automation Suite (Selenium + Cucumber + TestNG)

A BDD-style test automation framework for a demo e-commerce site, built in Java with Selenium WebDriver, Cucumber, and TestNG. It follows the Page Object Model and covers the full customer journey — registration, login, search, product details, cart, and checkout — with data-driven scenarios, Excel-backed test data, and Allure/Extent reporting.

## Features

- **Page Object Model** — one class per page (`LandingPage`, `LogInPage`, `RegistrationPage`, `HomePage`, `SearchPage`, `SearchResultsPage`, `ProductDetailsPage`, `ShoppingCartPage`, `OrderPlacementPage`) for maintainable, reusable locators and actions
- **BDD scenarios with Cucumber** — feature files in plain Gherkin for login, registration, search & filtering, product details, shopping cart, and order placement (including multiple payment/shipping paths)
- **Data-driven testing**
  - Login credentials read from an external Excel file (`LoginData.xlsx`) via Apache POI
  - Scenario Outlines with example tables for registration, search, and checkout variations
- **Reporting & logging**
  - Allure reports (`allure-results/`, Allure Maven plugin) integrated with both TestNG and Cucumber
  - Extent Reports for step-by-step HTML test reports
  - Log4j logging throughout test execution
- **Failure handling** — automatic screenshot capture on scenario failure
- **Test orchestration** — TestNG suite (`testng.xml`) drives a Cucumber `TestRunner`, so tests can be run either as a TestNG suite or through Maven Surefire

## Tech Stack

| Category | Tools |
|---|---|
| Language | Java 8 |
| Browser automation | Selenium WebDriver 4.29, WebDriverManager |
| Test framework | TestNG, Cucumber (Cucumber-Java, Cucumber-TestNG) |
| Build | Maven |
| Reporting | Allure (TestNG + Cucumber6 integration), ExtentReports |
| Logging | Log4j |
| Test data | Apache POI (Excel), JUnit |

## Project Structure

```
├── pom.xml
├── LoginData.xlsx                  # External test data for login scenarios
├── src
│   ├── main/java/pageObjects/      # Page Object classes
│   │   ├── LandingPage.java
│   │   ├── LogInPage.java
│   │   ├── RegistrationPage.java
│   │   ├── HomePage.java
│   │   ├── SearchPage.java
│   │   ├── SearchResultsPage.java
│   │   ├── ProductDetailsPage.java
│   │   ├── ShoppingCartPage.java
│   │   └── OrderPlacementPage.java
│   └── test
│       ├── java
│       │   ├── TestRunner.java             # Cucumber + TestNG entry point
│       │   ├── testng.xml                  # TestNG suite definition
│       │   ├── stepdefinition/             # Step definitions (one per feature)
│       │   └── Utility/                    # Logging, screenshots, Extent reports
│       └── resources/features/             # Gherkin .feature files
├── allure-results/                 # Allure raw results
└── test-output/                    # TestNG / Extent HTML reports
```

## Getting Started

### Prerequisites

- Java 8 (JDK)
- Maven
- Google Chrome (tests run against Chrome via WebDriverManager, which resolves the matching driver automatically)

### Clone the repository

```bash
git clone https://github.com/ananyaaa321/capstone01.git
cd capstone01
```

### Install dependencies

```bash
mvn clean install -DskipTests
```

### Run the tests

```bash
mvn test
```

This runs the TestNG suite defined in `src/test/java/testng.xml`, which triggers `TestRunner` to execute the Cucumber scenarios tagged `@test` under `src/test/resources/features`.

To run a specific tagged subset (e.g. only order placement scenarios tagged `@test01`), update the `tags` value in `TestRunner.java`'s `@CucumberOptions` or pass it via Maven Surefire system properties.

### View reports

- **Cucumber HTML report:** `target/cucumber-reports.html`
- **Allure report:**
  ```bash
  mvn allure:report
  mvn allure:serve
  ```
- **Extent report:** generated under `test-output/`
- **Logs:** `Logs/testlog.logs`

## Test Coverage

| Feature | Scenarios |
|---|---|
| Login | Data-driven login using credentials from Excel |
| Registration | Valid/invalid registration combinations (gender, name, email, password mismatch, missing fields) |
| Home page | Verifies key elements (categories, search bar, header, footer) after login |
| Search | Valid/invalid product search, sorting, result count, advanced/subcategory search |
| Product details | Product info display, add to cart, add to wishlist |
| Shopping cart | View cart, remove item, update quantity, proceed to checkout |
| Order placement | End-to-end checkout across different countries, addresses, and payment methods |

## Notes

- Screenshot capture on failure currently writes to a local Windows path in `CaptureScreenshot.java` — update this to a relative/cross-platform path (e.g. `./screenshots/`) before running on another machine.
- `LoginData.xlsx` should be updated with valid test credentials for your target environment before running the login suite.

## Author

**Ananya** — [@ananyaaa321](https://github.com/ananyaaa321)
