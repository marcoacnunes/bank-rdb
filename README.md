Bank Management System
======================

Description
-----------
The following project came as a challenge from [Rumos](https://www.linkedin.com/company/rumos/) to create a digital bank with a management system using JDBC + Spring Boot, allowing to create, store, update and access Clients, Accounts, and Credit/Debit Cards. 

In this repository you will find the following:
* bank-rdb-console-management:
**This Module is used to manipulate the rdb project in a console interface.**
* bank-rdb-model:
**This Module contains the model layer of the Application. Atention: the toString() is Overridden.**
* bank-rdb-repository:
**This Module is responsible for storing in a persistent manner the data used by the Service using MySql as DataBase.**
* bank-rdb-service:
**This Module is responsible for business logic.**
* bank-rdb-springboot:
**This Module is the controller/entrypoint for all restful requests for the following:**
* base url/swagger-ui/

Tech Stack
----------
* Java v11
* MySQL v8.0.27
* SpringBoot v2.4.0

Setup
-----
* download repository
* import all projects into IDE
* run maven clean install on model, repository, service, spring boot, on this order.
* import database schema from repository/database
* access base url/swagger-ui
