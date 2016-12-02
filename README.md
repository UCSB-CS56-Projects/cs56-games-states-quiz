# cs56-games-states-quiz
======================

project history
===============
```
 W14 | jcneally 5pm | zhansaya19, jennyvien | Quiz on US States with GUI
 W16 | ashedden 6pm | rowallen, ryankemper  | Added hint button, difficulty settings, improved architecture
```

Zhansaya Abdikarimova
Jenny Vien

Ryan Allen
Ryan Kemper


## Description

* This application is a geography quiz that tests the users' knowledge of the locations of states and capitals on the US map. As for the future, it can be advanced to choosing different countries and continents. 


* javadoc: http://www.cs.ucsb.edu/~rowallen/cs56/W16/StateQuiz/javadoc/

## Documentation

* The main frame of the game is in the `GameFrame` class that holds two different main panels: `FrontPanel` and `GamePanel`. 

* When the quiz starts, it askes if the user wants to be tested on state locations or their capital locations. The initial cover page is in the `FrontPanel` class where there are two buttons for the two options, as well as radio buttons to select the difficulty settings. 

(Note: user interface screenshots may not reflect the most recent design iteration)

![](http://i.imgur.com/1IdD5Zv.png)

* After selecting option of the quiz, `GamePanel` class runs the selected option. The `GamePanel` class has a MapPanel, a question scrollbar, an answer scroll bar, and 3 difficulty buttons. The `MapPanel` class holds the map of the United States with buttons for every state. If the user selects the state option it asks questions about each state location in random order, and if he or she selects the "states then capitals" option, the game will generate capital questions respectively. Here are the examples of states and capitals quizzes:  


![](http://i.imgur.com/XJQ9DyI.png)


![](http://i.imgur.com/ALQwhRo.png)



* All of the data is stored in the `States.txt` file. It has a list of states, their capitals and locations for buttons: ``` Alabama,Montgomery,495,310 ```

* The program stores data in the `Country` class. As you can see, in the constructor, every Country object has its name, capital and list of its states. It creates an `ArrayList` of `State` objects.  

```java
public Country(){
	states = new ArrayList<State>();
	this.name = null;
	this.capital = null;
}
``` 

The `State` class stores information about each state of a particular country

```java
public State(String name, String capital, int x, int y){
	this.name = name;
	this.capital = capital;
	this.xCoord = x;
	this.yCoord = y;
}
```
Below there is a method in `Country` class that reads all the information about the country from a file

```java
public void addStates(int numberOfStates, File file) throws Exception {
	Scanner scanner = new Scanner(file);

	String line;
	int x;
	int y;

	for(int i=0;i<numberOfStates;i++){
	    line = scanner.nextLine();
	    String[] splitted = line.split(",");

	    x=Integer.parseInt(splitted[2]);
	    y=Integer.parseInt(splitted[3]);
	    states.add(new State(splitted[0],splitted[1],x,y));
	    
	}
 }
```

* Another very important file that actually runs the game is `QuestionManager` class. It has the basic logic for running the quiz. It stores the list of states and their capitals, generates random indexes for the questions, and asks questions until all of the states asked. QuestionManager also supports the aforementioned game modes (states, capitals, states then capitals).

## How to run 
The main method is in `GameFrame` and in order to start the game, use `ant run`. 

## W16 Final Remarks

* While our project does not specifically employ the Model-Viewer-Controller (MVC) design pattern, the existing architecture distributes the tasks in a way that loosely aligns with the design. Storage of data is handled by the State and Country classes, corresponding to the "model" part of MVC. QuestionManager serves as the intermediary between the data in the "model" classes and the graphical display, so it functions as the controller. Finally, graphical elements are handled by GameFrame.java, GamePanel and FrontPanel, corresponding to the "viewer".


* Future refactoring of the project could more formally implement the MVC framework. This would have the benefit of making it far easier to comprehend the overall structure of the project. Additionally, separating out the 3 different roles (M-V-C) will make it easy to make changes that only affect the relevant portion of the MVC pattern.

### Ideas for future developers
There are several ways of how the game can be developed further:
	
	- game has quizzes for states and capitals mixed together
	- there is an option for continent, countries quizes
	- quizzes for states and capitals of other countries respectively
	- go back button for both state and capital quiz
	- expand behavior of hint button to offer deeper functionality
	- play a sound based off whether a correct or incorrect answer is selected
	- record and analyze statistics about player performance to generate a "report" at the end of the game

## F16 Final Remarks

* Take the time at the beginning of the project to go through each class and fully understand how they all connect to each other (perhaps by drawing out the connections). At this stage, using an IDE could be useful to jump around the different classes and find the implementation and usages of all the functions. This will be really helpful in adding new features and refactoring the code. At first glance, it may look intuitive, but once you try to add new code, you find that there are usually more levels of the program that interact with it.

* This code would benefit from more extensive commenting. Some of the variable names are straightforward, but some of them seem similar.

### Ideas for Future Developers
This game has plenty of room for improvement!

	- game has quizzes for states and capitals mixed together
	- there is an option for continent, countries quizes
	- quizzes for states and capitals of other countries respectively
	- record and analyze statistics about player performance to generate a "report" at the end of the game
	- expand the high score system- add nicknames, save scores from each mode, encrypt the file
	- implement an online chat room system
	- add an options menu (for muting the sound effects, etc.)
