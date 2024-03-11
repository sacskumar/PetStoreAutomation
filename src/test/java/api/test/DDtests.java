	package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDtests {

	@Test(priority=1, dataProvider="Data", dataProviderClass= DataProviders.class)
	public void testPostuser(String UserID, String UserName, String fname, String lname,String useremail, String psw, String ph) 
	{
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(psw);
		userPayload.setPhone(ph);
		
		Response response =UserEndpoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
// whatever users i am creating above(testPostUser method) i just want to delete here
	
	@Test(priority=2, dataProvider="UserNames",dataProviderClass = DataProviders.class)
	public void testDeleteUser(String UserName) 
	{
		Response response= UserEndpoints.deleteUser(UserName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
}
