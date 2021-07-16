package com.brane.springdemo.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brane.springdemo.model.Customer;

//THIS IS NEW SERVICE LAYER, SO WE CAN MAKE CALLS FROM THE CRM REST CLIENT(FRONTEND) TO THE CRM REST API(BACKEND)
//THESE ARE 2 SEPARATED APPLICATIONS THAT COMMUNICATE TOGETHER, TO RETRIEVE DATA FROM THE DATABASE.
//WE NEED TO MAKE RELATIONSHIP BETWEEN THIS 2 APPS, AND WE WILL DO THAT WITH THIS SERVICE LAYER.
@Service
public class CustomerServiceRestClientImpl implements CustomerService {

	//private filed theRestTemplate, so we can do constructor dependency injection
	private RestTemplate theRestTemplate;

	//private field for CRM REST API url
	//@Value("${crm.rest.url}")
	private String crmRestUrl;
	
	//logger just for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	//constructor dependency injection
	@Autowired
	public CustomerServiceRestClientImpl(RestTemplate restTemplate,@Value("${crm.rest.url}") String theUrl){
		theRestTemplate = restTemplate;
		crmRestUrl = theUrl;
		
		//just for diagnostics, to see if he loaded ok url for CRM REST API
		logger.info("Loaded property:  crm.rest.url=" + crmRestUrl);
	}
	
	
	
	@Override
	public List<Customer> getCustomers() {
		
		//just to know if he execute this method getCustomers()
		logger.info("in getCustomers(): Calling REST API " + crmRestUrl);

		//make call from CRM REST CLIENT to CRM REST API to retrieve ALL CUSTOMERS OBJECT from db.
		//Exchange method execute requests of any HTTP methods an returns instance of ResponseEntity object.
		//1.url for CRM REST API, 2.HTTP request method, which we are using(GET),
		//3.null for HttpEntity because have @Nullable annotation.HttpEntity is what you are going to pass in Request(with header) and what you are getting in Response.
		//4.type of return value and that is List<Customer>, because that is response type
		ResponseEntity<List<Customer>> responseEntity =
				theRestTemplate.exchange(crmRestUrl, HttpMethod.GET, null, 
													 new ParameterizedTypeReference<List<Customer>>() {});
				
		
		//Get the list of customers from response.
		//Jackson will convert now JSON objects to JAVA POJO, because of this we need Customer class.
		List<Customer> customers = responseEntity.getBody();

		//we will now show all customers from the database
		logger.info("in getCustomers(): customers" + customers);
		
		//returns all customers, converted java objects
		return customers;
	}

	
	
	@Override
	public Customer getCustomer(int theId) {

		logger.info("in getCustomer(): Calling REST API " + crmRestUrl);

		//make call from CRM REST CLIENT to CRM REST API to retrieve ONE CUSTOMER OBJECT from db.
		//We are calling GET HTTP METHOD using this getForObject(), this methos have 2 parameters.
		//1.url from our CRM REST API + "/" + Id of our customer object from the database
		//2.Customer.class is RESPONSE TYPE AND THAT IS Customer object.
		//NOW JACKSON WILL CONVERT JSON OBJECT TO JAVA POJO.
		Customer theCustomer = theRestTemplate.getForObject(crmRestUrl + "/" + theId,  Customer.class);

		//logging that one customer object from db
		logger.info("in saveCustomer(): theCustomer=" + theCustomer);
		
		return theCustomer;
	}

	
	
	@Override
	public void saveCustomer(Customer theCustomer) {

		logger.info("in saveCustomer(): Calling REST API " + crmRestUrl);
		
		//Get customerId, if customer id is null then save customer, else update customer
		int customerId = theCustomer.getId();


		if (customerId == 0) {
			
			//Make call from CRM REST CLIENT to CRM REST API to SAVE CUSTOMER OBJECT in db.
			//We are using method postForEntity(), because we are sending Customer Entity(object).
			//1.url from our CRM REST API,
			//2.Request object Customer to be POSTED (theCustomer object),
			//3.Response type (String or Customer).
			//NOW JACKSON WILL CONVERT JSON OBJECT IN JAVA POJO.
			theRestTemplate.postForEntity(crmRestUrl, theCustomer, String.class);			
		
		} else {
			//Make call from CRM REST CLIENT to CRM REST API to UPDATE CUSTOMER OBJECT in db.
			//If we have id for a customer object then UPDATE CUSTOMER.
			//1.url from our CRM REST API,
			//2.Request object Customer to be UPDATED (PUT-theCustomer object).
			theRestTemplate.put(crmRestUrl, theCustomer);
		}
		
		//when we save customer object, log this message.
		logger.info("in saveCustomer(): success");	
	}

	
	
	@Override
	public void deleteCustomer(int theId) {

		logger.info("in deleteCustomer(): Calling REST API " + crmRestUrl);

		//Make call from CRM REST CLIENT to CRM REST API to DELETE CUSTOMER OBJECT in db.
		//1.url from our CRM REST API + "/" + Id of our customer object from the database
		theRestTemplate.delete(crmRestUrl + "/" + theId);

		//Logging information about deleted customer (theId).
		logger.info("in deleteCustomer(): deleted customer theId=" + theId);
	}

}
