package edu.ucsb.cs56.projects.games.states_quiz;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * MapPanel is a class that extends JPanel and implements ActionListener. It
 * prints out the image of the blank map and holds all of the information about
 * each of the states. It sets up the buttons for each of the states and sends
 * QuestionManager the user's input.
 *
 * @author Nina Kaufman
 * @author Jenny Vien
 * @author Zhansaya Abdikarimova
 * @author Nick Eidler
 * @author Ryan Allen
 * @author Ryan Kemper
 */

public class MapPanel extends JPanel implements ActionListener {

    public static final String STATES_FILE_NAME = "States.txt";
    public static final int NUM_STATES = 50;

    public JButton[] stateButtons;
    public ArrayList<State> statesArray;
    public int totalscore = 0;
    public JButton answer;

    private QuestionManager questionManager;
    private Country c;
    private BufferedImage map;

    private SoundManager soundManager;

    public MapPanel() {

        this.setLayout(null);

        try {
            map = ImageIO.read(getClass().getClassLoader().getResource("image/map-of-united-states.jpg"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        this.repaint();

        stateButtons = new JButton[NUM_STATES];

        int x = 0;
        int y = 0;
        int width = 25;
        int height = 25;

        File fileName = new File(STATES_FILE_NAME);

        c = new Country();
        try {
            c.addStates(NUM_STATES, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        statesArray = c.getStatesArray();

        soundManager = new SoundManager();

        for (int i = 0; i < statesArray.size(); i++) {
            stateButtons[i] = new JButton();
            this.add(stateButtons[i]);

            x = statesArray.get(i).getXCoord();
            y = statesArray.get(i).getYCoord();

            stateButtons[i].setBounds(x, y, width, height);
            stateButtons[i].addActionListener(this);
            stateButtons[i].setVisible(false);
        }


    }

    /**
     * @param g Graphics object that prints the image of the map
     */

    public void paintComponent(Graphics g) {
        g.drawImage(map, 0, 0, this);
    }

    /**
     * @return QuestionManager
     */

    public QuestionManager getQuestionManager() {
        return this.questionManager;
    }

    /**
     * @param qm Represents the questionManager being made
     */

    public void setQuestionManager(QuestionManager qm) {
        this.questionManager = qm;
    }

    /**
     * @param button button that was clicked by a user
     */

    public void setAnswer(JButton button) {
        this.answer = button;
    }

    /**
     * Sends the button clicked to QuestionManager.
     *
     * @param e ActionEvent if user answered the question
     */

    public void actionPerformed(ActionEvent e) {
        boolean answeredCorrectly = questionManager.mapClickCallback(e.getSource());
        if (answeredCorrectly) {
            soundManager.playCorrectSound();
        } else {
            soundManager.playIncorrectSound();
        }
    }

    /**
     * @return Country object
     */

    public Country getCountry() {
        return c;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public void startRound(){
        for (int i = 0; i < statesArray.size(); i++) {
            stateButtons[i].setVisible(true);
        }
    }
}