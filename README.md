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

4 concrete classes (all demonstrated in MainApp.java):
- ArrayPlaylist - ArrayPlaylist.java
- Auth - Auth.java
- Song - Song.java
- Recommendation - Recommendation.java

Interface, implemented by ArrayPlaylist:
- Playlist - ArrayPlaylist.java

Abstract class, inherited from by Song
- AudioContent - Song.java
