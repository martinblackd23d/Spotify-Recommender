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

**How to test:**

## User inputs for significance of different criterias for recommending songs

**How the project meets this requirement:**

**How to test:**

## take an input song from the user

**How the project meets this requirement:**

**How to test:**

## take number of required return songs from the user

**How the project meets this requirement:**

**How to test:**

## search for specified number of songs related to the input song 

**How the project meets this requirement:**

**How to test:**

## return the specified number of songs in Spotify

**How the project meets this requirement:**

**How to test:**

## Save the results as playlist

**How the project meets this requirement:**

**How to test:**

## Return listening statistics based on averages

**How the project meets this requirement:**

**How to test:**

# Non-functional requirements
## Backup

**How the project meets this requirement:**

## Flexibility

**How the project meets this requirement:**

## Portability

**How the project meets this requirement:**

## Privacy

**How the project meets this requirement:**
