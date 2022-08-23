## Summary

TUI DX Backend technical Test v2

The base project uses lombok, so you have to install it. You can use the following guide https://www.baeldung.com/lombok-ide

## Order Pilotes

## For Run 
mvn spring-boot:run   

## These are the steps of the happy path :D

## For create user/client (POST)

![image](https://user-images.githubusercontent.com/22691843/186049007-de04aa0f-13fd-4472-b71c-a5b20d96ecfe.png)

{
    "user": String,
    "password": String,
    "token": null,
    "email": String,
    "firstName": String,
    "lastName": String,
    "telephone": String
}

## For new order (POST)

![image](https://user-images.githubusercontent.com/22691843/186049229-caff1d8c-9b1d-4b71-bb03-02424441d102.png)

{
    "number": null,
    "count": String , //    LOW_COUNT(5),    MEDIUM_COUNT(10),    HIGH_COUNT(15);
    "total": 0,
    "time": null,
    "clientId": Integer, //number of id Client
    "address": {
        "street": String,
        "postcode": Integer,
        "city": String,
        "country":String
    }
}

## If you want edit order less 5 minut (PUT)

![image](https://user-images.githubusercontent.com/22691843/186049561-399d3ed4-3e9c-4e48-8907-404d0eade272.png)

{
    "number": Integer, //number of order
    "count": String , //    LOW_COUNT(5),    MEDIUM_COUNT(10),    HIGH_COUNT(15);
    "total": 0,
    "time": null,
    "clientId": Integer, //number of id Client
    "address": {
        "street": String,
        "postcode": Integer,
        "city": String,
        "country":String
    }
}

## If you want get orders, you need get token. (use jwt)
## Put true user and password for get the token (POST)

![image](https://user-images.githubusercontent.com/22691843/186049871-eaecee45-0a9a-4130-9955-cd0747996a94.png)

user : String
password : String

## Get order by parameters: firstName, lastName, user, email and more token (GET)
Put token bearer for the access to api

![image](https://user-images.githubusercontent.com/22691843/186050111-3fca803e-b39b-40e6-bc25-e52c3efc1d42.png)

## For see the data

http://localhost:8080/console

![image](https://user-images.githubusercontent.com/22691843/186050947-ba9198c7-57ed-48ce-ad8a-9dd055b68975.png)

Press continue and run your querys

![image](https://user-images.githubusercontent.com/22691843/186050880-a9e69f96-ef61-494c-a8c2-428d83550b59.png)


