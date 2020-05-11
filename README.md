# familysearch-persons-api

## Running the Program

To run the program, download api-3-0.0.1-SNAPSHOT.jar, and from a command line type:
`java -jar api-3-0.0.1-SNAPSHOT.jar`

Then in a browser go to: http://localhost:8080/persons/

(There are already two persons added by default.)

## The API

The API supports create (POST), read (GET), update (PUT), and delete (DELETE).

Note that to read, update, or delete a given person, go to http://localhost:8080/persons/1 (replace 1 with the id of interest). 


## The Architecture

I chose to divide the code into a controller (`PersonController.java`), service (`DefaultPersonService.java`, which inherits from a `PersonService` interface), models (`Person.java` and `Address.java`), and repositories (`PersonRepository.java`).


## Unit Tests

I have written unit tests for all methods in the PersonController and the DefaultPersonService. Note that I could not find a standard way to test the model/repository/persistence layer, so unfortunately the unit tests do not show the constraints on the data (such as how two persons with the same first and last names, regardless of capitalization, is invalid). However, the constraints should all be working as instructed.

Note that I'm not 100% positive the unit tests are included in the executable jar file referenced above.  They should be in another jar file (that does not seem to execute properly), `out/artifacts/api_3_jar`, and in the `src` directory, of course.
