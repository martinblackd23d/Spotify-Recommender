# Running the project

```./gradlew run```

Currently the program will automatically initiate the login procedure by opening up the sign in page in the browser.

Once successfully signed in, it will create a recommendation from one song and save it as a playlist for the logged in user.

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