package edu.metrostate;

import java.util.ArrayList;

interface Playlist {
	public void addSong(Song song);
	public void removeSong(String songId);
	public ArrayList<Song> getList();
	public void exportPlaylist();
	public String getStats();
}

public class ArrayPlaylist implements Playlist {
	private ArrayList<Song> playlist;

	public ArrayPlaylist() {
		playlist = new ArrayList<Song>();
	}

	@Override
	public void addSong(Song song) {
		playlist.add(song);
	}

	@Override
	public void removeSong(String songId) {
		for (int i = 0; i < playlist.size(); i++) {
			if (playlist.get(i).getId().equals(songId) || playlist.get(i).getTitle().equals(songId)) {
				playlist.remove(i);
				return;
			}
		}
	}

	public void removeSong(int index) {
		playlist.remove(index);
	}

	@Override
	public ArrayList<Song> getList() {
		return playlist;
	}

	@Override
	public void exportPlaylist() {
		//TODO: implement
	}

	@Override
	public String getStats() {
		//TODO: implement
		return "";
	}
}