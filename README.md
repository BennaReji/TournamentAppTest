# 🧪 Tournament App - Selenium Tests

![Selenium Tests](https://github.com/BennaReji/TournamentAppTest/workflows/Selenium%20Tests/badge.svg)

End-to-end automated testing for the Tournament Score Sheet application using Selenium WebDriver with Java.

## 🎯 Test Type

**Black Box Testing** - Tests user interactions without knowledge of internal code structure.

## 🛠️ Tech Stack

- **Language:** Java 11
- **Testing Framework:** JUnit 4
- **Automation Tool:** Selenium WebDriver
- **Build Tool:** Maven
- **Driver Manager:** WebDriverManager
- **CI/CD:** GitHub Actions

## 📦 Installation
```bash
git clone https://github.com/BennaReji/TournamentAppTest.git
cd TournamentAppTest
mvn clean install
```

## 🧪 Running Tests

### Locally
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=TournamentAppTest

# Run specific test method
mvn test -Dtest=TournamentAppTest#testAppLoads
```

### View Test Reports
```bash
# Reports are generated in:
target/surefire-reports/index.html
```

## 📋 Test Coverage

### Features Tested
- ✅ Application loads successfully
- ✅ Team count toggle (4, 5, 6 teams)
- ✅ Team name customization
- ✅ Round-robin match scoring
- ✅ Winner highlighting
- ✅ Standings table updates
- ✅ Playoff bracket display
- ✅ Reset functionality
- ✅ Complete tournament workflow

### Test Classes
- `TournamentAppTest.java` - Main test suite
- `TeamManagementTest.java` - Team operations
- `RoundRobinTest.java` - Match scoring
- `StandingsTest.java` - Standings table
- `PlayoffTest.java` - Playoff brackets

## 🏗️ Project Structure
```
TournamentAppTest/
├── src/
│   ├── main/java/com/tournament/pages/
│   │   ├── BasePage.java
│   │   └── TournamentPage.java
│   └── test/java/com/tournament/
│       ├── tests/
│       │   ├── TournamentAppTest.java
│       │   ├── TeamManagementTest.java
│       │   ├── RoundRobinTest.java
│       │   ├── StandingsTest.java
│       │   └── PlayoffTest.java
│       └── utils/
│           └── TestBase.java
├── pom.xml
└── README.md
```

## 🤖 CI/CD Pipeline

Tests run automatically on:
- ✅ Every push to main/master
- ✅ Every pull request
- ✅ Scheduled daily at 9 AM UTC

## 🔗 Related Repositories

- **React App:** [TournamentApp](https://github.com/BennaReji/TournamentApp)

## 📝 Notes

- Tests run in **headless mode** in CI/CD
- ChromeDriver is automatically managed by WebDriverManager
- Target URL: `http://localhost:5173` (update in TestBase.java for deployed version)

## 👤 Author

**Benna Reji**
- GitHub: [@BennaReji](https://github.com/BennaReji)