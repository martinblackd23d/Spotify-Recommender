package edu.metrostate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

interface Playlist {
	public void addSong(Song song);
	public void addSong(Song song, int index);
	public void removeSong(String songId);
	public ArrayList<Song> getList();
	public void exportPlaylist(String name, Auth auth);
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

	public void addSong(Song song, int index) {
		playlist.add(index, song);
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
	/**
	 * Exports the playlist to the user's Spotify account
	 * @param name Name of the playlist
	 * @param auth Auth object
	 */
	public void exportPlaylist(String name, Auth auth) {
		String url = "https://api.spotify.com/v1/users/" + auth.getUserId() + "/playlists";
        
		// create playlist
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + auth.getAccessToken());
        headers.put("Content-Type", "application/json");
        
        String data = "{\"name\":\"" + name + "\",\"public\":false}";
        
		String playlistId;
        try {
            JsonObject response = Request.request("POST", url, null, headers, data);
            playlistId = response.get("id").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

		// add songs to playlist
		url = "https://api.spotify.com/v1/playlists/" + playlistId + "/tracks";
		data = "{\"uris\":[";
		for (int i = 0; i < playlist.size(); i++) {
			data += "\"spotify:track:" + playlist.get(i).getId() + "\"";
			if (i < playlist.size() - 1) {
				data += ",";
			}
		}
		data += "]}";
		try {
			Request.request("POST", url, null, headers, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}