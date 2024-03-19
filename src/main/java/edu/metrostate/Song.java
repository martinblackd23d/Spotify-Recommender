package edu.metrostate;

import java.util.ArrayList;

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

	public Song(String id) {
		//TODO: get song info from Spotify API
		this.id = id;
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
}
