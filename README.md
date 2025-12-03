# BE API Tests - regres.in

This project contains backend API tests for the public API at [https://reqres.in](https://reqres.in), written in **Java**, **JUnit 5**, and **RestAssured**.

Tests cover **GET** and **POST** endpoints with validation of status codes, response content.

---

## Running Tests

### 1. From IntelliJ IDEA
1. Open the project in IntelliJ IDEA.
2. Navigate to the `src/test/java` package.
3. Right-click on a test class (or the test package) → **Run**.
4. Test results appear in the **Run** panel.

### 2. From Command Line (Gradle)
1. Ensure a JDK 17+ is installed and set in your environment.
2. Open a terminal in the project root folder.

On Linux/macOS/Windows Git Bash, run:

```bash
./gradlew clean test
```
On Windows PowerShell / Command Prompt, run:

```bash
.\gradlew.bat clean test
```

