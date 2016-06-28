********************************
OBJECTIVE:
********************************
	This restful webservice isfor storing employee information(firstname, lastname, phnumber) 
	into database(oracle) and retrieving the employee information from DB using phnumber by
	making use of JBoss server, Maven structure and Java 1.7.

	
********************************
USAGE:
********************************
	1. Create a Simple Maven Project with Group Id and Artifact Id as - "restfulprogram"
	   and change the packaging from jar to war.

	2. Replace the data in pom.xml file with my data in pom.xml

	3. In src/main/webapp, create WEB-INF folder and add my web.xml in that folder.

	4. In src/main/java, create a package with name "restEasy" and copy JsonStoreAndRetrieve.java,
	   Response.java, UserDetails.java classes into that package structure.
	   
	5. Again in src/main/java, create another package with name "restEasy.client" and copy
	   JsonStoreAndRetrieveClient.java, Response.java and UserDetails.java classes into that 
	   package structure.
	
	6. Since I've used Oracle DB, create a table EMPLOYEE_REST with EMPID, FIRSTNAME, LASTNAME
	   and PHONENUMBER as 4 different columns. Create 1 sample employee with details directly.

	   NOTE: Make sure to change the database username and password with yours in getConnection()
		 method in JsonStoreAndRetrieveClient.java class

	7. Then start the JBoss server, Run pom.xml as "Maven install" and Run webapi(i.e restfulprogram)
	   as Run on server.

	8. Then to store employee in database, uncomment setJsonEmp() and comment getJsonEmp() in main() method
	   of JsonStoreAndRetrieveClient.java and run this client java class as java application.
	   
	   Console asks to enter firstname, lastname and phnumber and stores user entered data in database 
	   and prints stored values in Json format.

	9. To retrieve employee info, comment setJsonEmp() and uncomment getJsonEmp() in main() method
	   of JsonStoreAndRetrieveClient.java and run this client java class as java application.

	   Console asks to enter phnumber of the employee to get the details and prints the details in Json
	   format.
	   