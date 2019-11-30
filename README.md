# Secrets Factory - Password Generator

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://openjdk.java.net/)

![Java CI](https://github.com/Skerwe/secrets-factory-password-generator/workflows/Java%20CI/badge.svg)

Generate configurable passwords using lowercase, uppercase, numbers and special characters.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

You will need Java 1.8 installed and [Apache Maven][maven] 3.6+ configured on your system path.

It's best to use the [OpenJDK][openjdk], Windows users can get binaries from [AdoptOpenJDK][adoptopenjdk].

### Installing

1. Clone or download the repository from GitHub:  

```bash
git clone https://github.com/Skerwe/secrets-factory-password-generator.git
```

2. Compile, test and bundle the application:  

```bash
mvn compile package
```

3. Execute the application:  

```bash
java -cp target/secrets-factory-jar-with-dependencies.jar za.web.skerwe.factory.SecretsFactory
```

A default password of 12 lenght will be generated, consisting of 4 uppercase, 4 lowercase, 2 numbers and 2 special characters.

To configure the amount of characters:

```bash
java -cp target/secrets-factory-jar-with-dependencies.jar za.web.skerwe.factory.SecretsFactory -l 4 -u 3 -d 3 -s 2
```

## Running the Tests

The tests use [Junit][junit5] and the [Hamcrest][hamcrest] assertion library

```bash
mvn test
```

## Generating Reports

### Static Code Analysis

1. [PMD](https://pmd.github.io/) static code analyzer

```bash
mvn pmd:pmd
```

2. [Apache Maven Checkstyle Plugin](http://maven.apache.org/plugins/maven-checkstyle-plugin/)

```bash
mvn checkstyle:checkstyle
```

### Code Coverage

1. [JaCoCo](https://www.eclemma.org/jacoco/) Java Code Coverage Library

2. [Cobertura](https://cobertura.github.io/cobertura/)

[Cobertura Maven Plugin](https://www.mojohaus.org/cobertura-maven-plugin/)

```bash
mvn cobertura:cobertura
```

### Javadocs

```bash
mvn javadoc:javadoc
```

### Project Site

Generate and run a static server to serve the site
```bash
mvn site:run
```

### Maven Dashboard

```bash
mvn site
mvn dashboard:dashboard
```

## Built With

* [Passay](https://github.com/vt-middleware/passay) - A password policy enforcement for Java

## License

This project is licensed under the GNU GPLv3 License - see the [LICENSE](LICENSE) file for details

[openjdk]: https://openjdk.java.net/
[adoptopenjdk]: https://adoptopenjdk.net/
[maven]: https://maven.apache.org/
[junit5]: https://junit.org/junit5/
[hamcrest]: http://hamcrest.org/JavaHamcrest/index
