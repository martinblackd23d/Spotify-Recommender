package edu.metrostate;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonObject;

abstract class AudioContent {
	protected String id;
	protected String title;
	protected int length;

	public String toString() {
		return title;
	};

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getLength() {
		return length;
	}
}

public class Song extends AudioContent{

	private ArrayList<String> attributes;
	private String artist;
	private String artistId;

	public Song(String id, Auth auth) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + auth.getAccessToken());
		JsonObject response;
		try {
			response = Request.request("GET", "https://api.spotify.com/v1/tracks/" + id, null, headers, null);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		this.id = id;
		this.title = response.get("name").getAsString();
		this.length = response.get("duration_ms").getAsInt() / 1000;
		this.artist = response.get("artists").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
		this.artistId = response.get("artists").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
		this.attributes = new ArrayList<String>();
	}

	public ArrayList<String> getAttributes() {
		return attributes;
	}

	@Override
	public String toString() {
		return title + " by " + artist;
	}

	public String getArtist() {
		return artist;
	}

	public String getArtistId() {
		return artistId;
	}
}
