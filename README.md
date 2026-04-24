# DemoQA UI Automation Framework
DemoQA_POM
├── src/main/java/com/demoqa
│   ├── config        # Configuration loader (ConfigProvider)
│   ├── core          # BasePage with core Selenium wrappers and explicit waits
│   ├── pages         # Page Object classes mapped to application views
│   └── utils         # Helpers (DataHelper for Faker)
├── src/test/java/com/demoqa
│   ├── core          # TestBase managing the WebDriver lifecycle and setup
│   ├── tests         # JUnit 5 test classes containing assertions
│   └── utils         # Custom extensions (TestListener for screenshots)
├── src/test/resources
│   ├── config.properties # Global environment configurations
│   └── logback.xml       # Logging configurations
├── screenshots           # Directory for auto-generated failure screenshots
└── pom.xml               # Maven dependencies and build plugins

### 🚀 Project Overview
A robust, scalable, and maintainable UI Test Automation Framework built to verify the core functionalities of the [DemoQA](https://demoqa.com) web application. This project demonstrates enterprise-level testing practices, focusing on stability, fast execution, and clean architecture.

### 🛠️ Tech Stack
* **Language:** Java 21 (LTS)
* **Automation Tool:** Selenium WebDriver 4.43.0
* **Testing Framework:** JUnit 5 (Jupiter)
* **Build Tool:** Maven
* **Assertions:** AssertJ (for fluent and descriptive validations)
* **Data Generation:** DataFaker (to prevent hardcoded data and ensure test independence)
* **Logging:** SLF4J + Logback (for detailed, step-by-step test execution tracking)

### 🏗️ Architecture & Design Patterns
* **Page Object Model (POM):** Strict separation of page locators/actions from test logic (Single Responsibility Principle).
* **Fluent Interface (Method Chaining):** Page methods return page instances (`this` or new pages), making test scenarios highly readable and declarative.
* **Direct Navigation:** Tests bypass unnecessary UI menus, using direct URL injections for faster execution and reduced flakiness.
* **Smart Synchronization:** Replaced implicit waits with explicit `WebDriverWait` strategies to handle dynamic web elements reliably.
* **Configuration Management:** Environment variables, URLs, and credentials are abstracted into a `config.properties` file.

### ✨ Key Features
* **Custom Test Listener:** Implemented a JUnit 5 `TestWatcher` extension that automatically captures and saves screenshots whenever a test fails, aiding in rapid debugging.
* **Dynamic Test Data:** Every test run generates unique user profiles (names, emails, phones) using DataFaker, eliminating database state conflicts.
* **Informative Logging:** Granular logs capture every interaction (e.g., `INFO - Clicking on element: [Submit Button]`), making CI/CD pipeline logs easy to read.

### 🧪 Test Coverage
The framework currently covers the following modules:
* **Forms:** Positive and negative scenarios for complex registration forms (inputs, radio buttons, checkboxes, dropdowns, file uploads).
* **Alerts & Windows:** Handling JS Alerts, Confirmations, Prompts, IFrames, and switching between multiple browser tabs.
* **Elements:** Verifying the status of dynamically loaded broken links (HTTP 500) and broken images.
* **Book Store:** User authentication and profile validation.

### 💻 Getting Started

#### Prerequisites
* JDK 21 installed and configured in your `PATH`.
* Apache Maven installed.
* Google Chrome (Selenium Manager will automatically handle the ChromeDriver binary).

#### Installation & Execution
1. Clone the repository:
   ```bash
   git clone [https://github.com/YourUsername/QA77_DemoQA_POM.git](https://github.com/YourUsername/QA77_DemoQA_POM.git)
