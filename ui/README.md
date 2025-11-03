

# UI Testing with Playwright & Cucumber

## Running Tests

### Basic test execution
```bash
mvn test
```

**Note**: Parallel execution is enabled by default with 3 threads.

### With custom options
```bash
# Run with browser visible (non-headless mode)
mvn test -Dheadless=false

# Run against custom environment
mvn test -DbaseUrl=https://your-env.example.com

# Combine options
mvn test -Dheadless=false -DbaseUrl=https://your-env.example.com
```

### Run with Tags
```bash
# Run only scenarios with specific tag
mvn test -Dcucumber.filter.tags="@CreateUserFirst"

# Run with visible browser and specific tag
mvn test -Dcucumber.filter.tags="@CreateUserFirst" -Dheadless=false

# Run excluding certain tags
mvn test -Dcucumber.filter.tags="not @CreateUserFirst"

# Run with tag combinations (OR)
mvn test -Dcucumber.filter.tags="@CreateUserFirst or @Smoke"

# Run with tag combinations (AND)
mvn test -Dcucumber.filter.tags="@CreateUserFirst and @Regression"
```

## Parallel Execution

### Overview
Tests run in parallel by default (3 threads) for faster execution.

**Execution time comparison:**
- Sequential: ~60 seconds for 3 scenarios
- Parallel (3 threads): ~11 seconds for 3 scenarios

### How it works
- Each scenario runs in a separate thread
- Each thread gets its own browser instance
- `TestContext` is thread-safe (isolated per scenario via PicoContainer)
- Credentials stored in `TestContext` are thread-local

### Configure parallelism
```bash
# Override thread count via system property
mvn test -Dcucumber.execution.parallel.config.fixed.parallelism=5

# Disable parallel execution (temporarily)
mvn test -Dcucumber.execution.parallel.enabled=false
```

**Note**: To permanently disable parallel execution, comment out the parallel configuration in:
- `pom.xml` (Maven Surefire configuration)
- `RunCucumberTest.java` (Cucumber suite annotations)

### Parallel execution configuration
Located in:
1. **pom.xml**: Maven Surefire plugin configuration
2. **RunCucumberTest.java**: Cucumber suite annotations

Default settings:
- **Strategy**: `fixed`
- **Parallelism**: `3` threads
- **Thread-safety**: Guaranteed via Cucumber's PicoContainer DI

### Thread-safety considerations
✅ **Thread-safe (automatic):**
- `TestContext` (new instance per scenario)
- `Page` objects (isolated per thread)
- Browser instances (separate per thread)
- Test data loaded from YAML/properties

⚠️ **Not thread-safe (avoid):**
- Shared static variables
- Writing to same file from multiple threads
- Database operations without proper locking

### Best practices for parallel execution
1. **Scenarios should be independent** - No dependencies between test scenarios
2. **Use unique test data** - Dynamic email generation with timestamp ensures uniqueness
3. **Avoid shared state** - Don't use static fields or singletons
4. **Clean up resources** - Hooks properly close browsers after each scenario

## Configuration Override

### Base URL
- **Default**: Configured in `test.properties`
- **Override**: `-DbaseUrl=...`

### Headless Mode
- **Default**: `true` (headless)
- **Override**: `-Dheadless=false`

### Parallel Threads
- **Default**: `3` threads
- **Override**: `-Dcucumber.execution.parallel.config.fixed.parallelism=5`

## Playwright JavaScript vs Java Comparison

### Parallel Execution

| Feature | Playwright JS/TS | This Project (Java + Cucumber) |
|---------|------------------|--------------------------------|
| **Out-of-the-box parallel** | ✅ Yes (via `--workers` flag) | ⚠️ Requires configuration |
| **Configuration** | Simple CLI flag | pom.xml + suite annotations |
| **Thread isolation** | Automatic | Via Cucumber PicoContainer |
| **Browser instances** | Auto-managed per worker | Auto-managed per scenario |
| **Default behavior** | Parallel enabled | Parallel enabled (configured) |

### Implementation approach
- **Playwright JS/TS**: Test runner provides built-in parallelism
- **Java + Cucumber**: Parallelism via Cucumber JUnit Platform + Maven Surefire

### Example commands comparison
```bash
# Playwright JS/TS
npx playwright test --workers=3

# This project (Java + Cucumber)
mvn test -Dcucumber.execution.parallel.config.fixed.parallelism=3
```

### Advantages of this setup
1. ✅ BDD with Cucumber for business-readable specs
2. ✅ Parallel execution fully configured
3. ✅ Thread-safe test context
4. ✅ Flexible tag-based filtering
5. ✅ JUnit5 integration for IDE support

## Reports
- **HTML Report**: `target/cucumber-report.html`
- **JSON Report**: `target/cucumber.json`

## Test Data Management
- **YAML test data**: `src/test/resources/testdata/registration.yaml`
- **Properties file**: `src/test/resources/test.properties`

### Modifying Test Data
Edit `registration.yaml` to change registration inputs (name, password, address, etc.)

