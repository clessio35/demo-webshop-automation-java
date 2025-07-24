package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;

import hooks.Hooks;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.MetodosUtils;


public class ApiAutomationExerciseService {
	
	private Response response;

	public void accessApi(String url) {
		System.out.println("Access API " + url);
		RestAssured.baseURI = url;
	}

	public void sendRequestGETMethod(String endpoint) {
		System.out.println("Send request for endpoint: " + endpoint);
		response = RestAssured.given().log().all()
			.contentType(ContentType.JSON).get(endpoint);
		System.out.println("Status code: " + response.getStatusCode());
	}
	
	public void validateCompleteProductsList() {
		System.out.println("Validate List products");
		response.then().statusCode(200).log().body().extract().jsonPath();
		List<Map<String, Object>> products = response.jsonPath().getList("products");
		for (Map<String, Object> item : products) {
		    Assert.assertTrue(item.get("id").getClass().equals(Integer.class));
		    Assert.assertTrue(item.get("name").getClass().equals(String.class));
		    Assert.assertTrue(item.get("price").getClass().equals(String.class));
		    Assert.assertTrue(item.get("brand").getClass().equals(String.class));
		}
		MetodosUtils.saveTextEvidence(response.asPrettyString(), Hooks.getScenarioName());
	}
	
	public JSONObject payload() {
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put("search_product", "Tshirt");
		JSONObject json = new JSONObject(info);
		return json;
	}
	

	public void sendRequestPOSTMethod(String endpoint) {
		System.out.println("send post request");
		response = RestAssured.given().log().body()
			.contentType(ContentType.JSON).body(payload())
			.post(endpoint).then().statusCode(200).extract().response();
		System.out.println("Status code: " + response.getStatusCode());
	}

	public void validateFullResult(String result) {
		System.out.println("validate result");
		JsonPath json = response.jsonPath();
		List<Map<String, Object>> products = json.getList("products");
		 for (Map<String, Object> product : products) {
			 String name = (String) product.get("name");
		     if (name == null) continue;
			 Assert.assertTrue(" product '" + name + "' not found '" + result + "'",
		                name.toLowerCase().contains(result.toLowerCase()));
		}
		MetodosUtils.saveTextEvidence(response.asPrettyString(), Hooks.getScenarioName());
	}

	public void validateTimeResponse(String timeResp) {
		System.out.println("validate response time");
		long time = response.time();        
		long timeResponse = Long.parseLong(timeResp) * 1000;      

		if (time < timeResponse) {
		    System.out.println("Response time OK: " + time + "ms (<= " + timeResponse + "ms)");
		} else {
		    System.err.println("Response time above allowed: " + time + "ms (> " + timeResponse + "ms)");
		    System.out.println();
		    Assert.fail("Response time above " + timeResponse + "ms: " + time + "ms");
		}
		MetodosUtils.saveTextEvidence(response.asPrettyString(), Hooks.getScenarioName());
	}

}
