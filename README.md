# Running the project

```./gradlew build```

```./gradlew run```

Currently the program will automatically initiate the login procedure by opening up the sign in page in the browser.

Once successfully signed in, it will create a test playlist with 2 songs in the logged in users library for demonstration purposes.

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





4 concrete classes (all demonstrated in MainApp.java):
-ArrayPlaylist - ArrayPlaylist.java
-Auth - Auth.java
-Song - Song.java

Interface, implemented by ArrayPlaylist:
- Playlist - ArrayPlaylist.java

Abstract class, inherited from by Song
- AudioContent - Song.java