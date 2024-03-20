package edu.metrostate;

import java.util.ArrayList;
import java.util.List;

public class Recommendation {
    private List<String> seedSongs;
    private List<String> seedArtists;
    private List<String> seedGenre;
    private List<Attribute> attributes;

    public Recommendation(){
        //constructor
        this.seedSongs = new ArrayList<String>();
        this.seedArtists = new ArrayList<String>();
        this.seedGenre = new ArrayList<String>();
        this.attributes = new ArrayList<Attribute>();
    }
    public void addSong(String song){
        seedSongs.add(song);
    }
    public void addAttribute(Attribute attribute){
        attributes.add(attribute)
    }
    public void addArtist(String artist){
        seedArtists.add(artist);
    }
    public void addGenre(String genre){
        seedGenre.add(genre);
    }
    public List<Song> getRecommendation(){
        List<Song> newPlaylist = new ArrayList<Song>();
        //makes API call
        //this can be dummy data for now

        //when this is finished it should be calling request from the Request class
        //returns playlist when done.
        return newPlaylist;
    }
}
