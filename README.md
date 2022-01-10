# Rozetka automation

Rozetka automation is a Java project for automated testing on [Rozetka](https://rozetka.com.ua)

# Functionality

## Features overview

1. Simple syntax (using **Selenide**)
2. Tests parallel run
3. Multiple browsers support
4. Easy to read reports (using **Allure**)

## Prerequisites

Before installing the whole project you need to install:

1. [Java SDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
2. [Google Chrome](https://www.google.com/chrome/) and/or [Firefox](https://www.mozilla.org/en-US/firefox/new/)
   browsers (for tests running)
3. [IntelliJ IDEA](https://www.jetbrains.com/idea/)
4. [Maven](https://maven.apache.org/)

### Verify you installed correct version

Type in IDE terminal
<li>
   <code>java -version</code> to get installed java version
</li>
<li>
   <code>mvn -version</code> to get Maven version
</li>

## Installation

#### After preparations you need to configure your IDE:<br>

Install Lombok plugin in your IDE if it's not installed:<br>
> **Filter > Setting > Marketplace > type "Lombok" > Install**

# Running tests

1. Run single test class

```
mvn -Dtest=TestName test
```

2. Run single test method

```
mvn -Dtest=TestName#testName test
```

3. Run test suite

```
mvn test -DsuiteXml="test_suite_name.xml"
```

4. Available argument properties that could be added to command<br>
   (values are written for example)

<ul>
    <li>
        <code>-Dbrowser=chrome</code> - selecting browser, available: chrome, firefox, default: chrome;
    </li>
    <li>
        <code>-Dtimeout=1000</code> - setting timeout in millis, default: 2 minutes;
    </li>
    <li>
        <code>-Dresolution=800x600</code> - setting resolution, default: 1366x768;
    </li>
   <li>
      <code>-DparallelType=method/class</code> - run in parallel test methods/classes;
   </li>
   <li>
      <code>-Dthreads=2</code> - set thread pool limit when <b>running in parallel</b>;
   </li>
   <li>
      <code>-DtimeoutPageLoad=2000</code> - setting timeout for page loading, default: 3; minutes
   </li>
</ul>
