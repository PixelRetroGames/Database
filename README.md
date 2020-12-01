# Object Oriented Programming Course
# Homework - VideosDB

November 2020

# My implementation

My code is in the package ciolty that contains the engine and the VideoDBImplementation. 

## Control flow

Main instantiates a Server, gives it the Input and receives back from the Server the output. It converts the output in a JSON format and writes it to the file.

The Server takes the input, loads the Entities in memory with data from the Input (using UnitOfWork->Repository->DataManager). It parses all the actions one by one, and using the ActionController runs each action. The ActionController is responsible for creating the right type of action (using the Action Map and an Action Factory) and executing it.

The Action has a reference to the Unit of Work and the ActionData. To create a new Action we have to implement the Action interface and to override the start and execute functions. These two functions act as the start and update from a game loop. Start is called before execute and is used to get data from the Unit of Work and to determine whether or not the execute function should be called (an error occurred). If an error occurred then the action will return the error message, else it will execute and return the execution completion message.

## Engine

I've used the Model-View-Controller pattern coupled with the Repository and Unit of Work patterns.

The core of this approach are the Actions that act as Models and are managed by the ActionController which creates the right type of Action based on its data, using a Factory.

I've used a Resource Manager to hide the data structure used for data mapping. This way I can change it anytime, without breaking the code. 

Repositories have a Resource Manager and methods of handling its data. I've preferred to implement only the more frequently used and generic functions in the Repository and to move the rest in their respective actions.

The Unit of Work pattern is complementary to the Repository pattern and I used it to store multiple Repositories, that way encapsulating all the database logic within it.

The Server takes the whole database and all the actions as Input and uses the ActionController to run all the Actions and append the results to the Output.

## VideoDBImplementation

To add a new action to the system, all we would have to do is create a new implementation of Action and add it to the ActionController's FactoryMap (initialized from Server). That's all.

I separated the actions into their different types: Commands, Queries and Recommendations.

For queries, I used the Strategy pattern in order to accommodate all the sorting criteria.

For the other two, I've used inheritance to add new functionality, in a Decorator kind of way.

In the Repositories I've implemented the more general methods for getting filtered/sorted data.

# Info
https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/tema

# About the code

We provide the input tests, the logic that parses them and the checker that runs them.

* test_db - contains the tests in JSON format
* src
   * checker - the checker's sources including the checkstyle necessary files, not to be modified
   * fileio - classes for parsing the input files, not to be modified
   * common, utils
        * contain utility classes with constants and static methods
        * you can add your own functionality in the existing classes or add new classes as well
   * actor
        * contains an enum for the types of awards an actor can have - do not modify it
        * you can add here your actor related classes
   * entertainment
        * contains an enum for the supported video genres - do not modify it
        * class for representing a Season of a tv show - do not modify it
        * you can add here your entertainment related classes

We encourage you to organize your implementation in packages based on the functionality their classes offer. Try to keep
your code modular, readable and documented.

# Testing

The Main class runs the checker on your implementation. Add the entry point to your implementation in it.

Run Main to test your implementation from the IDE or from command line.

Run the main method from Test class with the name of the input file from the command line and the result will be written
to the out.txt file. Thus, you can compare this result with ref.