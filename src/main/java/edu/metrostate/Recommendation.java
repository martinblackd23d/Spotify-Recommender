package edu.metrostate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import java.util.HashMap;

public class Recommendation {
    private List<String> seedSongs;
    private List<String> seedArtists;
    private List<String> seedGenre;
    private HashMap<String, Integer> attributes;

    /**
     * Constructor for Recommendation
     */
    public Recommendation(){
        this.seedSongs = new ArrayList<String>();
        this.seedArtists = new ArrayList<String>();
        this.seedGenre = new ArrayList<String>();
        this.attributes = new HashMap<String, Integer>();
    }

    /**
     * Adds a song to the recommendation
     * @param song Song object
     */
    public void addSong(Song song){
        seedSongs.add(song.getId());
        seedArtists.add(song.getArtistId());
    }

    public void addAttribute(String attribute, Integer value){
        attributes.put(attribute, value);
    }

    public void addArtist(String artist){
        seedArtists.add(artist);
    }

    public void addGenre(String genre){
        seedGenre.add(genre);
    }

    /**
     * Gets a list of songs based on the recommendation's current settings
     * @param auth Auth object
     * @return Playlist object with the recommended songs
     */
    public Playlist getRecommendation(Auth auth) {
        Playlist newPlaylist = new ArrayPlaylist();
        
        // configure request
        String url = "https://api.spotify.com/v1/recommendations";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + auth.getAccessToken());

        // configure parameters of the request
        HashMap<String, String> params = new HashMap<String, String>();
        if (seedSongs.size() > 0){
            params.put("seed_tracks", String.join(",", seedSongs));
        }
        if (seedArtists.size() > 0){
            params.put("seed_artists", String.join(",", seedArtists));
        }
        if (seedGenre.size() > 0){
            params.put("seed_genres", String.join(",", seedGenre));
        }

        // make request
        JsonObject response = null;
        try {
            response = Request.request("GET", url, params, headers, null);
        } catch (Exception e) {
            e.printStackTrace();
            return newPlaylist;
        }

        // parse response into playlist
        for (int i = 0; i < response.get("tracks").getAsJsonArray().size(); i++){
            JsonObject track = response.get("tracks").getAsJsonArray().get(i).getAsJsonObject();
            newPlaylist.addSong(new Song(track.get("id").getAsString(), auth));
        }
        return newPlaylist;
    }
}
