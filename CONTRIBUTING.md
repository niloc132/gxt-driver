#Contributing
Fork, modify and create a pull against the master. 


##Formatting
* Use tabs with 4 spaces ending at 120 characters per line. 
* No spaces in string concatenation. 


##Testing

###Maven integration testing
Running the integration tests with maven.

* Check the `BaseTest` and change the web driver to firefox.
* Run `mvn clean install -Pintegration-test`

###IDE integration tests
Building and debugging integration tests

* Run `mvn clean install -Pintegration-test -DskipTests`
* Run `mvn jetty:run -Pintegration-test`
* Right click on test and click debug. 

####Assertions
* Turn on assertions when using Eclipse. Add `-ea` to the integration test VM Arguments.

###Browser
Dial up the server and goto: `http://127.0.0.1:9080/app/index.html?menubar` to see the GXT example.

* "menubar" is the scenario name listed at the top of the test. 
* If the scenario is missing it will error with a dialog: "Could not parse key".