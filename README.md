# Vass Java Test

The test requirements can be found in [TestJava2023.md](./TestJava2023.md)

The test cases requested are located in [PriceControllerIntegrationTest.java](./src/test/java/com/vass/vasstest/controllers/PriceControllerIntegrationTest.java)  
The test methods ar named:
- test1
- test2
- test3
- test4
- test5

After running the application with the classic SpringBoot configuration:

* You can check the endpoint and its documentation at [Swagger](http://localhost:8080/swagger-ui/index.html)
* You can access the H2 database console at [H2 Console](http://localhost:8080/h2-console/) Using the following settings:
    * Driver Class: **org.h2.Driver**
    * JDBC URL: **jdbc:h2:mem:testdb**
    * User Name: **sa**

### IMPORTANT

The **/price** endpoint accepts the ISO date-time format: **yyyy-MM-ddTHH:mm:ss** i.e. **2020-06-14T10:00:00**