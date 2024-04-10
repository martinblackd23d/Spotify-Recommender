# Spotify Song Recommender

Martin Black, Torri Presler, Tracey Treat

ICS 372

The Spotify Song Recommender is a Java program that, when given a seed (for example, another song), uses the [Spotify API](https://developer.spotify.com/documentation/web-api) to generate personalized song recommendations. These recommendations are then saved as a new playlist. 

# Running the project

```./gradlew run```

1. Press login button and log in to test spotify account in browser
3. Click on settings and set desired playlist length
4. Type song title in searchbox and click on search
5. From the list of songs, click on the one you want to get recommendations for
6. Click on Export to add playlist in Spotify account
7. When prompted, change name of the playlist

# Account to test project
Email:
spotifyrecommendations@outlook.com
Password:
ICS-372 project

# Functional requirements
The functionality for all of these can be found in the MainSceneController.java file.
## Log the user into Spotify

**How the project meets this requirement:**
Using a Spotify account, users can log into the system. 

**How to test:**
On the main screen, click the 'Login' button. The Spotify login page will open up in a browser. Input test Spotify account's username and password. After successful login, you can close the browser page and return to the application. 

## Search for a song from a query

**How the project meets this requirement:**
The program takes a user-inputted query and returns results using the Spotify API.

**How to test:** On the main screen, type an input into the search bar (the input field to the left of the 'Search' button) and click 'Search'. The program then displays the results.

## Specify the number of songs to be searched for or recommended

**How the project meets this requirement:**
Users can specify the song limit using the Settings feature. The program will display up to that many songs.

**How to test:** On the main screen, click the 'Settings' button. Input the song limit in the text field. Then, click 'OK'. 

## Create a list of recommendations from a selected song

**How the project meets this requirement:**
The program returns the specified number of recommendations based on the selected song.

**How to test:**
After a successful search, click on any song in the resulting list.

## Export recommended list as playlist

**How the project meets this requirement:** 
Users can export the playlist created by the program with the 'Export' feature. Then, this playlist can be used in Spotify.

**How to test:** After creating a playlist, click the 'Export' button.

## Give name to exported playlist

**How the project meets this requirement:** The exported playlist can be given a user specified name.

**How to test:** After clicking the export button, type the desired name and click OK.


## Extensibility
The classes are written in a way that enables extending functionality beyond what is currently implemented.
For example:
- The Request class can be used to connect to other API endpoints
- The Song and Recommendations classes can be easily extended to support attributes for more control over results
- The Settings class can be extended for more control
- The functions handling user input and the functions actually doing processing are somewhat decoupled for easier modification

## Robustness

**How the project meets this requirement:**
Errors in usage are checked for and the appropriate error messages are presented.
For example:
- If login was not successful, or the search query is empty, searching will give a warning
- If no playlist is available to export, it will give a warning
- If invalid input is entered for limit, it will give a warning

## Security
**How the project meets this requirement:**
This application implements the OAuth protocol to securely authenticate the user and use the API.

## Usability
**How the project meets this requirement:**
While the application is waiting for a request, it is signaled to the user by changing the background color.
Additionally, buttons are disabled, so repeated inputs don’t lead to errors.


# Milestone 3 requirements
## Code structure

ArrayPlaylist.java
- Interface for Playlist structures
- Implementation class using ArrayList

Auth.java
- Class for handling logging in and authorization

MainApp.java
- main class
- sets up UI

MainSceneController.java
- contains application logic

Request.java
- Static class for handling HTTP requests

Song.java
- Abstract class for AudioContent structures
- Concrete subclass Song

Recommendation.java
- Class for handling recommendations

## Where to find requirements

4 concrete classes (all demonstrated in MainSceneController.java in handleLogin()):
- ArrayPlaylist - ArrayPlaylist.java
- Auth - Auth.java
- Song - Song.java
- Recommendation - Recommendation.java

Interface, implemented by ArrayPlaylist:
- Playlist - ArrayPlaylist.java

Abstract class, inherited from by Song
- AudioContent - Song.java
