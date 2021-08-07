I made CRM REST client app that communicates with CRM REST API, two separated web applications which communicates together on the server.GitHub link of the CRM REST API is: https://github.com/marjanovicbrane/spring-crm-rest-api-db


On the client side we can show list customer objects retrieved from the database.

![image](https://user-images.githubusercontent.com/61464267/128600468-7777808a-fdb4-4237-851d-cbb3138fce96.png)


We also have a button “Add Customer” and when we click on that button a new form opens for adding a new customer and when we click on button Save, that customer object will be saved to the database.

![image](https://user-images.githubusercontent.com/61464267/128600684-2ca23936-8672-4dab-ba50-315da304b332.png)

We created a “Update” link and when we click on that button a new form opens for updating a customer.We have now pre-populated form.When we update our customer object and when we click on button Save, that customer object will be saved to the database.

![image](https://user-images.githubusercontent.com/61464267/128600771-d8db5ca0-775b-4cb4-ae3a-8e9f48af2805.png)

We also created a “Delete” link to delete a customer object from the database.

We created CRM REST API with all CRUD methods, using http request methods:
GET: get a list of customers, GET: get a single customer by id, POST: add a new customer, PUT: update a customer, DELETE: delete a customer.
I used REST client Postman to test this CRM REST API with all HTTP request methods and I also used Jackson to convert automatically Java POJO to JSON and vice versa.

![image](https://user-images.githubusercontent.com/61464267/128600845-bdd4a8d2-0106-410a-b2a9-769383b0a8d5.png)

The CRM Web App REST client will have a new Customer Service implementation.Hibernate DAO code is no longer required in the CRM Web App REST client.The database interaction is now handled by the backend CRM REST API.In this version of the CRM Web App REST client, we will simply make REST client calls to the CRM REST API to retrieve the data. 
The CRM Web App REST Client needs to connect to the backend REST API. To accomplish this, we added a configuration property that has the url of the CRM REST API.






