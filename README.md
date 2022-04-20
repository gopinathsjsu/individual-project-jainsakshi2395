# individual-project-jainsakshi2395


## Problem Statement
We will develop a flight booking application having flights information and users are allowed to book flights by booking them. There are a limited number of seat in each category for flights. There will be a validation for card, available seats and even if the flight user trying to booking is even present or not. If a booking satisfies all the requirements, the total price for each booking based on number of seats booking in each category will be calculated and the available seats would be updated.

## Instructions to run the application:

Needs Java 8 installed in the system that is running the program.

### Instructions to run the JAR file:
- Opem Command Prompt in the location of JAR File. <br>(Location of JAR File is: `/Users/sakshijain/Desktop/projects/cmpe202_individual_project/cmpe202-individual-project/target/cmpe202-individual-project-1.0-SNAPSHOT.jar)`. 
- Run the following command in the command prompt:
`java -cp <jar_name> "<Flights.csv>" "<Sample.csv>" "<Output.csv>" "<Output.txt>"` <br>


For Example: <br>
`java -cp cmpe202-individual-project-1.0-SNAPSHOT.jar test.RunClient /Users/sakshijain/Desktop/projects/cmpe202_individual_project/cmpe202-individual-project/files/Sample.csv /Users/sakshijain/Desktop/projects/cmpe202_individual_project/cmpe202-individual-project/files/flights.csv /Users/sakshijain/Desktop/projects/cmpe202_individual_project/cmpe202-individual-project/files/Output.csv /Users/sakshijain/Desktop/projects/cmpe202_individual_project/cmpe202-individual-project/files/Output.txt`
- Output files will be created in the directory as the files provided as arguments to run the JAR, if not please check your target folder 

## Design Patterns used are:

I decided to use these design patterns in my project to solve multiple problems speficied along with the design pattern.
- Singleton
- Factory
- Chains of Responsibility
- Iterator

### Singleton:
- It is creational design pattern that lets you ensure that a class has only one instance, while providing the global access point to this instance.
- This pattern is used to build the database for the application. Only the Database class is used as part of this pattern.
- Whenever an instance of the database is needed, the getIntance() method is called so that only one instance of the object can be reused.


### Factory:
- It is a creational design pattern that provides an interface for creating objects in a superclass, but allows subcasses to alter the type of objects that will be created. 
- We have used this pattern to output when a booking is being made. The following files are as part of this pattern implementation: 
     - Interface OutputFile
     - Class BookingFile
     - Class ErrorFile
- When the respective concrete handler figures out the output should be a booking file or error file, we will create an object of OutputFile. The FileReader class is used to read and create files in the application.


### Chains of Responsilibilty:
- It is a behavioral design pattern that lets you pass requests along a chain of handlers. Upon receiving a request, each handler decided either to process the request or to pass it to the next handler in the chain.
- We have used this design pattern to determine for the validation parts like if the booking can be processed correctly or not. The following files are used in this pattern:
     - Interface ValidationHandler
     - Class CardValidation (to check if the card entered is valid or not)
     - Class FlightValidation (to validate if the flight exists or not)
     - Class SeatValidation (to validate if seats are available or not)
- Before we verify the booking can be processed or not, ValidationHandler object is created and will be passed on from one Handler to another using the same.

### Iterator:
The iterator pattern is used to while parsing the flight.csv file to create a flight model. It abstracts away the location from where the data is being read and allows the location to change from a file to a database (in the future) with minimal code refactoring.




