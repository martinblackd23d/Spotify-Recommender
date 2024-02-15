# Project Skeleton

When submitting your final project, delete the contents of this file and write your project build and run steps here.

This is a base project upon which a JavaFX application can be built. It is meant to
be used with Intellij IDEA and has been tested with the [community edition](https://www.jetbrains.com/idea/download).

This project uses [Gradle](https://gradle.org) through the "Gradlew Wrapper" (this means that you don't need to
install) Gradle separately as its build tool and requires a Java
Development Kit version 17 If using IntelliJ IDEA, new versions of the JDK can be installed without leaving the IDE.
Open the settings pane by selecting "IntelliJ IDEA -> Settings" on MacOS and "File -> Settings" on Windows. Then
on the left side of the settings pane expand "Build, Execution, Deployment" -> "Build Tools" -> "Gradle". Then in
the pane on the right near the bottom there is a dropdown menu labeled "Gradle JVM". Click on the dropdown, then on
"+ Add SDK" -> "Download JDK". In the new popup select 17 for version and then for version select
"Eclipse Temurin (AdoptOpenJDK HotSpot) 17.0.4". If you have a Mac with Apple Silicon select
"Eclipse Temurin (AdoptOpenJDK HotSpot) 17.0.4 aarch64".

After installing the JDK click on the elephant icon on the top right side IntelliJ to synchronize your project and
dependencies. Once that is complete, ensure that "ICS372" run configuration is selected in the dropdown to the
left of the green play button on the top right side of the toolbar and then click the play button. The project will
compile and then start the application.