# TEST AUTOMATION FRAMEWORK

#### About this project
This project is a test automation framework that support both UI and API applications.

#### Framework usage
##### Required software
* To install Test Automation Framework Java 17 is required: [Amazon Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)

##### Run tests
Typical example of executing all tests and generate Serenity report on environment, defined in `serenity.conf`:

`$ mvn clean verify -Denvironment=env1 -Dcucumber.filter.tags="@tagToRun"`

##### Generate Serenity BDD report
After test execution Serenity will generate some files in folder `target/site/serenity`. 

Single page report (suitable for emails), the report will be generated in folder `target/site/serenity/serenity-summary.html`

##### Code style & conventions
[Java conventions](http://www.oracle.com/technetwork/java/codeconventions-150003.pdf) should be used whenever it is possible and reasonable. Some of conventions may be overridden by project code formatter (see below).

Using code best practices (DRY, KISS, SOLID and others) is encouraged when it is reasonable.

Every framework contributor should install and use project code formatter. Formatting code with
 project code formatter is mandatory before pushing each new commit to main develop branch. 
 
 To use project code formatter with **`Intellij IDEA`** do next steps:
* Open `File` > `Settings` > `Editor` > `Code Style` > `Java`;
* In `Scheme` option select `Import Scheme` > `Intellij IDEA code style XML` > import file `formatter/taf-intellij-java-style.xml`;
* Consider automating code formatting process by:
  * installing plugin **`Save Actions`**;
  * in case creating commits from IDEA, tick `Reformat code` and `Optimize imports` before commit;
  * or format code by manual invocation (`Project` view > select `src` folder > perform `Reformat Code` action > tick `Only VCS changed text`) 
  
  To use project code formatter with **`Eclipse IDE`** do next steps:
* Open `Project` > `Properties` > `Java Code Style` > `Formatter`;
* In Formatter window, check `Enable project specific settings`, select `Import` and select file `formatter/taf-eclipse-java-style`


Delivering clean, readable and maintainable code is personal responsibility of each framework 
contributor despite of passing static code analysis, code review and other quality gates.


### Miscellaneous
##### Useful links
Here you can find some useful information about framework tools:
* [Serenity documentation](https://serenity-bdd.github.io/docs/guide/user_guide_intro)
* [Cucumber documentation](https://docs.cucumber.io/cucumber/)