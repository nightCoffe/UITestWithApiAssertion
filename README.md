# The test automation project for API testing

___
## The project technologies:

![](images/logo/Intelij_IDEA.png)
![](images/logo/Java.png)
![](images/logo/Gradle.png)
![](images/logo/JUnit5.png)
![](images/logo/Selenide.png)
![](images/logo/Selenoid.png)
![](images/logo/Allure_Report.png)
![](images/logo/allureTestOps.png)
![](images/logo/Github.png)
![](images/logo/Jenkins.png)
![](images/logo/Rest-Assured.png)

___

## Run tests from terminal locally

### Run tests :

```bash
gradle clean test 
```

### Serve report:

```bash
allure serve build/allure-results
```

___

## Results

The test results can be found in:
+ [Jenkins](#jenkins)
+ [Allure Report](#allure-report)
+ [Allure TestOps](#allure-testOps)

### Jenkins

[Jenkins job](https://jenkins.autotests.cloud/job/009-UIWithApiNotifications/)

<p align="center">
  <img src="images/screenshot/apiJenkins.JPG">
</p>

### Allure Report


:arrow_right: [The launch with results](https://jenkins.autotests.cloud/job/009-UIWithApiNotifications/9/allure/)

<p align="center">
  <img src="images/screenshot/apiAllure1.JPG">
</p>


<p align="center">
  <img src="images/screenshot/apiAllure2.JPG">
</p>

:arrow_right: [The launch with results](https://jenkins.autotests.cloud/job/009-UIWithApiNotifications/9/allure/#suites/d1071dc10f3fb05fb519df702c0c447f/cd69fc4100523739/)

<p align="center">
  <img src="images/screenshot/apiAllure.JPG">
</p>

### Allure TestOps

<p align="center">
  <img src="images/screenshot/apiallureTestOps1.JPG">
</p>

<p align="center">
  <img src="images/screenshot/apiallureTestOps.JPG">
</p>

___

