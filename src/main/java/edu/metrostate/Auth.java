package edu.metrostate;

import com.google.gson.JsonObject;
import java.util.Map;
import java.util.HashMap;
import java.awt.Desktop;
import java.net.URI;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.net.InetSocketAddress;
import java.io.OutputStream;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import java.io.IOException;

import java.util.Base64;

public class Auth {
	private String CLIENT_ID = "1cad4f4ba7094f96bfb35d07211a4298";
	private String REDIRECT_URI = "http://localhost:8080/callback";
	private String accessToken;
	private String CLIENT_SECRET = "a542bc4f487e4eb1b18b9c39bbe2dc35";
	private String userId;

	private BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

	/**
	 * Logs the user into Spotify and retrieves an access token, as well as the user's ID.
	 * use getAccessToken() and getUserId() to retrieve the access token and user ID respectively.
	 * @return success
	 */
	public boolean login() {
		String url = "https://accounts.spotify.com/authorize" + 
			"?client_id=" + CLIENT_ID + 
			"&response_type=code" + 
			"&redirect_uri=" + REDIRECT_URI + 
			"&scope=playlist-modify-private%20playlist-modify-public";
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI(url));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		String code;
		try {
			interceptRedirect();
			code = queue.take();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		String data = "grant_type=authorization_code&code=" + code + "&redirect_uri=" + REDIRECT_URI;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Basic " + 
			Base64.getEncoder().encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes()));
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		JsonObject response;
		try {
			response = Request.request("POST", "https://accounts.spotify.com/api/token", null, headers, data);
			accessToken = response.get("access_token").getAsString();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		try {
			response = Request.request("GET", "https://api.spotify.com/v1/me", null, 
				new HashMap<String, String>(){{put("Authorization", "Bearer " + accessToken);}}, null);
			userId = response.get("id").getAsString();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getUserId() {
		return userId;
	}

	private void interceptRedirect() throws IOException {

		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		server.createContext("/callback", new HttpHandler() {
			@Override
			public void handle(HttpExchange exchange) throws IOException {
				String response = "Auth successful";
				exchange.sendResponseHeaders(200, response.length());
				
				OutputStream os = exchange.getResponseBody();
				os.write(response.getBytes());
				os.close();

				accessToken = exchange.getRequestURI().getQuery().split("code=")[1];
				try {
					queue.put(accessToken);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				server.stop(0);

			}
		});
		server.setExecutor(null);
		server.start();
	}

}
