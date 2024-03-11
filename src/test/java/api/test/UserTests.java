package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests 
	{
	
	Faker faker;
	User userPayload;      //User is pojo class , i will create a variable for class
	public Logger logger;  // here create one variable
	
		@BeforeClass
		public void setUp()
		{
			faker = new Faker();
			userPayload = new User();
			
			userPayload.setId(faker.idNumber().hashCode());
			userPayload.setUsername(faker.name().username());
			userPayload.setFirstName(faker.name().firstName());
			userPayload.setLastName(faker.name().lastName());
			userPayload.setEmail(faker.internet().safeEmailAddress());
			userPayload.setPassword(faker.internet().password(5,10));
			userPayload.setPhone(faker.phoneNumber().cellPhone());
			
	//Log
		logger=LogManager.getLogger(this.getClass());
		
			
		}
		
		@Test(priority=1)
		public void testPostUser()
		{
		logger.info("********** Creating User  *************");
		
		Response response =UserEndpoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*********** User is created *************");
		}
		
		@Test(priority=2)
		public void testGetUser() 
		{
			logger.info("************ Reading user info  *************");
		Response response=	UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("************ User info is displyed  *************");
		}
		
		@Test(priority=3)
		public void testUpdateUser()
		{
			logger.info("********** Updating User *************");
		// update the data using Payload
			
			userPayload.setFirstName(faker.name().firstName());
			userPayload.setLastName(faker.name().lastName());
			userPayload.setEmail(faker.internet().safeEmailAddress());
			
			Response response =UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
			response.then().log().all();
			response.then().log().body().statusCode(200);
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("**********  User is updated ************");
			
	// cheak the data After Update	..........so send get request
			
			Response res=	UserEndpoints.readUser(this.userPayload.getUsername());
			res.then().log().all();
			Assert.assertEquals(res.getStatusCode(),200);
		}
		
		@Test(priority=4)
		public void testDeleteUser()
		{
			logger.info("**********   Deleting User   *************");
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
			
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********   User is deleted   *************");
		}
		}
