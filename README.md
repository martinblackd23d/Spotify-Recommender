# Spotify Song Recommender

Martin Black, Torri Presler, Tracey Treat

ICS 372

The Spotify Song Recommender is a Java program that, when given a seed (for example, another song), uses the [Spotify API](https://developer.spotify.com/documentation/web-api) to generate personalized song recommendations. These recommendations are then saved as a new playlist. 

# Running the project

```./gradlew run```

Press Login button

When the browser prompts, log into your Spotify account

# Current functionality
1. The program starts and displays the main UI
2. If the Login button is pressed it prompts the user to log in
3. Once the user is logged in, it generates a playlist from a hardcoded source song
4. It saves the playlist into the user's library
5. It also displays the main UI with the playlist

# Code structure

ArrayPlaylist.java
- Interface for Playlist structures
- Implementation class using ArrayList

Auth.java
- Class for handling logging in and authorization

MainApp.java
- main class
- sets up UI

MainSceneController.java
- automatically runs some demo functionality when the login button is pressed
- populates listview

Request.java
- Static class for handling HTTP requests

Song.java
- Abstract class for AudioContent structures
- Concrete subclass Song

Recommendation.java
- Class for handling recommendations

# Where to find requirements

4 concrete classes (all demonstrated in MainSceneController.java in handleLogin()):
- ArrayPlaylist - ArrayPlaylist.java
- Auth - Auth.java
- Song - Song.java
- Recommendation - Recommendation.java

Interface, implemented by ArrayPlaylist:
- Playlist - ArrayPlaylist.java

Abstract class, inherited from by Song
- AudioContent - Song.java


# Functional requirements
## Log the user into spotify

**How the project meets this requirement:**
Using a Spotify account, users can log into the system. 

**How to test:**
On the main screen, click the 'Login' button. The Spotify login page will open up in a browser. Input your Spotify username and password. After successful login, you can close the browser page and return to the application. 

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

**How to test:**

## Export recommended list as playlist

**How the project meets this requirement:** 
Users can export the playlist created by the program with the 'Export' feature. Then, this playlist can be used in Spotify.

**How to test:** After creating a playlist, click the 'Export' button.

## Give name to exported playlist

**How the project meets this requirement:**

**How to test:**


## Extensibility
The classes are written in a way that enables extending functionality beyond what is currently implemented.

## Robustness

**How the project meets this requirement:**
Errors in usage are checked for and the appropriate error messages are presented.

## Portability

**How the project meets this requirement:**
This application is written in Java and works on different operating systems.

## Privacy

**How the project meets this requirement:**
This application is integrated with Spotify-- all user accounts are managed by Spotify and none of the user data is collected or stored permanently by this project.

## Security
**How the project meets this requirement:**
This application implements the OAuth protocol to securely authenticate the user and use the API.

## Usability
**How the project meets this requirement:**
While the application is working, it is signaled to the user by changing the background color or showing dialog boxes, and buttons are disabled, so repeated inputs don’t lead to errors.