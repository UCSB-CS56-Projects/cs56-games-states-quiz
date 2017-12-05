package edu.ucsb.cs56.projects.games.states_quiz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


/**
 * GamePanel sets up the GamePanel with the frame that holds the text for questions and answers and the scrollbar.
 *
 * @author Nina Kaufman
 * @author Jenny Vien
 * @author Zhansaya Abdikarimova
 * @author Ryan Kemper
 * @author Ryan Allen
 * @author Diana Reyes
 */


public class GamePanel extends JPanel {

    static final int SCREEN_WIDTH = 980;
    static final int SCREEN_HEIGHT = 680;
    static final int MAP_X_BOUND = (int) (.75 * SCREEN_WIDTH);
    static final int MAP_Y_BOUND = (int) (.7 * SCREEN_HEIGHT);
    private MapPanel mapPanel;
    private JPanel panel;
    private JTextArea questionTextArea; // text area on bottom where question displays

    private JScrollPane questionScrollPane;
    private JButton hintButton;
    private JButton startButton;
    private Runnable reloadFrame;
    private QuestionManager questionManager;
    
    private StopWatch stopWatch;
   
    public GamePanel(Runnable reloadFrame) {
        this.reloadFrame = reloadFrame;
        Font ourFont = new Font("Verdana", Font.BOLD, 24);
        mapPanel = new MapPanel();

        String questionText = "Welcome to the USA map quiz!\n";
       
        questionTextArea = generateQuestionTextArea(4, 20, ourFont, questionText);
       

        int hintX = (int) (.77 * SCREEN_WIDTH);
        int hintY = (int) (.7 * SCREEN_HEIGHT); 
        hintButton = this.generateHintButton(hintX, hintY, 180, 60, "Click For Hint");
	mapPanel.add(hintButton);
        int homeX = (int) (.77 * SCREEN_WIDTH);
        int homeY = (int) (.55 * SCREEN_HEIGHT);
        JButton homeButton = this.generateHomeButton(homeX, homeY, 180, 60, "Main Menu");
        mapPanel.add(homeButton);

	int startX = (int) (.77 * SCREEN_WIDTH);
        int startY = (int) (.3 * SCREEN_HEIGHT); 
	startButton = generateStartButton(startX, startY, 180, 60, "Start");

        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        mapPanel.repaint();

        this.setLayout(new BorderLayout());
        this.add(mapPanel, BorderLayout.CENTER);

        this.add(questionScrollPane, BorderLayout.SOUTH);

	stopWatch = new StopWatch((int) (.8 * SCREEN_WIDTH), (int) (.4 * SCREEN_HEIGHT), 180, 80);
	mapPanel.add(stopWatch);

        this.setVisible(false);
        this.repaint();
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    private JButton generateStartButton(int x, int y, int w, int h, String text){
	startButton = new JButton();
	mapPanel.add(startButton);
	startButton.setText("<html>" + text + "</html>");
        startButton.setVisible(true);
        startButton.setBounds(x, y, w, h);
        startButton.addActionListener(e ->{
	    stopWatch.start();
	    startButton.setVisible(false);
	    revalidate();
	    repaint();
	    });
	return startButton;
    }
    
    private JButton generateHomeButton(int x, int y, int w, int h, String text) {
        JButton homeButton = new JButton();
        homeButton.setText("<html>" + text + "</html>");
        homeButton.setEnabled(true);
        homeButton.setVisible(true);
        homeButton.setBounds(x, y, w, h);
        homeButton.addActionListener(e -> {
		questionManager.endRound();
		reloadFrame.run();
	    });
        return homeButton;
    }
    
    /**
     * @param x    x coord of hintButton
     * @param y    y coord of hintButton
     * @param w    width of hintButton
     * @param h    height of hintButton
     * @param text hintButton display text
     * @return a newly generated hintButton with the specified x/y/w/h/text values
     */

    private JButton generateHintButton(int x, int y, int w, int h, String text) {
        JButton hintButton = new JButton();
        hintButton.setText("<html>" + text + "</html>");
        hintButton.setEnabled(true);
        hintButton.setVisible(false);
        hintButton.setBounds(x, y, w, h);
        hintButton.addActionListener(e -> {

		State state = mapPanel.getQuestionManager().getCorrectState();
		String stateHint = state.getQuadrant();
		String capitalHint = "Capital's first letter: " + getFirstLetterOfCapital(state.getCapital());
		hintButton.setText("<html>State is " + stateHint + " " + capitalHint + "</html>");
		questionManager.isHintButtonClicked(true);
	    });
        return hintButton;
    }
    

    /**
     * @param rows rows of the question text area
     * @param cols cols of the question text area
     * @param font font of the question text area
     * @param text text of the question text area
     * @return textArea the new question text area, questionScrollPane is also initialized
     */

    private JTextArea generateQuestionTextArea(int rows, int cols, Font font, String text) {

        JTextArea textArea = new JTextArea(rows, cols);
        textArea.setLineWrap(true);
        questionScrollPane = new JScrollPane(textArea);
        questionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        questionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        questionScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
		public void adjustmentValueChanged(AdjustmentEvent e) {
		    e.getAdjustable().setValue(e.getAdjustable().getMaximum());
		}
	    });
        textArea.setFont(font);
        textArea.setEditable(false);
        textArea.append(text);


        return textArea;
    }

    /**
     * Called by QuestionManager when number of guesses hits 3
     *
     * @param b the boolean that represents whether to set the hint button visible or not
     */

    public void setHintButtonVisible(Boolean b) {
        if (!b)
            hintButton.setText("Click For Hint");
        hintButton.setVisible(b);
    }

    /**
     * @return questionTextArea the Text Area with questions
     */
    public JTextArea getQuestionTextArea() {
        return this.questionTextArea;
    }

    /**
     * Adds text to the questionTextArea.
     *
     * @param text the text to append to the question area
     */
    public void appendQuestionTextArea(String text) {
        this.questionTextArea.append(text);
    }

    /**
     * Sets text in questionTextArea.
     *
     * @param text the text to set the question area to
     */
    public void setQuestionTextArea(String text) {
        questionTextArea.setText(text);
    }

    /**
     * @return mapPanel panel of the map
     */
    public MapPanel getMapPanel() {
        return this.mapPanel;
    }

    public String getFirstLetterOfCapital(String capital) {
        return capital.substring(0, 1);
    }

    public void setQuestionManager(QuestionManager questionManager) {
        this.questionManager = questionManager;
    }
}
