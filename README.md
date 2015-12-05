# introsde-2015-assignment-3-server

###Server

This project implements a SOAP Web Service for managing (CRUD) a database of people and the history of their health measurements (i.e. weight, height, steps walked). It is an assignment for the Introduction to Service Design and Engineering lecture at UNITN, Winter Semester 2015-16.

The project is implemented in Java using stardard javax.ws, JAXB and JPA. Communication is Document based. The database is a simple sqlite file.

The project includes a client as well which you can find here ([introsde-2015-assignment-3-client](https://github.com/djbb7/introsde-2015-assignment-3-client)), which connects to the server and tests all of its functionality.


##Package Structure

The project is divided into 6 packages. Each package contains:

`introsde.assignment.dao`: Handling Java Persistence Entity Manager.

`introsde.assignment.model`: JPA Entities.

`introsde.assignment.model.request`: Classes that represent the objects expected from the client.

`introsde.assignment.model.response`: Classes that represent the response sent to the client.

`introsde.assignment.soap`: Web Service interface, implementation and endpoint.

`introsde.assignment.test`: Unit tests for functionality.

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
