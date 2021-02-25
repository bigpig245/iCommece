###### iCommerce

**Technical Design**
- Technical stack: Spring Boot, Spring Security, Spring JPA Data, Embedded H2 Database
- Please see technical design in folder: /uml

**Microservices**
We have 4 services here: 
- 2 services which holding businesses and interact with DB (which are configured as internal APIs and do not public)
    + service-user: handle user activities such as login via access token, track user activities...
    + service-product: handle products such as create/update products, handle user's order activities...
    + database versioning and change-sets will be handled by liquibase.
- 2 backend services play as API gateways for client interact. All the authentication processes placed here.
    + backend-product: product management such as create/update products, all are required authentication
    + backend-order: manage customer's activity such as 
        - searching/viewing products: not required authentication, in case authenticated, all those activities will be tracked.
        - add to cart/place order: required authentication
    In the real case, the authentication on backend-product and backend-order should be separated. Due to lack of time, I am using the same authentication for both.

**Folder structure**
- config: holding the configurations of services such as spring security configuration, authentication if needed, rest exception handler...
- controller: manage controller
- domain: manage the entities (if any)
- dto: manage the DTOs
- exception
- rest: holding the external rest API configuration (if any)
- service: holding the business of services
- utils: utility classes
- resources: contains server configuration and database changesets

**Java libararies**
- Spring boot: create stand-alone Spring applications with embedded tomcat
- Spring security: handle authentication
- Spring JPA: store and retrieve data in a relational database
- querydsl-jpa: querydsl provides a typesafe querying layer on top of JPA. It is used for building searching query easier.
- Liquibase: manage database versioning and change-sets
- retrofit: type-safe HTTP client, provides a powerful framework for authenticating and interacting with APIs and sending network requests with OkHttp
- mapstruct: a code generator that greatly simplifies the implementation of mappings between Java bean types based on a convention over configuration approach
- podam (for unit-test):  a lightweight tool to auto-fill Java POJOs with data
- assertJ: provides a rich set of assertions, truly helpful error messages

**Set Up**
- Required dependences: maven 3.5, java 8
- Each folder is a separated service, you must go to each service to start them

**For each service**
- run `mvn clean install` to build the project
- For dev mode: Create spring boot Application and run server or run `mvn spring-boot:run`
- The default port for each project is config in `application.properties`. You can change them manually if needed.

Due to lack of time, I can only provide some basic unit test cases, mostly in service-product.

**API guideline**
- I support a simple authentication method using bearer token. I also generated some seed user data in 
`iCommerce\service-user\src\main\resources\db\changelog\changelog-202102211142.xml`,
you can go through this file to find the appropriate access token for authentication.

- I prepare some basic cases for those requirements by postman, you can see the sample API collection below:
https://www.getpostman.com/collections/79551085de185102eda1
You need to specify an environment and run collection. I already prepared some environment variables in the pre-request scripts in each folder