package edu.metrostate;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Request {

	/**
	 * Sends a request to a given URL with the given method, parameters, headers, and data.
	 * @param method The method to use for the request (GET or POST).
	 * @param url The URL to send the request to. this is the API endpoint
	 * @param params The parameters to include in the request.
	 * 					A hashmap object, where the final request goes to:
	 * 						url?key=value&key2=value2
	 * 					Only used for GET requests.
	 * @param headers The headers to include in the request.
	 * 					A hashmap object, with key value pairs of the headers to include in the request.
	 * @param data The data to include in the request as string.
	 * 				Only used for POST requests.
	 * 				If the endpoint requires JSON, it needs to be converted beforehand.
	 * @return The response from the server as a JsonObject.
	 */
	public static JsonObject request(String method, String url, Map<String, String> params, Map<String, String> headers, String data) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = null;

		//set up parameters
		String paramString = "";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		for (String key : params.keySet()) {
			paramString += key + "=" + URLEncoder.encode(params.get(key), StandardCharsets.UTF_8) + "&";
		}

		//set up headers
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		String[] headersArray = new String[headers.size() * 2];
		int i = 0;
		for (String key : headers.keySet()) {
			headersArray[i] = key;
			headersArray[i + 1] = headers.get(key);
			i += 2;
		}
		if (data == null) {
			data = "";
		}

		//create request
		if (method.equals("GET")) {
			request = HttpRequest.newBuilder()
					.uri(new URI(url + "?" + paramString))
					.headers(headersArray)
					.GET()
					.build();
		} else if (method.equals("POST")) {
			request = HttpRequest.newBuilder()
					.uri(new URI(url))
					.headers(headersArray)
					.POST(HttpRequest.BodyPublishers.ofString(data, StandardCharsets.UTF_8))
					.build();
		}

		//send request and get response
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() / 100 != 2) {
			throw new Exception("Request failed with status code " + response.statusCode() + ": " + response.body() + 
					"\nURL: " + url + "\nMethod: " + method + "\nParams: " + paramString + "\n\t" + params.toString() + "\nHeaders: " + headers.toString() + "\nData: " + data);
		}

		JsonObject result = new JsonParser().parse(response.body()).getAsJsonObject();

		return result;
	}
	
}
