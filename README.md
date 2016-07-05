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

Live on: http://ec2-52-34-50-160.us-west-2.compute.amazonaws.com:8080/FoodyBuddy

API Details
-----------
Endpoint - http://127.0.0.1:8080/FoodyBuddy
Response Format - JSON

#Operations

Country
```
GET /country - Lists all countries
GET /country/:id - Lists details of country with id
POST /country - Add a country
PUT /country/:id - Lists details of country with id
DELETE /country/:id - Delete a country
```

State
```
GET /state - Lists all states
GET /state/:id - Lists details of state with id
POST /state - Add a state
PUT /state/:id - Lists details of state with id
DELETE /state/:id - Delete a state
```
City
```
>GET /city - Lists all cities
>GET /city/:id - Lists details of city with id
>POST /city - Add a city
>PUT /city/:id - Lists details of city with id
>DELETE /city/:id - Delete a city
```

Locality
```
GET /locality - Lists all locality
GET /locality/:id - Lists details of locality with id
POST /locality - Add a locality
PUT /locality/:id - Lists details of locality with id
DELETE /locality/:id - Delete a locality
```

Apartment
```
GET /apartment - Lists all apartments
GET /apartment/:id - Lists details of apartment with id
POST /apartment - Add an apartment
PUT /apartment/:id - Lists details of apartment with id
DELETE /apartment/:id - Delete a apartment
```

Buyer
```
>GET /buyer - Lists all buyers
>GET /buyer/:id - Lists details of buyer with id
>POST /buyer - Add a buyer
>PUT /buyer/:id - Lists details of buyer with id
>DELETE /buyer/:id - Delete a buyer
```

Seller
```
>GET /seller - Lists all seller
>GET /seller/:id - Lists details of seller with id
>POST /seller - Add a seller
>PUT /seller/:id - Lists details of seller with id
>DELETE /seller/:id - Delete a seller
```
