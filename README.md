# introsde-2015-assignment-3-server

This project implements a SOAP Web Service for managing (CRUD) a database of people and the history of their health measurements (i.e. weight, height, steps walked). It is an assignment for the Introduction to Service Design and Engineering lecture at UNITN, Winter Semester 2015-16.

The project is implemented in Java using stardard JAX-WS and JPA. Communication is Document based. The database is a simple sqlite file.

The project includes a client as well which you can find here ([introsde-2015-assignment-3-client](https://github.com/djbb7/introsde-2015-assignment-3-client)), which connects to the server and tests all of its functionality.


##Package Structure

The project is divided into 6 packages. Each package contains:

`introsde.assignment.dao`: Class for handling Java Persistence Entity Manager.

`introsde.assignment.model`: JPA Entities.

`introsde.assignment.model.request`: Classes that represent the objects expected from the client.

`introsde.assignment.model.response`: Classes that represent the response sent to the client.

`introsde.assignment.soap`: Web Service interface, implementation and endpoint.

`introsde.assignment.test`: Unit tests for functionality.

##Methods Implemented

The methods the Web Service offers correspond to the following operations:

* `readPersonList()` => Lists all the people in the database.
* `readPerson(Long id)` => Gives all the Personal information plus current measures of one Person identified by {id} (e.g., current measures means the latest measure of each measure type).
* `updatePerson(Person p)` => Updates the personal information of the Person identified by {id} (e.g., only the Person's information, not the measures of the health profile).
* `createPerson(Person p)` => Creates a new Person and returns the newly created Person with its assigned id (if a health profile is included, it also creates those measures for the new Person).
* `deletePerson(Long id)` => deletes the Person identified by {id} from the database.
* `readPersonHistory(Long id, String measureType) => Lists all the values (the history) of {measureType} (e.g. weight) for the Person identified by {id}.
* `readMeasureTypes()` => Lists all the types of measures.
* `readPersonMeasure(Long id, String measureType, Long mid)` => Returns the value of {measureType} (e.g. weight) identified by {mid} for Person identified by {id}.
* `savePersonMeasure(Long id, Measure m)` => Saves a new measure {m} (e.g. weight) of Person identified by {id} and archive the old value in the history.
* `updatePersonMeasure(Long id, Measure m)` => Update the measure identified with {m.mid}, related to the Person identified by {id}.

##Files included

The project contains some additional files.

`peoplemeasure.sqlite`: Database file. For convenience, the latest measurements of a person are extracted from the measurement's table with a View.

##Execution

This project contains a `build.xml` file which can be run with `ant`. It will download all the required dependencies using ivy. It will also download ivy if it is not installed.


First step is to compile everything
```
ant install
```

To start the standalone server run:
```
ant run
```

For running unit tests run:
```
ant junit
```
