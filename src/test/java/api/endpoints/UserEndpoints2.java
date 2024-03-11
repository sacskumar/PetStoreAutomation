

package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndpoints.java
//Created for perform Create,Read, Update And Delete Request User API (CRUD Operations)


public class UserEndpoints2 {
	
	// Method created for getting URL's From properties files
	
	static ResourceBundle getURL() // userdefine method i am creating will  get the URL from properties File
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");  //Load properties file
		return routes;
	}
		
/*"routes" is Name Of Properties File we have specify in getBundle() which is stored in"src/test/resources"
		entire path is required only give name of file
		--> ResoursceBundle is class , routes is variable*/
	
	public static Response createUser(User payload)
	{
		String post_url=getURL().getString("post_url");
		
		// Above statement which will get the actual url from properties file
		
			Response response = given()
					
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
			.when()
			.post(post_url);
			return response;
		}
		
	public static Response readUser(String userName)
		{
			String get_url= getURL().getString("get_url");
			
			Response response = given()
				
			.pathParam("username", userName)
			
			.when()
			.get(get_url);
			return response;
		}
		
	public static Response updateUser(String userName, User payload)
		{
		String update_url = getURL().getString("update_url");
		
			Response response =given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.pathParam("username", userName)
			.when()
			.put(update_url);
			return response;
		}
		
	public static Response deleteUser(String userName) 
		{
		String delete_url=getURL().getString("delete_url");
		
		Response response = given()
		.pathParam("username", userName)
		.when()
		.delete(delete_url);
		return response;
		}
	
	
	
	
	
	

}
