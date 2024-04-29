package com.ApiTesting;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basePath;;
public class RestfulBookerApiTests {
	
	@BeforeMethod
	public void setup() {
		// Set the base url
		baseURI = "https://restful-booker.herokuapp.com/";
		basePath = "booking";
	}
	
	
	@Test
	public void testGetbooking() {

		given().basePath("booking").log().all().when().get().then().log().all().statusCode(200);
	}
	
	@Test
	public void testGetBookingWithQueryParams() {

		given().basePath("booking").param("firstname", "Jake").param("lastname", "Smith").log().all().when().get()
				.then().log().all().statusCode(200);
	}

	@Test
	public void testGetBookingWithPathParams() {

		given().basePath("booking").pathParam("id", 1668).log().all().when().get("/{id}").then().log().all()
				.statusCode(200);
	}
	@Test
	public void testCreateBooking() {

		String requestBody = "{\r\n" + "    \"firstname\" : \"Jim\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n" + "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}";

		
		given().basePath("booking").header("Content-Type", "application/json").body(requestBody).log().all().when()
				.post().then().log().all().statusCode(200);
	}

}
