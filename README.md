# FoodyBuddy
A  marketplace for for Buyers(Foodies) and Sellers(Buddies) who live around them

## Application setup:
First, you need to clone this repository using:
```
git clone https://github.com/sairamch04/FoodyBuddy.git
```
on your terminal. Alternatively, you can also download the source code in the zip format and then unzip it.

Then, you can go to the project directory. You will find files like pom.xml and src folder in it. Here, you run the following command:
```
mvn install #for building the project and packaging war with dependencies
or 
mvn install -DskipTests  #for building project without running tests
```
You need to have apache maven installed for it
On executing this command, npm will install all the dependencies for you.
Then run the following to start server !



Now your application is setup!

## Starting the application:
Run the command:
```
mvn jetty:run-war #Running the war file in jetty server
or
mvn jetty:run-war -DskipTests #Running the war file in jetty server

```

on your terminal. The application has started.

Visit http://127.0.0.1:8080/FoodyBuddy and hit the requests to it

Live on: http://ec2-52-34-50-160.us-west-2.compute.amazonaws.com:8080
