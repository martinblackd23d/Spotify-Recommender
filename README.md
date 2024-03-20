# Running the project

```./gradlew run```

# Current functionality
1. The program starts and displays the main UI
2. It automatically prompts the user to log in
3. Once the user is logged in, it generates a playlist from a hardcoded source song
4. It saves the playlist into the user's library

# Code structure

ArrayPlaylist.java
- Interface for Playlist structures
- Implementation class using ArrayList

Auth.java
- Class for handling logging in and authorization

MainApp.java
- main class
- sets up UI
- automatically runs some demo functionality

Request.java
- Static class for handling HTTP requests

Song.java
- Abstract class for AudioContent structures
- Concrete subclass Song

Recommendation.java
- Class for handling recommendations



4 concrete classes (all demonstrated in MainApp.java):
- ArrayPlaylist - ArrayPlaylist.java
- Auth - Auth.java
- Song - Song.java
- Recommendation - Recommendation.java

Interface, implemented by ArrayPlaylist:
- Playlist - ArrayPlaylist.java

Abstract class, inherited from by Song
- AudioContent - Song.java
