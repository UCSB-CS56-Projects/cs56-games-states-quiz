package edu.ucsb.cs56.projects.games.states_quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * GamePanel sets up the GamePanel with the frame that holds the text for questions and answers and the scrollbar.
 *
 * @author Nina Kaufman
 * @author Jenny Vien
 * @author Zhansaya Abdikarimova
 */


public class GamePanel extends JPanel {

    private MapPanel mapPanel; // mapPanel is a MapPanel reference
    private JPanel panel;
    private JTextArea questionTextArea; // text area on bottom where question displays
    private JScrollPane questionScrollPane;
    private JTextArea answerTextArea; // text area on right for correct answers
    private JScrollPane answerScrollPane;
    private Font ourFont;

    public GamePanel() {
        ourFont = new Font("Arial", Font.PLAIN, 24);
        mapPanel = new MapPanel();

        //Setting up the text area to display questions
        questionTextArea = new JTextArea(4, 20);
        questionTextArea.setLineWrap(true);

        questionScrollPane = new JScrollPane(questionTextArea);
        questionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        questionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        questionScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });

        questionTextArea.setFont(ourFont);
        questionTextArea.append("Welcome to the USA map quiz!\n");
        questionTextArea.setEditable(false);

        //Set up text area to display answers
        answerTextArea = new JTextArea(20,10);
        answerTextArea.setLineWrap(true);
        answerScrollPane = new JScrollPane(answerTextArea);
        answerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        answerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        answerTextArea.setFont(ourFont);
        answerTextArea.append("Correct Answers:\n");
        answerTextArea.setEditable(false);


        this.setSize(980, 680);

        mapPanel.repaint();

        this.setLayout(new BorderLayout());
        this.add(mapPanel,BorderLayout.CENTER);
        this.add(questionScrollPane,BorderLayout.SOUTH);
        this.add(answerScrollPane,BorderLayout.EAST);
        this.setVisible(false);
        this.repaint();
    }

    /**
     * @return questionTextArea the Text Area with questions
     */
    public JTextArea getQuestionTextArea() {
        return this.questionTextArea;
    }

    /**
     * Adds text to the questionTextArea.
     * @param txt
     */
    public void setQuestionTextArea(String txt) {
        this.questionTextArea.append(txt);
    }

    /**
     * @return answerTextArea the Text Area with correct answers
     */
    public JTextArea getAnswerTextArea() {
        return this.answerTextArea;
    }

    /**
     * @param txt answer that goes into answerTextAres
     */
    public void setAnswerTextArea(String txt) {
        this.answerTextArea.append(txt + "\n");
    }

    /**
     * @return mapPanel panel of the map 
     */
    public MapPanel getMapPanel() {
        return this.mapPanel;
    }
}
