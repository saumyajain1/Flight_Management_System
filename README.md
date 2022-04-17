# My Personal Project


## FlightManager

*What will the application do? Who will use it?* 

This Application is meant to be used by a flight manager as a simple flight management system. 
This system will be able to create and manage a list of flights. 
Flights can be added and removed from the list and the status of the flights can be changed.
This application could resemble a simple version of the software
used in the flight status boards that we see in airports.

*Why is this project of interest to you?*

This project is of interest to me because a flight management system has a real world use case,
 giving me a sense that I'm building software to solve real world problems, which is great. 
I'm also fascinated by flights.


## User Stories

The user can:
- View a **list** of flights
- **Add or Remove** a flight from the list
- View a **flight's details** by selecting it
- Change the **status** of a flight and edit its details
- Save the current list of flights
- Load the list of flights last saved
- View the event log on quitting

Changing the status of a flight can involve:
- Activating and Scheduling the flight (Setting destination, date and time for departure and arrival)
- Making the plane fly or land
- Changing the flight's occupancy
- Deactivating the flight

## Phase 4 task 2
Event Log:

Fri Apr 01 10:43:42 PDT 2022

Loading from file...

Fri Apr 01 10:43:42 PDT 2022

The Flight: nnt444 has been added to the list of flights

Fri Apr 01 10:43:42 PDT 2022

The Flight: AAA1 has been added to the list of flights

Fri Apr 01 10:43:42 PDT 2022

The Flight: BBB123 has been added to the list of flights

Fri Apr 01 10:43:42 PDT 2022

The Flight: ZZZY26 has been added to the list of flights

Fri Apr 01 10:43:42 PDT 2022

The Flight: gg772 has been added to the list of flights

Fri Apr 01 10:43:42 PDT 2022

The Flight: Air Canada 077 has been added to the list of flights

Fri Apr 01 10:43:42 PDT 2022

The Flight: Emirate112 has been added to the list of flights

Fri Apr 01 10:43:42 PDT 2022

Loading finished.

Fri Apr 01 10:44:14 PDT 2022

The Flight: Qatar442 has been added to the list of flights

Fri Apr 01 10:44:39 PDT 2022

The Flight: Air India 333 has been added to the list of flights

Fri Apr 01 10:44:52 PDT 2022

The Flight: BBB123 has been removed from the list of flights

Fri Apr 01 10:44:57 PDT 2022

Saved to file.

Thank You!


Process finished with exit code 0


## Phase 4 task 3: Reflection on possible Design changes
I realized that there is a lot of duplication within the FlightManager class, 
specifically in the creation of different frames that each correspond to a different user story. 
For example, I dispose the main menu frame and switch to an adding a flight frame or editting a flight frame.
The setting up these frames have some duplicated code. I also noticed that the FlightManager class 
is really long and is not very cohesive - it carries out several types of functions 
instead of just one thing (single responsibility principle).

To address this, things I could do to refactor my code include:
- Extract out the common code for setting up frames distributed in different functions and set up one method 
that carries out these operations. (This would remove duplicate code and improve readability)
- Create a new class for each of the different frames. For example a class for the frame for adding 
a flight, one for removing a flight, one for editing flight details, etc. (This would make my code more cohesive)
- Then create an abstract super class called Graphics perhaps and extract out the common code 
from all of the above mentioned new classes and make these classes extend the Graphics class. 
(This would remove duplicate code)


## REFERENCES

- CPSC 210 JsonSerializationDemo: 
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

- Java tutorial for JList:
https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java

- CPSC 210 AlarmSystem:
https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git