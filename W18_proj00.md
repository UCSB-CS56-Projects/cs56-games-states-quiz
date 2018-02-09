# Proj 00: Looking at States Quiz Game

A) The project is a game that quizzes you on the location of US states and their capitals.

B) Our set of user stories:

   * As a user, I can play the states game mode so that I can better my memory of the state locations.
   * As a user, I can play the capitals game mode so that I can better my memory of the capitals of each state.

C) Most of the features work correctly in that it is able to play correctly with states, capitals, or both. 

D) Potential User Stories:
 
   * As a user, I can play the game so that Alabama exists (Alabama never shows up and is not tested even during all 50 states). 
   * As a user, I can see my highscores so that I can track my progress.
   * As a user, I can press a skip button so that I can skip a state or capital I don't know.
   * As a user, I cannot press the the states before pressing the start button so that I am properly timed when playing.
   * As a user, I can press the main menu button instead of exiting so i can play a different game mode.
   * As a user, I can press anywhere on the state to register a button press so that I don't have to press the specific button.
   
E) The current `README.md` has a good amount of information. It includes what the different classes actually do. `FrontPanel` takes care of the actual inputs and buttons while `GamePanel` takes care of the actual quiz. It tells us how to correctly run the game. Something that could be added is to have updated pictures showing the most current iteration. It has a note clarifying this, but instead could have a note that says to have the most recent iteration.

F) Current build.xml is functioning. It can build, run, create javadocs, and create a jar file. Adding tests would be useful. The build and source files are in src or build in a bunch of nested folders at edu/ucsb/cs56/projects/games/states_quiz. That would be nice to clean up.

G) The current issues are very concise and it is easily understood what is needed to be done. The available issues take us well over the 1000 point mark.

H) We didn't add any additional issues. We wanted to focus on the current and basic issues such as adding Alabama back into the game.

I) The code looks very organized such that each object needed to run the game is separated into different classes. These range from having a class to implement the background map, the buttons to play, and a clock in order to run. With class names such as `Country.java` and `StopWatch.java` have good enough names to understand by just looking at the title. The `Country` class tells us that this is the implementation to construct a country and the `StopWatch` class is obviously the class for the stop watch. Specifically, the `Country` class is implemented in such a way that we are able to add other countries other than the USA. To easily convey the entire project, I would give a brief overview of what the classes do and how they interact with each other. From there, you can address the issues with the current code.

J)There are no tests AT ALL. This is actually a current [issue](https://github.com/ucsb-cs56-projects/cs56-games-states-quiz/issues/51 "Add some tests"). We can add tests for literally anything.

