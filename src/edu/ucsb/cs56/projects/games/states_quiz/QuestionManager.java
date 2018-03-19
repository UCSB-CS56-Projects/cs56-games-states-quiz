package edu.ucsb.cs56.projects.games.states_quiz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * QuestionManager manages the current question and score
 *
 * @author Nina Kaufman
 * @author Jenny Vien
 * @author Zhansaya Abdikarimova
 * @author Nick Eidler
 * @author Ryan Allen
 * @author Ryan Kemper
 * @author Diana Reyes
 * @author Lindsey Nguyen
 **/

public class QuestionManager {
    protected GamePanel gamePanel;
    protected MapPanel mapPanel;

    protected boolean isSkip;

    protected String gameMode;
    protected String difficulty;
    protected String option;
    protected int currentQuestion;
    protected int randIndex;
    protected int currentScore;
    protected int guesses = 0;
    protected int easyLength = 40;
    protected int normalLength = 25;
    protected int hardLength = 0;

    protected ArrayList<State> states;
    protected ArrayList<Integer> randStateIndexes;
    protected ArrayList<State> correctStates;
    protected ArrayList<JButton> hiddenButtons;

    private Runnable reloadFrame;

    /**
     * Constructor QuestionManager creates a new array of the fifty state names
     */
    public QuestionManager(GamePanel parent, Runnable reloadFrame) {
        this.reloadFrame = reloadFrame;
        states = new ArrayList<State>();
        correctStates = new ArrayList<State>();
        randStateIndexes = new ArrayList<Integer>();
        hiddenButtons = new ArrayList<JButton>();

        isSkip = false;

        gamePanel = parent;
        mapPanel = gamePanel.getMapPanel();
        mapPanel.setQuestionManager(this);
        states = mapPanel.getCountry().getStatesArray();

        for (int i = 0; i < states.size(); i++) {
            randStateIndexes.add(i);
        }
    }


    /**
     * Sets the current question and score to zero and then asks the first
     * question. Also gets the GUI and MapPanel from GameFrame.
     */
    public void init() {
        randIndex = (int) (Math.random() * states.size());
        currentQuestion = randStateIndexes.get(randIndex);
        currentScore = 0;
    }

    /**
     * Called by the MapPanel when a button is pressed on the screen.
     *
     * @param o Object representing the button that was clicked.
     */
    public boolean mapClickCallback(Object o) {
        return this.receiveAnswer(((JButton) o));
    }

    /**
     * Called by GamePanel when receiving an event
     *
     * @return returns correct state
     */
    public State getCorrectState() {
        return this.states.get(currentQuestion);
    }

    /**
     * @return returns current difficulty
     */

    public String getDifficulty() {
        return this.difficulty;
    }


    /**
     * Called by the GameFrame when the game starts
     *
     * @param diff String representing the difficulty selected
     */

    public void setDifficulty(String diff) {
        this.difficulty = diff;
    }

    /**
     * @return returns the selected game mode
     */

    public String getGameMode() {
        return this.gameMode;
    }

    /**
     * Called by GameFrame when a game mode is selected and the game starts
     *
     * @param mode String that represents the game mode selected
     */

    public void setGameMode(String mode) {
        this.gameMode = mode;
    }

    /**
     * Asks the next question if the question counter is less than 50. Prints
     * out the current state.
     */
    public int getRandStateIndex() {
        return this.randStateIndexes.size();
    }

    public int getModeLength() {
        switch (difficulty) {
            case "Easy":
            return easyLength;
            case "Normal":
            return normalLength;
            case "Hard":
            return hardLength;
        }
        return 0;
    }

    public void askNextQuestion() {
        if (!randStateIndexes.isEmpty()) {
            if (Objects.equals(getGameMode(), "Capitals")) {
                gamePanel.appendQuestionTextArea("Click on: " + states.get(currentQuestion).getCapital() + "\n");
            } else {
                gamePanel.appendQuestionTextArea("Click on: " + states.get(currentQuestion).getName() + "\n");
            }
            mapPanel.setAnswer(mapPanel.stateButtons[currentQuestion]);
        } else {
            currentScore += 10;
            endRound();
        }
    }


    /**
     * Ends the game round. Gives the option to play again.
     */
    public void endRound() {
        mapPanel.getSoundManager().playCompletedSound();
        gamePanel.appendQuestionTextArea("Round has ended!");
        UIManager.put("OptionPane.cancelButtonText", "Cancel");
        String username = JOptionPane.showInputDialog(
            gamePanel.getParent(),
            "Game over. Your score: " + currentScore,
            "Enter a username");

        recordHighScore(username);
        showHighScores();
        int n = JOptionPane.showConfirmDialog(
            gamePanel.getParent(),
            "Would you like to play again?",
            "Congratulations!",
            JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            reloadFrame.run();
        }

    }

    public void showHighScores() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(gamePanel);
        File file = new File("high_scores/" + gameMode + "/" + difficulty + ".txt");
        HighscoreDialog hsd = new HighscoreDialog(frame, 500, 500, file);
    }

    public void recordHighScore(String username) {
        String file = "high_scores" + "/" + gameMode + "/" + difficulty + ".txt";
        String time = gamePanel.getStopWatch().getFormattedTime();
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.append(username + "\t" + currentScore + "\t" + time + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Receives the answer from MapPanel and checks to see if the answer is
     * equivalent to the current state. Prints out the current score and
     * increments the counters.
     *
     * @param answerButton JButton that represents answer input
     */
    public boolean receiveAnswer(JButton answerButton) {
        if (isSkip) {
            setIsSkip(false);

            String message = AnswerOption.NO_ANSWER.getMessage();
            gamePanel.setQuestionTextArea(message);
            gamePanel.getStopWatch().addPenalty();

            randStateIndexes.remove(randIndex);

            if (!randStateIndexes.isEmpty()) {
                checkEndRound(getModeLength());
            }
            gamePanel.setHintButtonVisible(false);

            currentScore -= 3;
            this.guesses = 0;

            gamePanel.appendQuestionTextArea("Your current score is: " + currentScore + "\n");
            this.askNextQuestion();
            return true;
        } else if (answerButton == mapPanel.stateButtons[currentQuestion]) {
            AnswerOption answer = null;
            if (getGameMode().equals("States then Capitals")) {
                answer = checkCapital();
            }

            String message;
            if (answer == null) {
                message = AnswerOption.CORRECT.getMessage();
            } else {
                message = answer.getMessage();
            }
            gamePanel.setQuestionTextArea(message);

            correctStates.add(states.get(currentQuestion));

            for (JButton button : this.hiddenButtons) {
                button.setVisible(true);
            }

            if (this.getDifficulty().equals("Easy")) {
                answerButton.setVisible(false);
            }

            randStateIndexes.remove(randIndex);


            if (!randStateIndexes.isEmpty()) {
                checkEndRound(getModeLength());
            }
            gamePanel.setHintButtonVisible(false);

            currentScore += 10;
            currentScore -= (guesses * 2);
            this.guesses = 0;


            gamePanel.appendQuestionTextArea("Your current score is: " + currentScore + "\n");
            this.askNextQuestion();
            return true;
        } else {
            gamePanel.getStopWatch().addPenalty();
            if (this.getDifficulty().equals("Medium") || this.getDifficulty().equals("Easy")) {
                answerButton.setVisible(false);
                this.hiddenButtons.add(answerButton);
            }

            guesses++;
            if (guesses == 3)
                gamePanel.setHintButtonVisible(true);

            String stateChosen = "";
            String capitalChosen = "";


            for (int i = 0; i < 50; i++) {
                if (answerButton == mapPanel.stateButtons[i]) {
                    stateChosen = states.get(i).getName();
                    capitalChosen = states.get(i).getCapital();
                    break;
                }
            }


            if (getGameMode().equals("States") || getGameMode().equals("States then Capitals")) {
                gamePanel.getQuestionTextArea().setText("Nope! That was " + stateChosen + "! ");
            } else if (getGameMode().equals("Capitals")) {
                gamePanel.getQuestionTextArea().setText("Nope! That was " + capitalChosen + "! ");
            }
            this.askNextQuestion();
            return false;
        }
    }

    public void checkEndRound(int gameLength) {

        if (getRandStateIndex() >= gameLength) {
            randIndex = (int) (Math.random() * (randStateIndexes.size() - 1));
            currentQuestion = randStateIndexes.get(randIndex);
        } else {
            endRound();
        }
    }

    /**
     * Called when in StateThenCapitals mode
     * Continuously loops until the capital is entered correctly
     */

    private AnswerOption checkCapital() {
        AnswerOption answer = askCapital();
        StateErrorChecker errorChecker = new StateErrorChecker(states.get(currentQuestion).getName());

        boolean isFirst = true;
        while (answer == AnswerOption.INCORRECT) {
            if (isFirst) {
                gamePanel.appendQuestionTextArea(errorChecker.guessCritique(AnswerOption.getCapital()));
                isFirst = false;
            }
            gamePanel.setQuestionTextArea(errorChecker.guessCritique(AnswerOption.getCapital()));
            answer = askCapital();
            this.guesses++;
            gamePanel.getStopWatch().addPenalty();
        }
        if (answer == AnswerOption.NO_ANSWER) {
            guesses++;
        }
        return answer;
    }
 public void isHintButtonClicked(boolean hintButtonClicked) {
    if (hintButtonClicked) {
        this.currentScore -= 2;
    }
}

public void setIsSkip(boolean skip) {
    isSkip = skip;
}

    /**
     * Called by checkCapital during StateThenCapitals mode
     * Prompts the user for the capital of the current state
     *
     * @return AnswerOption representing if capital input is correct or not
     */

    private AnswerOption askCapital() {
        UIManager.put("OptionPane.cancelButtonText", "Skip");
        String s = JOptionPane.showInputDialog(
            gamePanel.getParent(),
            "Enter the capital of " + states.get(currentQuestion).getName() + ":",
            "Capital Input",
            JOptionPane.PLAIN_MESSAGE);
        System.out.print(s);
        if (s == null || s.equals("Skip")) {
            return AnswerOption.NO_ANSWER;
        } else if ((s.toLowerCase()).equals((states.get(currentQuestion).getCapital()).toLowerCase())) {
            return AnswerOption.CORRECT;
        }
        AnswerOption.setCapital(s);
        return  AnswerOption.INCORRECT;
    }
}